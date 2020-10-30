package com.tesco.iam.appservice.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5066224924360045214L;

	private Date timestamp;

	private String id;
	
	private String message;
	
}
