package it.colletta;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"it.colletta.controller", "it.colletta.service","it.colletta.repository"})
public class SweightApplication extends SpringBootServletInitializer {
  private static final Logger logger = LogManager.getLogger(SweightApplication.class);

  // Main
  public static void main(String[] args) {
    SpringApplication.run(SweightApplication.class, args);
  }
}
