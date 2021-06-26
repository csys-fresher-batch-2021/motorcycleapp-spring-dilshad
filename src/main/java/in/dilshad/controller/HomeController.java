package in.dilshad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * To go to Home page of this website
 *
 * @author dils2654
 *
 */
@Controller
public class HomeController {

	/**
	 * Goes to the Index page of the website
	 *
	 * url: http://localhost:9000/
	 *
	 * @return
	 */
	@GetMapping
	public String home() {
		System.out.println("Index page");
		return "redirect:index.jsp";
	}
}
