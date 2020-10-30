package com.tesco.iam.appservice.service;

import java.util.ArrayList;
import java.util.List;

import com.tesco.iam.appservice.dto.PeopleDTO;

public class Test {

	public static void main(String[] s) {
		PeopleGraphServiceImpl ps = new PeopleGraphServiceImpl();
		List<PeopleDTO> peopleDTOList = new ArrayList<>();
		peopleDTOList.add(PeopleDTO.of("110", 1, "arun", "bhardwaj", "a@gmail.com", 23));
		peopleDTOList.add(PeopleDTO.of("220", 2, "arun", "bhardwaj", "a@gmail.com", 23));
		try {
			ps.bulkUpload(peopleDTOList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
