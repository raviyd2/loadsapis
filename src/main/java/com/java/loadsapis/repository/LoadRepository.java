package com.java.loadsapis.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.java.loadsapis.entity.LoadEntity;

@Repository
public interface LoadRepository extends JpaRepository< LoadEntity , Long> {

	Optional<LoadEntity> findByShipperId(String shipperId);

	Optional<LoadEntity> findById(Long loadId);
	

}
