package tk.nikhil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.nikhil.model.LoginDTO;
import tk.nikhil.repo.LoginRepository;
@Service
public class LoginService {
	@Autowired
	private LoginRepository loginRepository;
	public LoginDTO createAuthToken(LoginDTO req) {
		String username=req.getUsername();
		String pwd =req.getPassword();
		return loginRepository.save(req);
	}
}
