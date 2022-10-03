package tk.nikhil.ma.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ms01Controller {
	
	@GetMapping("/ms01")
	public String getMS01Message() {
		return "from MS01";
	}

}
