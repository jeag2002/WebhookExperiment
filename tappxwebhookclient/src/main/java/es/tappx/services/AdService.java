package es.tappx.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.tappx.controllers.AdvertisementController;
import es.tappx.domain.Ad;



@Component
public class AdService {
	
	
	private final Logger LOG = LoggerFactory.getLogger(AdService.class);
	
	@Autowired
    ObjectMapper objectMapper;
	
	private static final String URL_SAVE = "http://localhost:8080/adservice/save";
	private static final String URL_DELETE = "http://localhost:8080/adservice/delete";
	
	
	public String saveAdvertisementToServer(Ad intro) {
		
		Ad ad = null;
		String responseAd = "";
		
		try {
		
			LOG.info("[ADSERVICE] ADSERVICE SAVE");
			
			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			String json = objectMapper.writeValueAsString(intro);
			
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> entity = new HttpEntity<String>(json,headers);
			
			ResponseEntity<Ad> response = rest.exchange(URL_SAVE, HttpMethod.POST, entity, Ad.class);
			
			if (response.getStatusCode().is2xxSuccessful()) {
				
				ad = response.getBody();
				LOG.info("[ADSERVICE] STATUS (" + response.getStatusCodeValue() + ") RESPONSE (" + ad + ")");
			 
				responseAd = "Advertisement processed successfully";
				
			}else {
				throw new Exception("HTTP 500 Error");
			}
		
		}catch(Exception e) {
			LOG.warn("[ADSERVICE] error (" + e.getMessage() + ")");
			responseAd = e.getMessage();
		}finally {
			return responseAd;
		}
		
		
	}
	
	public void deleteAdvertisementToServer(Long id) {
		
		try {
			
			LOG.info("[ADSERVICE] ADSERVICE DELETE");
			
			RestTemplate rest = new RestTemplate();
			rest.delete(URL_DELETE+"/"+String.valueOf(id));
			
		}catch(Exception e) {
			LOG.warn("[ADSERVICE] error (" + e.getMessage() + ")");
		}
		
	}
	
	
}
