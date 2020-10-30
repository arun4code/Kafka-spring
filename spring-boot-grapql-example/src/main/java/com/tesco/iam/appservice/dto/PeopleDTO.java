package com.tesco.iam.appservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PeopleDTO {

	private String id;
    private int peopleId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

}
