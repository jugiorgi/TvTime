package br.com.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.controller.LoginController;

@RestController
@RequestMapping(value = "/login")
public class LoginResource {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> listar() {

		LoginController login = new LoginController();
		ResponseEntity<Object> response = login.login();
		
		return response;
	}
}
