package mx.edu.tecnologicodecoacalco.tescocontrolapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	private static final String BASIC_AUTH = "basicAuth";

	@Bean
	public OpenAPI tescoControlApiOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Tesco Control API")
						.description("Control de acceso a laboratorios de cómputo con tarjeta NFC")
						.version("v1"))
				.addSecurityItem(new SecurityRequirement().addList(BASIC_AUTH))
				.schemaRequirement(BASIC_AUTH,
						new SecurityScheme().name(BASIC_AUTH).type(SecurityScheme.Type.HTTP).scheme("basic"));
	}
}
