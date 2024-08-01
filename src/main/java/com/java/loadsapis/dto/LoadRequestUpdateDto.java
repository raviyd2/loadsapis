package com.java.loadsapis.dto;

import lombok.Data;

@Data
public class LoadRequestUpdateDto {

	private String loadingPoint;
	private String unloadingPoint;
	private String productType;
	private String truckType;
	private Integer noOfTrucks;
	private Integer weight;
	private String comment;
	private String date;
}
