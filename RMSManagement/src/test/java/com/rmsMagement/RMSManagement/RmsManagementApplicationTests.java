package com.rmsMagement.RMSManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.rmsMagement.RMSManagement.Entity.Rate;

@SpringBootTest
class RmsManagementApplicationTests {

 @Autowired
 private TestRestTemplate restTemplate;
 @LocalServerPort
 private int port;
 private String getRootUrl() {
 return "http://localhost:" + port;
 }
	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllRates() {
	HttpHeaders headers = new HttpHeaders();
	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	ResponseEntity<String> response = restTemplate.exchange( getRootUrl()+ "/rates",
	HttpMethod.GET, entity, String.class);
	assertNotNull(response.getBody());
	}
	
	@Test
	public void testSearchById() {
	Rate rate = restTemplate.getForObject(getRootUrl() + "/rates/1", Rate.class);
	System.out.println(rate.getAmount());
	assertNotNull(rate);
	}
	
	@Test
	public void testAddRate() {
	Rate rate = new Rate();
	rate.setRateDescription("abc");
	rate.setRateEffectiveDate(new Date());
	rate.setRateExpirationDate(new Date());
	rate.setAmount(100);
	ResponseEntity<Rate> postResponse = restTemplate.postForEntity(getRootUrl() + "/addrate", rate, Rate.class);
	assertNotNull(postResponse);
	}
	
	@Test
	public void testUpdateRate() {
	int id = 1;
	Rate rate = restTemplate.getForObject(getRootUrl() + "/updaterates/" + id, Rate.class);
	rate.setAmount(1000);
	rate.setRateDescription("abcdef");
	restTemplate.put(getRootUrl() + "/updaterates/" + id, rate);
	Rate updatedRate = restTemplate.getForObject(getRootUrl() + "/updaterates/" + id, Rate.class);
	assertNotNull(updatedRate);
	}
	
	@Test
	public void testDeleteRate() {
	int id = 2;
	Rate rate = restTemplate.getForObject(getRootUrl() + "/deleterate/" + id, Rate.class);
	assertNotNull(rate);
	restTemplate.delete(getRootUrl() + "/deleterate/" + id);
	try {
	rate = restTemplate.getForObject(getRootUrl() + "/deleterate/" + id, Rate.class);
	} catch (final HttpClientErrorException e) {
	assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	}
}
