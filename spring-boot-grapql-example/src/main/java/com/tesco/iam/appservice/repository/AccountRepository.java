package com.tesco.iam.appservice.repository;



import org.springframework.stereotype.Repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import com.tesco.iam.appservice.domain.Account;

@Repository
public interface AccountRepository extends GremlinRepository<Account, String> {
	
}
