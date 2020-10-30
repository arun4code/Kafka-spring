package com.tesco.iam.appservice.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.microsoft.spring.data.gremlin.annotation.Vertex;
import com.tesco.iam.appservice.dto.PeopleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Vertex(label="people")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String age;
    
    private String email;

    private String partition = "d8608501-7d0c-449b-82a1-6bd39ca70831";
    
	public static People of(PeopleDTO peopleDTO) {
		People people = new People();
		people.setAge(String.valueOf((peopleDTO.getAge())));
		people.setEmail(peopleDTO.getEmail());
		people.setFirstName(peopleDTO.getFirstName());
		people.setLastName(peopleDTO.getLastName());
		people.setId(String.valueOf(peopleDTO.getPeopleId()));
		return people;
	}

	public static List<People> of(List<PeopleDTO> peopleDTO) {
		List<People> poepleList = new ArrayList<>();
		peopleDTO.stream().forEach(item -> {
			poepleList.add(People.of(item));
		});
		return poepleList;
	}
}

