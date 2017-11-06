package mx.edu.tecnologicodecoacalco.tescocontrolapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"mx.edu.tecnologicodecoacalco.tescocontrolapi"})
public class TescoControlApiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TescoControlApiApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TescoControlApiApplication.class);
	}
	
}
