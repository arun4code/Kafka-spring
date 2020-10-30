package com.tesco.iam.appservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesco.iam.appservice.domain.People;
import com.tesco.iam.appservice.dto.PeopleDTO;
import com.tesco.iam.appservice.repository.PeropleGraphRepository;
import com.tesco.iam.appservice.service.PeopleGraphService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/application")
@Slf4j
public class PeopleContorller {

	@Autowired
	PeropleGraphRepository repo;
	
	@Autowired
	PeopleGraphService service;
	
	
	@GetMapping(value = "/people/{peopleId}", produces =  {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<People> getPeople(@PathVariable String peopleId) {
	
		Optional<People> people = repo.findById(peopleId);
		
		log.info("people data : {}", people.get());
	
		return new ResponseEntity<People>(people.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/peopleCount", produces =  {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> getPeopleCount() {
	
		long count = repo.count();;
	
		return new ResponseEntity<String>("Total Count : " + count, HttpStatus.OK);
	}
	
	@PostMapping(value = "/saveData", produces = { MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> createAccount(@RequestBody PeopleDTO peopleDTO) {
		People people = People.of(peopleDTO);
		repo.save(people);
		
		return new ResponseEntity<>("Created", HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/savePeopleData", produces = { MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> createPeople(@RequestBody List<PeopleDTO> peopleDTOList) {
		//List<People> peopleList = People.of(peopleDTOList);
		//repo.saveAll(peopleList);
		
		try {
			service.bulkUpload(peopleDTOList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("Created", HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/deletePeopleData")
	public ResponseEntity<String> deletePeople() {
		repo.deleteAll();
		
		return new ResponseEntity<>("deleted", HttpStatus.ACCEPTED);
	}

}
