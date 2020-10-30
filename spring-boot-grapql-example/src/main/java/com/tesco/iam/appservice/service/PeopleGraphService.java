package com.tesco.iam.appservice.service;

import java.util.List;

import com.tesco.iam.appservice.dto.PeopleDTO;

public interface PeopleGraphService {
	public void bulkUpload(List<PeopleDTO> peopleDTO) throws Exception;
}
