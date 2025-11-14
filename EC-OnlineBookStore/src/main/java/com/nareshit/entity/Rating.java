package com.nareshit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ratings") // it is optional
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int rate;
	private String reviewText;
	
	@ManyToMany
	@JoinColumn(name = "book_id",updatable = false)
	private BooksModule booksModule;
	
	@ManyToMany
	@JoinColumn(name = "custmer_id",updatable = false)
	private Customer customer;
}
