package com.appsdelevloper.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdelevloper.app.ws.io.entity.AddressEntity;
import com.appsdelevloper.app.ws.io.entity.UserEntity;
import com.appsdelevloper.app.ws.io.repositories.AddressRepository;
import com.appsdelevloper.app.ws.io.repositories.UserRepository;
import com.appsdelevloper.app.ws.service.AddressService;
import com.appsdelevloper.app.ws.shared.dto.AddressDTO;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository; 
	
	@Override
	public List<AddressDTO> getAddresses(String userId) {
		
		List<AddressDTO> returnValue = new ArrayList<>();
		UserEntity userEntity = userRepository.findByUserId(userId);
		ModelMapper modelMapper = new ModelMapper();
		
		if(userEntity == null) return returnValue;
		
		Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
		
		for(AddressEntity addressEntity:addresses) {
			returnValue.add( modelMapper.map(addressEntity, AddressDTO.class) );
		}
		
		return returnValue;
	}

	@Override
	public AddressDTO getAddress(String addressId) {
		AddressDTO returnValue = null;
		
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		
		if(addressEntity != null) {
			returnValue = new ModelMapper().map(addressEntity, AddressDTO.class);
		}
		
		return returnValue;
	}

}
