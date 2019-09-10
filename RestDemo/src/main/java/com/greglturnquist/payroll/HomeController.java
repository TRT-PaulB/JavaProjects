package com.greglturnquist.payroll;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	// NOTE:  
	// Spring Boot’s autoconfigured view resolver will map to src/main/resources/templates/index.html.
	
	
}