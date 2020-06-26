package br.com.domain;

import lombok.Data;

@Data
public class Login {

	private String apiKey = "";
	private String userKey = "";
	private String username = "";

//	@JsonProperty("token")
	private String token = "";

	public Login() {
	}

}
