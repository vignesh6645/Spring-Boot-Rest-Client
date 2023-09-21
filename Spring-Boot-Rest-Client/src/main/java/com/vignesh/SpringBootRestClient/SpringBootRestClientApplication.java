package com.vignesh.SpringBootRestClient;

import com.vignesh.SpringBootRestClient.dto.UserDetails;
import com.vignesh.SpringBootRestClient.serviceImpl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class SpringBootRestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestClientApplication.class, args);
	}

	private final EmployeeServiceImpl employeeService;

	@PostMapping("/")
	public UserDetails saveUserDetails(@RequestBody UserDetails user) throws Exception {
		return employeeService.saveUserDetails(user);
	}
	@GetMapping("/")
	public List<UserDetails> getAllUserDetails() throws Exception {
		return employeeService.getAllUserDetails();
	}
	@GetMapping("/id")
	public String  getAllUserDetails(@RequestParam long id) throws Exception {
		return employeeService.deleteUserById(id);
	}

}
