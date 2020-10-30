package com.tesco.iam.appservice.domain;

import com.microsoft.spring.data.gremlin.annotation.Edge;
import com.microsoft.spring.data.gremlin.annotation.EdgeFrom;
import com.microsoft.spring.data.gremlin.annotation.EdgeTo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;

@Edge
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Relation {

    @Id
    private String id;

    private String name;

    @EdgeFrom
    private Account personFrom;
 
    @EdgeTo
    private Application applicationTo;

   
}