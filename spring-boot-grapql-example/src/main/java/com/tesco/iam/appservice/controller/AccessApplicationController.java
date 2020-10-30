package com.tesco.iam.appservice.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesco.iam.appservice.domain.Account;
import com.tesco.iam.appservice.dto.AccountDTO;
import com.tesco.iam.appservice.dto.SuccessResponse;
import com.tesco.iam.appservice.repository.AccountRepository;
import com.tesco.iam.appservice.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/application")
@Slf4j
public class AccessApplicationController {

	@Autowired
	ApplicationRepository appRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping(value = "/account/{accountId}", produces =  {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Account> getApplications(@PathVariable String accountId) {
	
		Optional<Account> account = accountRepository.findById(accountId);
		
		log.info("account data : {}", account.get());
	
		return new ResponseEntity<Account>(account.get(), HttpStatus.OK);
	}
	
	@PostMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SuccessResponse> createAccount(@RequestBody AccountDTO accountDto) {
		Account account = new Account(accountDto.getAccountId(), accountDto.getAccountName());
		accountRepository.save(account);
		
		return new ResponseEntity<>(new SuccessResponse(new Date(),
				"123", "Request Created"), HttpStatus.CREATED);
	}
	
	

}
