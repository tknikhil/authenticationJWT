package tk.nikhil.ms.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MS02Controller {

	@GetMapping("/ms02")
	public String getMS02Message() {
		return "from MS02";
	}
}
