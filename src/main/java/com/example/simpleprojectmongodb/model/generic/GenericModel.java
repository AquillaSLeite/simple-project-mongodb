package com.example.simpleprojectmongodb.model.generic;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class GenericModel implements Serializable {
	
	@Id @Getter
	private String id;
}
