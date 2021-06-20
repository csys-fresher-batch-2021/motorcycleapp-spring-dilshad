package in.dilshad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// http://localhost:9000
	@GetMapping
	public String home() {
		return "index.jsp";
	}
}
