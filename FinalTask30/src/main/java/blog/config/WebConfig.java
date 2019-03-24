package blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ServiceConfigurationError;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "blog")
public class WebConfig {
    
}
