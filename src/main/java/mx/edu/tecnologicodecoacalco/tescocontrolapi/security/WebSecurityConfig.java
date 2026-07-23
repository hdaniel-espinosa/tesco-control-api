package mx.edu.tecnologicodecoacalco.tescocontrolapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
		return manager;
	}

	/**
	 * El lector NFC del laboratorio y los sensores ambientales son
	 * dispositivos sin sesión de usuario, así que sus endpoints se dejan
	 * abiertos; el resto de la API requiere autenticación.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.POST, "/tesco-control-api/acceso/validar").permitAll()
				.requestMatchers(HttpMethod.POST, "/tesco-control-api/estados-laboratorio").permitAll()
				.anyRequest().authenticated())
			.csrf(csrf -> csrf.disable())
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}