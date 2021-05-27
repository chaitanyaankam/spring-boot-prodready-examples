package com.polam.login.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.netflix.appinfo.AmazonInfo;

@Profile(value={"stage","prod"})
@Configuration
@EnableEurekaClient
public class AWSConfiguration {
	
	@Value("${server.port}")
	private int port;
	
	private static final Logger logger = LoggerFactory.getLogger(AWSConfiguration.class);

	@Bean
	@Autowired
	public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
		logger.info("Loading AWS aware");
		EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
		config.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
		config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
		config.setNonSecurePort(port);
		config.setDataCenterInfo(info);
		return config;
	}
}
