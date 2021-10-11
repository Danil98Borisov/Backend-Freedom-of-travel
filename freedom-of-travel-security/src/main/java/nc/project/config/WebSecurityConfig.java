package nc.project.config;

import lombok.RequiredArgsConstructor;
import nc.project.jwt.AuthEntryPointJwt;
import nc.project.jwt.AuthTokenFilter;
import nc.project.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import static nc.project.const_enum.ERole.ROLE_ADMIN;
import static nc.project.const_enum.ERole.ROLE_ADVERTISER;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@CrossOrigin(origins = "http://localhost:4200")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/api/logout/**").permitAll()
                .antMatchers("/api/verify/**", "/api/verify-user-email/**").permitAll()
                .antMatchers("/api/hotel/**").permitAll()
                .antMatchers("/api/hotel/manage-hotel/**").hasAuthority(ROLE_ADVERTISER.toString())
                .antMatchers("/api/hotel/manage-hotel/all").hasAuthority(ROLE_ADMIN.toString())
                .antMatchers("/api/hotelPreview/**").permitAll()
                .antMatchers("/api/apartmentPreview/**").permitAll()
                .antMatchers("/api/apartment/details/**").permitAll()
                .antMatchers("/api/apartment/apart-in-hotel/**").permitAll()
                .antMatchers("/api/admin/**").hasAuthority(ROLE_ADMIN.toString())
                .antMatchers("/api/advertiser/**").hasAnyAuthority(ROLE_ADMIN.toString(), ROLE_ADVERTISER.toString())
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
