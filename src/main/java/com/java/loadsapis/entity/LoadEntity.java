package com.java.loadsapis.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name="load")
public class LoadEntity {
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="loading_point")
	private String loadingPoint;
	@Column(name="unloading_point")
	private String unloadingPoint;
	@Column(name="product_type")
	private String productType;
	@Column(name="truck_type")
	private String truckType;
	@Column(name="no_of_trucks")
	private Integer noOfTrucks;
	@Column(name="weight")
	private Integer weight;
	@Column(name="comment")
	private String comment;
    @Column(name="shipper_id")
	private String shipperId;
    @Column(name="date")
	private String date;
    @CreationTimestamp
    @Column(name="created_at")
	private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name="updated_at")
	private LocalDateTime updatedAt;
	

}
