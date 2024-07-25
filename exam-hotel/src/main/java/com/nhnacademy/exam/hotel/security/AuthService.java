package com.nhnacademy.exam.hotel.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

	private final String LOGIN_URL = "http://133.186.241.167:8200/login";

	public String loginAndGetToken(String username, String password) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> request = new HashMap<>();
		request.put("username", username);
		request.put("password", password);

		ResponseEntity<Map> response = restTemplate.postForEntity(LOGIN_URL, request, Map.class);
		return (String) response.getBody().get("access_token");
	}
}
