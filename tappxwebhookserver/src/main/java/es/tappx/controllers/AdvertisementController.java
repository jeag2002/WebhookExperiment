package es.tappx.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.tappx.advertisement.hook.AdvertisementManager;
import es.tappx.domain.Ad;

@Controller
@RequestMapping("/adservice")
public class AdvertisementController {
	
	private final Logger LOG = LoggerFactory.getLogger(AdvertisementController.class);
	
	@Autowired
	AdvertisementManager manager;
	
	@RequestMapping(value="/save", method= RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public HttpEntity<Ad> insertAdvertisement(@RequestBody Ad input) {
		LOG.info("[ADVERTISEMENTCONTROLLER] insert new Advertisement [" + input.toString() +  "]");
		
		try {
			Ad response = manager.save(input);
			LOG.info("[ADVERTISEMENTCONTROLLER] response [" + response.toString() + "]");
			return new ResponseEntity<Ad>(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Ad>(new Ad(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value="/delete/{id}", method= RequestMethod.DELETE)
	public void deleteAdvertisement(@PathVariable Long id) {
		manager.delete(id);
	}
	
	
	

}
