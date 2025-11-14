package com.nareshit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nareshit.entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Long> {

}
