package com.nareshit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {

	private Long custmerId;
	private Long bookId;
	private int rate;
	private String reviewText;
}
