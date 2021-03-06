package com.sparta.toysrus.config;

import com.google.common.collect.ImmutableList;
import com.sparta.toysrus.security.FilterSkipMatcher;
import com.sparta.toysrus.security.FormLoginSuccessHandler;
import com.sparta.toysrus.security.filter.FormLoginFilter;
import com.sparta.toysrus.security.filter.JwtAuthFilter;
import com.sparta.toysrus.security.jwt.HeaderTokenExtractor;
import com.sparta.toysrus.security.provider.FormLoginAuthProvider;
import com.sparta.toysrus.security.provider.JWTAuthProvider;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTAuthProvider jwtAuthProvider;
    private final HeaderTokenExtractor headerTokenExtractor;
//    private final CorsFilter corsFilter;


    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(formLoginAuthProvider())
                .authenticationProvider(jwtAuthProvider);
    }

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**")
                .antMatchers("/api/auth/signup")
                .antMatchers("/api/auth/login")
                .antMatchers("/api/crawling")
                .antMatchers("/api/item/**")
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "**/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/health"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors();
//                .addFilterBefore(corsFilter, SecurityContextPersistenceFilter.class);
        // 아래 코드는 실패해서 삭제하고 위에 and(); 붙여줌
//

        // 서버에서 인증은 JWT로 인증하기 때문에 Session의 생성을 막습니다.
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /*
         * 1.
         * UsernamePasswordAuthenticationFilter 이전에 FormLoginFilter, JwtFilter 를 등록합니다.
         * FormLoginFilter : 로그인 인증을 실시합니다.
         * JwtFilter       : 서버에 접근시 JWT 확인 후 인증을 실시합니다.
         */
        http
                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/v2/api-docs",
                        "/swagger-resources/**",
                        "**/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/health").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/auth").permitAll()
                .antMatchers("/api/crawling").permitAll()
                .antMatchers("/api/item/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // [로그아웃 기능]
                .logout()
                // 로그아웃 요청 처리 URL
                .logoutUrl("/user/logout")
                .permitAll()
                .and()
                .exceptionHandling();
    }

    @Bean
    public FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter formLoginFilter = new FormLoginFilter(authenticationManager());
        formLoginFilter.setFilterProcessesUrl("/api/auth/login");
        formLoginFilter.setAuthenticationSuccessHandler(formLoginSuccessHandler());
        formLoginFilter.afterPropertiesSet();
        return formLoginFilter;
    }

    @Bean
    public FormLoginSuccessHandler formLoginSuccessHandler() {
        return new FormLoginSuccessHandler();
    }

    @Bean
    public FormLoginAuthProvider formLoginAuthProvider() {
        return new FormLoginAuthProvider(encodePassword());
    }

    private JwtAuthFilter jwtFilter() throws Exception {
        List<String> skipPathList = new ArrayList<>();

        // h2-console 허용
        skipPathList.add("GET,/h2-console/**");
        skipPathList.add("POST,/h2-console/**");
        // 회원 관리 API 허용
        skipPathList.add("GET, /api/**");
        skipPathList.add("POST, /api/**");
        skipPathList.add("POST, /api/auth/signup");
        skipPathList.add("POST, /api/auth/login");

        //상품관련 API허용
        skipPathList.add("POST, /api/crawling");
        skipPathList.add("POST, /api/item/**");
        skipPathList.add("GET, /api/item/**");

        // Swagger
        skipPathList.add("GET, /swagger-ui.html");
        skipPathList.add("GET, /swagger/**");
        skipPathList.add("GET, /swagger-resources/**");
        skipPathList.add("GET, /webjars/**");
        skipPathList.add("GET, /v2/api-docs");

        skipPathList.add("POST, /swagger-ui.html");
        skipPathList.add("POST, /swagger/**");
        skipPathList.add("POST, /swagger-resources/**");
        skipPathList.add("POST, /webjars/**");
        skipPathList.add("POST, /v2/api-docs");

        // 기본 허용 사항들
        skipPathList.add("GET,/");
        skipPathList.add("GET,/favicon.ico");

        FilterSkipMatcher matcher = new FilterSkipMatcher(
                skipPathList,
                "/**"
        );

        JwtAuthFilter filter = new JwtAuthFilter(
                matcher,
                headerTokenExtractor
        );
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

      // 이 방법이 안돼서 run 파일에다 설정 추가
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOrigins(ImmutableList.of("*"));
//        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
//        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
//        configuration.addAllowedHeader("*");
//        configuration.addExposedHeader("Authorization");
//        configuration.setAllowCredentials(true); // 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있도록 함
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}