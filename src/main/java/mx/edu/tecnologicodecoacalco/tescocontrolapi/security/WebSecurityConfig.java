package mx.edu.tecnologicodecoacalco.tescocontrolapi.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Value("${app.cors.allowed-origins:http://localhost:4200}")
	private List<String> allowedOrigins;

	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
		return manager;
	}

	/**
	 * Permite que la app web (Angular, en otro origen/puerto) llame a la
	 * API. Sin esto, el navegador manda un preflight OPTIONS que Spring
	 * Security rechaza con 401 antes de que se agreguen los encabezados
	 * de CORS, y la petición real nunca llega a dispararse.
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(allowedOrigins);
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	/**
	 * El lector NFC del laboratorio y los sensores ambientales son
	 * dispositivos sin sesión de usuario, así que sus endpoints se dejan
	 * abiertos. La documentación de Swagger también queda abierta para
	 * poder consultarla sin credenciales; las llamadas reales que se
	 * disparen desde "Try it out" sí exigen autenticación, igual que el
	 * resto de la API.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/tesco-control-api/acceso/validar").permitAll()
				.requestMatchers(HttpMethod.POST, "/tesco-control-api/estados-laboratorio").permitAll()
				.requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
				.anyRequest().authenticated())
			.csrf(csrf -> csrf.disable())
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}