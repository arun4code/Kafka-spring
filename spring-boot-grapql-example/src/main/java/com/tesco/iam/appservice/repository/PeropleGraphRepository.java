package com.tesco.iam.appservice.repository;

import org.springframework.stereotype.Repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import com.tesco.iam.appservice.domain.People;


@Repository
public interface PeropleGraphRepository extends GremlinRepository<People, String> {
	
}

