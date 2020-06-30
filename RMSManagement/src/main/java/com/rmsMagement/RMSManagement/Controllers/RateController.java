package com.rmsMagement.RMSManagement.Controllers;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.rmsMagement.RMSManagement.Entity.Rate;
import com.rmsMagement.RMSManagement.Services.RateService;

@RestController
@RequestMapping("/rms")
public class RateController {

	@Autowired
	RateService rateservice;

	@GetMapping("/rates")
	public List<Rate> getAllUsers() {
		return rateservice.findAll();
	}

	@GetMapping("/rate/{id}")
	public ResponseEntity<Rate> getUsersById(@PathVariable(value = "id") Long rateId) throws Exception {
		Rate rateById = rateservice.searchRate(rateId);
		final String uri = "https://surcharge.free.beeceptor.com/surcharge";

		// Commenting because The given URI is not opening . showing 429 error- Too many
		// requests.

		/*
		 * RestTemplate restTemplate = new RestTemplate(); String resultsurcharge =
		 * restTemplate.getForObject(uri, String.class);
		 * 
		 * Gson gson = new Gson(); String jsonStringRate = gson.toJson(rateById);
		 * 
		 * JSONObject inputJson1 = new JSONObject(resultsurcharge); JSONObject
		 * inputJson2 = new JSONObject(jsonStringRate);
		 * 
		 * JSONObject responseMergeResult = rateservice.mergerJSONObjects(inputJson1,
		 * inputJson2);
		 */

		return ResponseEntity.ok(rateById);

	}

	// add rate entity
	@PostMapping("/addrate")
	public Rate createUser(@RequestBody Rate rate) {
		return rateservice.addRate(rate);
	}

	// Update rate entity
	@PutMapping("/updaterates/{id}")
	public ResponseEntity<Rate> updateUser(@PathVariable(value = "id") Long rateId, @RequestBody Rate rateDetails)
			throws Exception {
		Rate updateRate = rateservice.updateRate(rateDetails, rateId);
		return ResponseEntity.ok(updateRate);

	}

	// Delete rate entity
	@DeleteMapping("/deleterate/{id}")
	public Map<String, Boolean> deleteRate(@PathVariable(value = "id") Long rateId) throws Exception {
		Map<String, Boolean> result = rateservice.deleteRate(rateId);
		return result;
	}

}
