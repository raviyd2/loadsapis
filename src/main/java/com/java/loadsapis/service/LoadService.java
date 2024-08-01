package com.java.loadsapis.service;

import com.java.loadsapis.dto.LoadRequestDto;
import com.java.loadsapis.dto.LoadRequestUpdateDto;
import com.java.loadsapis.dto.ResponseDto;

public interface LoadService {

	ResponseDto saveLoadEntity(LoadRequestDto loadRequestDto);

	ResponseDto getLoadDetails(String shipperId);

	ResponseDto getLoadDetailsByLoadId(Long loadId);

	ResponseDto updateLoadDetails(LoadRequestUpdateDto loadRequestDto,Long loadId);

	ResponseDto deleteLoadDetailsByLoadId(Long loadId);


}
