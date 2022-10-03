package com.ultimate.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class JWTResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final String jwtToken;

}
