package com.fullstack.jugtours.config;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Makes Spring Security React-friendly


@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .oauth2Login().and()
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
            .authorizeRequests()
                .antMatchers("/**/*.{js,html,css}").permitAll()
                .antMatchers("/", "/api/user").permitAll()
                .anyRequest().authenticated();
    }

    /*
     Configuring CSRF (cross site request forgery) protection with CookieCsrfTokenRepository.withHttpOnlyFalse() 
     means that the XSRF-TOKEN cookie won’t be marked HTTP-only, so React can read it and send it back when it tries 
     to manipulate data.

     The antMatchers lines define what URLs are allowed for anonymous users. You will soon configure things so your 
     React app is served up by your Spring Boot app, hence the reason for allowing web files and “/”. 
     You might notice there’s an exposed /api/user path too. Create src/main/java/.../jugtours/web/UserController.java 
     and populate it with the following code. This API will be used by React to 1) find out if a user is authenticated, 
     and 2) perform global logout. 
     */
    
    /*
     The RequestCache bean overrides the default request cache. It saves the referrer header (misspelled referer in real life), 
     so Spring Security can redirect back to it after authentication. The referrer-based request cache comes in handy when
     you’re developing React on http://localhost:3000 and want to be redirected back there after logging in.
     */
    @Bean
    public RequestCache refererRequestCache() {
        return new HttpSessionRequestCache() {
            @Override
            public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
                String referrer = request.getHeader("referer");
                if (referrer != null) {
                    request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", new SimpleSavedRequest(referrer));
                }
            }
        };
    }
}