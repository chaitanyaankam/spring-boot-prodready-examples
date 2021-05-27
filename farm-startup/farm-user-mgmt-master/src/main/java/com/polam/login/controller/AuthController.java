package com.polam.login.controller;

import java.net.URI;
import java.sql.Timestamp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.polam.login.entity.Role;
import com.polam.login.entity.RolePk;
import com.polam.login.entity.User;
import com.polam.login.exception.BadRequestException;
import com.polam.login.payload.ApiResponse;
import com.polam.login.payload.AuthResponse;
import com.polam.login.payload.LoginRequest;
import com.polam.login.payload.SignUpRequest;
import com.polam.login.repository.RoleRepository;
import com.polam.login.repository.UserRepository;
import com.polam.login.service.TokenProvider;
import com.polam.login.util.ApplicationConstant;
import com.polam.login.util.AuthProvider;
import com.polam.login.util.UserIdUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
@Api(value="Authentication management", description="Operations pertaining to Authentication management", produces=MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
	
	@Value("${user-auth.generate-userid}")
	private boolean isUserIdCreationOn;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    @ApiOperation(value = "User Authentication")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        User user = new User();
        if(isUserIdCreationOn)
        	user.setId(UserIdUtil.createTemporaryUserId(signUpRequest.getName()));
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userRepository.save(user);
        
        //creating USER_ROLE by default
        RolePk rolePk = RolePk.builder().userid(user.getId()).role(ApplicationConstant.ROLE_USER).build();
        Role role = Role.builder().createdBy(ApplicationConstant.SELF).createdOn(new Timestamp(System.currentTimeMillis()))
        				.rolePk(rolePk).build();
        roleRepository.save(role);
        
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully@"));
    }

}