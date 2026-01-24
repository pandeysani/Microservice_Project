package com.example.ApiGateway.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiGateway.utils.JwtUtil;

@RestController
public class AuthController {

	@GetMapping("/auth/token")
	public Map<String, String> token(@RequestParam String user, @RequestParam String role) {

		String springRole = "ROLE_" + role.toUpperCase();

		String token = JwtUtil.generateToken(user, List.of(springRole));

		return Map.of("token", token);
	}
}
