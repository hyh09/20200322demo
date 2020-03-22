package com.example.demo_sanm;

import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Controller
@SpringBootApplication
@EnableSwagger2
@ImportResource(locations = "classpath:spring.xml")
public class DemoSanmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSanmApplication.class, args);
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		DwrSpringServlet servlet = new DwrSpringServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet, "/dwr/*");
		registrationBean.addInitParameter("debug", "true");
		return registrationBean;
	}

}
