package br.com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.domain.Login;
import br.com.utils.Propriedade;
import br.com.utils.Propriedade.EnumPropriedade;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginController {
	
	public ResponseEntity<Object> login(){
		Map<String, Object> response = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;
		ObjectMapper objectMapper = new ObjectMapper();
		
		final Propriedade propEnd = new Propriedade(EnumPropriedade.Endpoint);
		final Propriedade propAuth = new Propriedade(EnumPropriedade.Auth);
		Login body = new Login();

		try {
		
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		final Integer timeout = new Integer(propEnd.getValor("timeout"));
		final RestTemplate restTemplate = new RestTemplate();
		((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(timeout);
		((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(timeout);
		
		body.setApiKey(propAuth.getValor("apiKey"));
		body.setUserKey(propAuth.getValor("userKey"));
		body.setUsername(propAuth.getValor("user"));
		
		final String requestObj = new Gson().toJson(body);

		final HttpEntity<String> requestBody = new HttpEntity<String>(requestObj, headers);
		StringBuffer sb = new StringBuffer(propEnd.getValor("login"));
		final ResponseEntity<String> resp = restTemplate.exchange(sb.toString(), HttpMethod.POST, requestBody,
				String.class);
		
		body = objectMapper.readValue(resp.getBody().toString(), Login.class);
		
		response.put("token", body.getToken());
		
		}catch(Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println(e.getMessage());
		}
		
	    log.info("response {} ", response);
	    
		return ResponseEntity.status(httpStatus).body(response);
	}

}
