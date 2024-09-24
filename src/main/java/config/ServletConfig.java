package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebMvc

@ComponentScan(basePackages = {
        "exception",
        "controller",
        "board.controller",
        "member.controller"
})
public class ServletConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/resources/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**") // url이 /resources/로 시작하는 경로
                .addResourceLocations("/resources/");// webapp/resources/ 경로
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("/resources/assets/");

    }

}
