package com.cms.szy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.cms.szy.configuration.log.GwsLogger;

@SpringBootApplication
@ServletComponentScan
public class CMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(CMSApplication.class, args);
		GwsLogger.info("jason_cms manage server is started!");
	}
}
