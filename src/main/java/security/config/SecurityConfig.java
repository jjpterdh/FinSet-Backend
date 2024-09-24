package security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import security.filter.AuthenticationErrorFilter;
import security.filter.JwtAuthenticationFilter;
import security.filter.JwtUsernamePasswordAuthenticationFilter;
import security.handler.CustomAccessDeniedHandler;
import security.handler.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@Log4j
@MapperScan(basePackages = {"security.account.mapper"})
@ComponentScan(basePackages = {"security"})
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    @Autowired
    private JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationErrorFilter authenticationErrorFilter;

    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // PasswordEncoder의 구현체
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();


        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);

        config.addAllowedOriginPattern("*");

        config.addAllowedHeader("*");

        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/assets/**","/*","/api/member/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.addFilterBefore(encodingFilter(), CsrfFilter.class)

                .addFilterBefore(authenticationErrorFilter, UsernamePasswordAuthenticationFilter.class)

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .addFilterBefore(jwtUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        http.exceptionHandling()

                .authenticationEntryPoint(authenticationEntryPoint)

                .accessDeniedHandler(accessDeniedHandler);

        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.POST,"/api/member").authenticated()
                .antMatchers(HttpMethod.PUT,"/api/member/","api/member/*/changepassword").authenticated()// 모든 OPTIONS 요청 허용
                .antMatchers(HttpMethod.POST,"api/board/**").authenticated()
                .antMatchers(HttpMethod.PUT,"/api/board/**").authenticated()
                .antMatchers(HttpMethod.DELETE,"/api/board/**").authenticated()
                .anyRequest().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();


        encodingFilter.setEncoding("UTF-8");

        encodingFilter.setForceEncoding(true);

        return encodingFilter;


    }
}