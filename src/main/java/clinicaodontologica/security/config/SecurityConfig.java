package clinicaodontologica.security.config;

import clinicaodontologica.security.service.AuthenticationService;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;
    private final BCryptPasswordEncoder encoder;
    private final JwtRequestFilter jwtRequestFilter;

    private final JwtAuthentication jwtAuthentication;

    public SecurityConfig(AuthenticationService authenticationService, BCryptPasswordEncoder encoder, JwtRequestFilter jwtRequestFilter, JwtAuthentication jwtAuthentication) {
        this.authenticationService = authenticationService;
        this.encoder = encoder;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtAuthentication = jwtAuthentication;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        final String ADMIN = "ADMIN";
        final String DENTIST = "DENTIST";
        final String PATIENT = "PATIENT";
        http.csrf().disable().authorizeRequests().
                antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/session/v1/signup").permitAll()
                .antMatchers("/session/v1/login").permitAll()
                .antMatchers("/odontologos/v1/**").hasAnyRole(ADMIN, DENTIST)
                .antMatchers("/pacientes/v1/**").hasAnyRole(ADMIN, PATIENT)
                .antMatchers("/turnos/v1/**").hasAnyRole(ADMIN, PATIENT)
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().exceptionHandling()
                .authenticationEntryPoint(jwtAuthentication)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(authenticationService).passwordEncoder(encoder);
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

}
