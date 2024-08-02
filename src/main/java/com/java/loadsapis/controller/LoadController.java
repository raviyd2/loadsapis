package com.java.loadsapis.controller;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.loadsapis.dto.LoadRequestDto;
import com.java.loadsapis.dto.LoadRequestUpdateDto;
import com.java.loadsapis.dto.ResponseDto;
import com.java.loadsapis.service.LoadService;
import com.java.loadsapis.utils.LoadUtils;


@RestController
@RequestMapping("/api")
public class LoadController {
	
	@Autowired
	private LoadService loadService;
	
	
	@PostMapping("/load")
	public ResponseEntity<?> addedLoadDetails(@RequestBody LoadRequestDto loadRequestDto){
		ResponseDto response=new ResponseDto();
		if(!StringUtils.hasLength(loadRequestDto.getLoadingPoint())) {
			String message="Loading point is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.hasLength(loadRequestDto.getUnloadingPoint())) {
			String message="Unloading point is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.hasLength(loadRequestDto.getProductType())) {
			String message="Product type is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.hasLength(loadRequestDto.getTruckType())) {
			String message="Truck type is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.hasLength(loadRequestDto.getShipperId())) {
			String message="Shipper id is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		boolean validateUUID=LoadUtils.validUUID(loadRequestDto.getShipperId());
		if(!validateUUID) {
			String message="Shipper id is invalid required UUID type!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(Objects.isNull(loadRequestDto.getNoOfTrucks())|| loadRequestDto.getNoOfTrucks()<=0) {
			String message="No of truck is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		} 
		if(Objects.isNull(loadRequestDto.getWeight())|| loadRequestDto.getWeight()<=0) {
			String message="Weight is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(Objects.isNull(loadRequestDto.getDate())) {
			String message="Date is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		ResponseDto saveStatus = loadService.saveLoadEntity(loadRequestDto);
		if(saveStatus.getMessage().equals("Success")) {
			String message="Load details added successfully";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}	
		return new ResponseEntity<>(saveStatus,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/load")
	public ResponseEntity<?> getLoadDetails(@RequestParam(required=false) String shipperId){
		ResponseDto response=loadService.getLoadDetails(shipperId);
		if(Objects.isNull(response.getData())) {
			String message="No data found!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/load/{loadId}")
	public ResponseEntity<?> getLoadDetailsByLoadId(@PathVariable Long loadId){
		ResponseDto response=loadService.getLoadDetailsByLoadId(loadId);
		if(Objects.isNull(response.getData())) {
			String message="No data found!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/load/{loadId}")
	public ResponseEntity<?> updateLoadDetails(@PathVariable Long loadId,@RequestBody LoadRequestUpdateDto loadRequestDto){
		ResponseDto response=new ResponseDto();
		if(Objects.isNull(loadId)) {
			String message="loadId is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.hasLength(loadRequestDto.getLoadingPoint())) {
			String message="Loading point is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.hasLength(loadRequestDto.getUnloadingPoint())) {
			String message="Unloading point is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.hasLength(loadRequestDto.getProductType())) {
			String message="Product type is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.hasLength(loadRequestDto.getTruckType())) {
			String message="Truck type is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		if(Objects.isNull(loadRequestDto.getNoOfTrucks())|| loadRequestDto.getNoOfTrucks()<=0) {
			String message="No of truck is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		} 
		if(Objects.isNull(loadRequestDto.getWeight())|| loadRequestDto.getWeight()<=0) {
			String message="Weight is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(Objects.isNull(loadRequestDto.getDate())) {
			String message="Date is required!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		ResponseDto updateData = loadService.updateLoadDetails(loadRequestDto,loadId);
		if(Objects.nonNull(updateData.getMessage()) ) {
		 
			return new ResponseEntity<>(updateData,HttpStatus.BAD_REQUEST);
		}	
		return new ResponseEntity<>(updateData,HttpStatus.OK);
	}
	
	@DeleteMapping("/load/{loadId}")
	public ResponseEntity<?> deleteLoadDetailsByLoadId(@PathVariable Long loadId){
		ResponseDto response=loadService.deleteLoadDetailsByLoadId(loadId);
		if(response.getMessage().equals("Success")){
			String message="Load details deleted successfully!";
			response.setMessage(message);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		String message="No data found!";
		response.setMessage(message);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
