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

import es.tappx.domain.Ad;
import es.tappx.services.AdService;

@Controller
@RequestMapping("/adservice")
public class AdvertisementController {

	private final Logger LOG = LoggerFactory.getLogger(AdvertisementController.class);
	
	@Autowired
	AdService ad;
	
	
	@RequestMapping(value="/save", method= RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> saveAdvertisement(@RequestBody Ad input) {
		String response = ad.saveAdvertisementToServer(input);
		LOG.info("[ADVERTISEMENTCONTROLLER] RESPONSE (" + response + ")");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method= RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public void deleteAdvertisement(@PathVariable Long id) {
		ad.deleteAdvertisementToServer(id);
	}
}
