package com.tesco.iam.appservice.domain;

import com.microsoft.spring.data.gremlin.annotation.Vertex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;

@Vertex(label="Account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
    private String id;
	
    private String name;
    
    private String partition = "d8608501-7d0c-449b-82a1-6bd39ca70831";
    
    public Account(String id, String name) {
    	this.id= id;
    	this.name = name;
    }
       
}