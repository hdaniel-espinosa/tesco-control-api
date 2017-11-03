package mx.edu.tecnologicodecoacalco.tescocontrolapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("mx.edu.tecnologicodecoacalco")
@SpringBootApplication(scanBasePackages= {"mx.edu.tecnologicodecoacalco"})
public class TescoControlApiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TescoControlApiApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TescoControlApiApplication.class);
	}
	
}
