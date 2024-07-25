package com.nhnacademy.exam.hotel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nhnacademy.exam.hotel.controller.UserAuthzValidator;

@RestController
@RequestMapping("/api")
public class HotelReservationController {

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserAuthzValidator userAuthzValidator;

	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/reservations")
	public ResponseEntity<?> getReservations(@RequestHeader("Authorization") String token) {
		try {
			// Validate the token
			if (token == null || token.isEmpty()) {
				throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
			}

			// Extract and validate user ID
			Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
			if (!userAuthzValidator.isValid(userId)) {
				throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "User is not authorized");
			}

			// Call the hotel reservation API
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", token);
			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<String> response = restTemplate.exchange(
				"http://some-hotel-api-url/reservations",
				HttpMethod.GET,
				entity,
				String.class
			);

			return ResponseEntity.ok(response.getBody());
		} catch (HttpClientErrorException e) {
			// Handle token expiration or invalid token
			if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				String newToken = authService.loginAndGetToken("nhnacademy", "nhnacademy12345*");
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", "Bearer " + newToken);
				HttpEntity<String> entity = new HttpEntity<>(headers);

				ResponseEntity<String> response = restTemplate.exchange(
					"http://some-hotel-api-url/reservations",
					HttpMethod.GET,
					entity,
					String.class
				);

				return ResponseEntity.ok(response.getBody());
			} else {
				throw e;
			}
		}
	}
}
