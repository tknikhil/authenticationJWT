package tk.nikhil.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import tk.nikhil.model.LoginDTO;
import tk.nikhil.service.LoginService;

@Api(tags = "Login Controller", description = "Login Service", hidden = true)
@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	 @ApiImplicitParams({
	        @ApiImplicitParam(example = JsonFormat.LOGIN_DATA, name = "req", paramType = "body", dataTypeClass  = String.class)})
	@PostMapping("/login")
	public String authTokenGenerator(@RequestBody LoginDTO loginDTO){
		loginService.createAuthToken(loginDTO);
		return "save";
//		ResponseDTO responseDTO=
//		return new ResponseEntity<>(responseDTO,HttpStatus.OK);
	}
	

}
