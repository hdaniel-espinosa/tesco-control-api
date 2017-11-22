package mx.edu.tecnologicodecoacalco.tescocontrolapi.security;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcWebApplicationInitializer extends
AbstractAnnotationConfigDispatcherServletInitializer {

@Override
protected Class<?>[] getRootConfigClasses() {
return new Class[] { WebSecurityConfig.class };
}

@Override
protected Class<?>[] getServletConfigClasses() {
	// TODO Auto-generated method stub
	return null;
}

@Override
protected String[] getServletMappings() {
	// TODO Auto-generated method stub
	return null;
}

// ... other overrides ...
}