package it.colletta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"it.colletta.controller", "it.colletta.service"})
public class SweightApplication extends SpringBootServletInitializer {
  // Main
  public static void main(String[] args) {
    SpringApplication.run(SweightApplication.class, args);
  }
}
