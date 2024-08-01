package com.java.loadsapis.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.java.loadsapis.dto.LoadRequestDto;
import com.java.loadsapis.dto.LoadRequestUpdateDto;
import com.java.loadsapis.dto.LoadResponseDto;
import com.java.loadsapis.dto.ResponseDto;
import com.java.loadsapis.entity.LoadEntity;
import com.java.loadsapis.repository.LoadRepository;
import com.java.loadsapis.service.LoadService;

@Service
public class LoadServiceImpl implements LoadService{
	
	@Autowired
	private LoadRepository loadRepository;

	@Override
	public ResponseDto saveLoadEntity(LoadRequestDto loadRequestDto) {
		ResponseDto response=new ResponseDto();
		try {
		   Optional<LoadEntity> checkExistance=loadRepository.findByShipperId(loadRequestDto.getShipperId());
		   if(checkExistance.isPresent()) {
			   String message="Load details is already exist with the shipperId!";
			   response.setMessage(message);
			   return response;
		   }
		   LoadEntity loadEntity=setDataInLoadEntity(loadRequestDto);
		   LoadEntity saveData=loadRepository.save(loadEntity);
			if(Objects.nonNull(saveData)) {
				response.setMessage("Success");
				return response;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		response.setData("Failed");
		return response;
	}

	private LoadEntity setDataInLoadEntity(LoadRequestDto loadRequestDto) {
		 LoadEntity loadEntity=new LoadEntity();
			loadEntity.setComment(loadRequestDto.getComment());
			loadEntity.setCreatedAt(LocalDateTime.now());
			loadEntity.setDate(loadRequestDto.getDate());
			loadEntity.setLoadingPoint(loadRequestDto.getLoadingPoint());
			loadEntity.setUnloadingPoint(loadRequestDto.getUnloadingPoint());
			loadEntity.setProductType(loadRequestDto.getProductType());
			loadEntity.setTruckType(loadRequestDto.getTruckType());
			loadEntity.setNoOfTrucks(loadRequestDto.getNoOfTrucks());
			loadEntity.setWeight(loadRequestDto.getWeight());
			loadEntity.setShipperId(loadRequestDto.getShipperId());
		return loadEntity;
	}

	@Override
	public ResponseDto getLoadDetails(String shipperId) {
		ResponseDto response=new ResponseDto();
		try {
		  if(StringUtils.hasLength(shipperId)) {
		  Optional<LoadEntity> checkExistance=loadRepository.findByShipperId(shipperId);
		   if(checkExistance.isPresent()) {
			   LoadResponseDto loadEntity=setLoadDataInResponse(checkExistance.get());
			   response.setData(loadEntity);
			   return response;
		   }
		  }
		  List<LoadEntity> loadlist=loadRepository.findAll();
		  if(!loadlist.isEmpty()) {
		  List<LoadResponseDto> loadEntityList=setLoadDataInResponseList(loadlist);
		  response.setData(loadEntityList);
		  return response;
		  }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	private List<LoadResponseDto> setLoadDataInResponseList(List<LoadEntity> loadlist) {
		List<LoadResponseDto> loadListResponse=new ArrayList<LoadResponseDto>();
		if(!loadlist.isEmpty()) {
			loadlist.forEach(load->{
				LoadResponseDto responseDto=new LoadResponseDto();
				responseDto.setComment(load.getComment());
				responseDto.setDate(load.getDate());
				responseDto.setLoadId(load.getId());
				responseDto.setLoadingPoint(load.getLoadingPoint());
				responseDto.setNoOfTrucks(load.getNoOfTrucks());
				responseDto.setProductType(load.getProductType());
				responseDto.setShipperId(load.getShipperId());
				responseDto.setTruckType(load.getTruckType());
				responseDto.setUnloadingPoint(load.getUnloadingPoint());
				responseDto.setWeight(load.getWeight());
				loadListResponse.add(responseDto);
			});
		}
		return loadListResponse;
	}

	private LoadResponseDto setLoadDataInResponse(LoadEntity loadEntity) {
		LoadResponseDto responseDto=new LoadResponseDto();
			responseDto.setComment(loadEntity.getComment());
			responseDto.setDate(loadEntity.getDate());
			responseDto.setLoadId(loadEntity.getId());
			responseDto.setLoadingPoint(loadEntity.getLoadingPoint());
			responseDto.setNoOfTrucks(loadEntity.getNoOfTrucks());
			responseDto.setProductType(loadEntity.getProductType());
			responseDto.setShipperId(loadEntity.getShipperId());
			responseDto.setTruckType(loadEntity.getTruckType());
			responseDto.setUnloadingPoint(loadEntity.getUnloadingPoint());
			responseDto.setWeight(loadEntity.getWeight());
		return responseDto;
	}

	@Override
	public ResponseDto getLoadDetailsByLoadId(Long loadId) {
		ResponseDto response=new ResponseDto();
		try {
			 Optional<LoadEntity> loadEntity=loadRepository.findById(loadId);
			 if(loadEntity.isPresent()) {
				   LoadResponseDto loadResponse=setLoadDataInResponse(loadEntity.get());
				   response.setData(loadResponse);
				   return response;
			   }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	@Override
	public ResponseDto updateLoadDetails(LoadRequestUpdateDto loadRequestDto,Long loadId) {
		ResponseDto response=new ResponseDto();
		try {
			Optional<LoadEntity> loadEntity=loadRepository.findById(loadId);
			 if(!loadEntity.isPresent()) {
				  String message="Invalid loadId!";
				  response.setMessage(message);
				  return response;
			 }
			 loadEntity.get().setComment(loadRequestDto.getComment());
			 loadEntity.get().setComment(loadRequestDto.getComment());
			 loadEntity.get().setCreatedAt(LocalDateTime.now());
			 loadEntity.get().setDate(loadRequestDto.getDate());
			 loadEntity.get().setLoadingPoint(loadRequestDto.getLoadingPoint());
			 loadEntity.get().setUnloadingPoint(loadRequestDto.getUnloadingPoint());
			 loadEntity.get().setProductType(loadRequestDto.getProductType());
			 loadEntity.get().setTruckType(loadRequestDto.getTruckType());
			 loadEntity.get().setNoOfTrucks(loadRequestDto.getNoOfTrucks());
			 loadEntity.get().setWeight(loadRequestDto.getWeight());
			 LoadEntity saveData=loadRepository.save(loadEntity.get());
			 if(Objects.nonNull(saveData)) {
				 LoadResponseDto loadEntityData=setLoadDataInResponse(saveData);
				 response.setData(loadEntityData);
				 return response;
			 }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	@Override
	public ResponseDto deleteLoadDetailsByLoadId(Long loadId) {
		ResponseDto response=new ResponseDto();
		try {
			 Optional<LoadEntity> loadEntity=loadRepository.findById(loadId);
			 if(loadEntity.isPresent()) {
				loadRepository.delete(loadEntity.get());
				String message="Load details deleted successfully!";
				response.setMessage(message);
				return response;
			   }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		 String message="Data doesn't exist!";
		 response.setMessage(message);
		 return response;
	}

}
