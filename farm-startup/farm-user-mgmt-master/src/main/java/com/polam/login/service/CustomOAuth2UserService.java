package com.polam.login.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.polam.login.entity.Role;
import com.polam.login.entity.RolePk;
import com.polam.login.entity.User;
import com.polam.login.exception.OAuth2AuthenticationProcessingException;
import com.polam.login.repository.RoleRepository;
import com.polam.login.repository.UserRepository;
import com.polam.login.security.UserPrincipal;
import com.polam.login.security.oauth2.user.OAuth2UserInfo;
import com.polam.login.security.oauth2.user.OAuth2UserInfoFactory;
import com.polam.login.util.ApplicationConstant;
import com.polam.login.util.AuthProvider;
import com.polam.login.util.UserIdUtil;

/**
 * The CustomOAuth2UserService extends Spring Security’s DefaultOAuth2UserService and implements its loadUser() method. 
 * This method is called after an access token is obtained from the OAuth2 provider.
 * In this method, we first fetch the user’s details from the OAuth2 provider. 
 * If a user with the same email already exists in our database then we update his details, otherwise, we register a new user.
 **/
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Value("${user-auth.generate-userid}")
	private boolean isUserIdCreationOn;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }
        List<String> roles = roleRepository.findByRolePkUserid(user.getId())
				.stream().map(r->r.getRolePk().getRole()).collect(Collectors.toList());
        return UserPrincipal.create(user, oAuth2User.getAttributes(), roles);
    }

    @Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        if(isUserIdCreationOn)
        	user.setId(UserIdUtil.createTemporaryUserId(oAuth2UserInfo.getName()));
        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        user = userRepository.save(user);
        //creating USER_ROLE by default
        RolePk rolePk = RolePk.builder().userid(user.getId()).role(ApplicationConstant.ROLE_USER).build();
        Role role = Role.builder().createdBy(ApplicationConstant.SELF).createdOn(new Timestamp(System.currentTimeMillis()))
        				.rolePk(rolePk).build();
        roleRepository.save(role);
        return user;
    }

    @Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

}