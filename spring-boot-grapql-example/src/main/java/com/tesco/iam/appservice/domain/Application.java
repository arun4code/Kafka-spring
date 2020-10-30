package com.tesco.iam.appservice.domain;

import org.springframework.data.annotation.Id;

import com.microsoft.spring.data.gremlin.annotation.Vertex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Vertex(label="Application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application {

	@Id
    private String id;
	
    private String name;
    
    private String owner;
    
}