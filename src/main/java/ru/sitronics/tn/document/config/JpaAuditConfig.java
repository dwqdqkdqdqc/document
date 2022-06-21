package ru.sitronics.tn.document.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
// https://stackoverflow.com/questions/40370709/createddate-annotation-does-not-work-with-mysql
// https://stackoverflow.com/questions/60606861/spring-boot-jpa-metamodel-must-not-be-empty-when-trying-to-run-junit-integrat/60608499#:~:text=You%20need%20to%20move%20%40EnableJpaAuditing%20annotation
public class JpaAuditConfig {
}
