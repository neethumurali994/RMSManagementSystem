package com.rmsMagement.RMSManagement.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmsMagement.RMSManagement.Entity.Rate;
import com.rmsMagement.RMSManagement.Repository.RateRepository;

@Service
public class RateService {

	@Autowired
	RateRepository raterepo;
	//search rate
	public Rate searchRate(long id) throws Exception {
		Rate searchrate = raterepo.findById(id).orElseThrow(() -> new Exception("Rate not found in rms") );
		return searchrate;
	}
	
	//add rate
	public Rate addRate(Rate rate)
	{
		Rate entity = raterepo.save(rate);
		if(entity==null)
		{
			throw new RuntimeException("Internal Server error. Please contact admin");
		}
		return entity;
	}
	
	//update rate
	public Rate updateRate(Rate rate, long id) throws Exception
	{
		Rate updaterate = raterepo.findById(id).orElseThrow(() -> new Exception("Rate not found in rms") );
		updaterate.setRateDescription(rate.getRateDescription());
		updaterate.setRateEffectiveDate(rate.getRateEffectiveDate());
		updaterate.setRateExpirationDate(rate.getRateExpirationDate());
		updaterate.setAmount(rate.getAmount());
		
		final Rate responseentity =  raterepo.save(updaterate);
		return responseentity;
	}
	
	//delete rate
	public Map<String, Boolean> deleteRate(long id) throws Exception
	{
		Rate deleterate = raterepo.findById(id).orElseThrow(() -> new Exception("Rate not found in rms") );
		raterepo.delete(deleterate);
		Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	}
	
	public List<Rate> findAll()
	{
		return raterepo.findAll();
	}
	//For merge 2 json object
	public  JSONObject mergerJSONObjects(JSONObject json1, JSONObject json2) {
		JSONObject mergedJSON = new JSONObject();
		mergedJSON = new JSONObject(json1, JSONObject.getNames(json1));
		for (String crunchifyKey : JSONObject.getNames(json1)) {
			mergedJSON.put(crunchifyKey, json2.get(crunchifyKey));
		}
		return mergedJSON;
	}
  
}
