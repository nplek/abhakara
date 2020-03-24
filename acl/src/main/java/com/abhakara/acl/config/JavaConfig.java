package com.abhakara.acl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecurityConfig.class})
public class JavaConfig {

}