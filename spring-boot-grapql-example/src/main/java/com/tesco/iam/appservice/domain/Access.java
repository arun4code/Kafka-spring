package com.tesco.iam.appservice.domain;

import org.springframework.data.annotation.Id;

import com.microsoft.spring.data.gremlin.annotation.Vertex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Vertex(label="Access")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Access {

	@Id
    private String id;
	
    private String name;
    
    
}