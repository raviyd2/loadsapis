package com.java.loadsapis.dto;

import lombok.Data;
@Data
public class LoadRequestDto {
	
	private String loadingPoint;
	private String unloadingPoint;
	private String productType;
	private String truckType;
	private Integer noOfTrucks;
	private Integer weight;
	private String comment;
	private String shipperId;
	private String date;

}
