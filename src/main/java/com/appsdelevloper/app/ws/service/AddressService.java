package com.appsdelevloper.app.ws.service;

import java.util.List;

import com.appsdelevloper.app.ws.shared.dto.AddressDTO;

public interface AddressService {
	List<AddressDTO> getAddresses(String userId);
}
