package com.cms.szy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.cms.szy.configuration.log.GwsLogger;

@SpringBootApplication
@ServletComponentScan
@ImportResource({"classpath:conf/redisConfig.xml"})
public class CMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(CMSApplication.class, args);
		GwsLogger.info("jason_cms manage server is started!");
	}
}
