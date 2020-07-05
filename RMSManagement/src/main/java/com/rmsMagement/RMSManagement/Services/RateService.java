package com.rmsMagement.RMSManagement.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rmsMagement.RMSManagement.Entity.Rate;
import com.rmsMagement.RMSManagement.Repository.RateRepository;

@Service
public class RateService {

	@Autowired
	RateRepository raterepo;

	// search rate
	@HystrixCommand(fallbackMethod = "callSearch_Fallback", commandKey = "search", groupKey = "search")
	public Rate searchRate(long id) throws Exception {
		Rate searchrate = raterepo.findById(id).orElseThrow(() -> new Exception("Rate not found in rms"));
		return searchrate;
	}

	// Hystrix fallback method implementation
	@SuppressWarnings("unused")
	private Rate callSearch_Fallback(long rateId) {
		System.out.println("search Service is down!!!! fallback route enabled");
		return new Rate(3, "search Service is down!!!! fallback route enabled", new Date(), new Date(), 0);

	}

	// add rate
	@HystrixCommand(fallbackMethod = "calladd_Fallback", commandKey = "add", groupKey = "add")
	public Rate addRate(Rate rate) {
		Rate entity = raterepo.save(rate);
		if (entity == null) {
			throw new RuntimeException("Internal Server error. Please contact admin");
		}
		return entity;
	}

	// Hystrix fallback method implementation
	@SuppressWarnings("unused")
	private Rate calladd_Fallback(Rate rate) {
		System.out.println("add Service is down!!!! fallback route enabled");
		return new Rate(3, "add Service is down!!!! fallback route enable", new Date(), new Date(), 0);

	}

	// update rate
	public Rate updateRate(Rate rate, long id) throws Exception {
		Rate updaterate = raterepo.findById(id).orElseThrow(() -> new Exception("Rate not found in rms"));
		updaterate.setRateDescription(rate.getRateDescription());
		updaterate.setRateEffectiveDate(rate.getRateEffectiveDate());
		updaterate.setRateExpirationDate(rate.getRateExpirationDate());
		updaterate.setAmount(rate.getAmount());

		final Rate responseentity = raterepo.save(updaterate);
		return responseentity;
	}

	// delete rate
	@HystrixCommand(fallbackMethod = "calldelete_Fallback", commandKey = "findall", groupKey = "findall")
	public Map<String, Boolean> deleteRate(long id) throws Exception {
		Rate deleterate = raterepo.findById(id).orElseThrow(() -> new Exception("Rate not found in rms"));
		raterepo.delete(deleterate);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	// findall
	@HystrixCommand(fallbackMethod = "callfindAll_Fallback", commandKey = "findall", groupKey = "findall")
	public List<Rate> findAll() {

		return raterepo.findAll();
	}

	@SuppressWarnings("unused")
	private List<Rate> callfindAll_Fallback() {
		System.out.println("findall Service is down!!!! fallback route enabled");
		Rate fallrate = new Rate(3, "findall Service is down!!!! fallback route enabled", new Date(), new Date(), 0);
		List<Rate> rates = new ArrayList<Rate>();
		rates.add(fallrate);
		return rates;
	}

}
