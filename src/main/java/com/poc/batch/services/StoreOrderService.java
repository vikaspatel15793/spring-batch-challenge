package com.poc.batch.services;

import org.springframework.stereotype.Service;

@Service
public class StoreOrderService {

	public String getDummyById(Long id) {
		return "dummy";
	}

}
