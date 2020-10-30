package com.tesco.iam.appservice.repository;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import com.tesco.iam.appservice.domain.Application;

@Repository
public interface ApplicationRepository extends GremlinRepository<Application, String> {
	
	List<Application> findVertexByName(String applicationName);
	
}
