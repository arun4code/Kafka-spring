package com.tesco.iam.appservice.repository;



import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import com.tesco.iam.appservice.domain.AppData;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDataRepository extends GremlinRepository<AppData, String> {
}
