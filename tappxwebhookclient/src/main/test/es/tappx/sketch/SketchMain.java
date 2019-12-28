package es.tappx.sketch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.tappx.domain.Ad;
import es.tappx.domain.AdSize;

public class SketchMain {
	
	private static final String URL = "http://localhost:8081/adservice/save";

	public static void main(String[] args) throws Exception{
		
		Ad ad = new Ad();
		//ad.setIndex(0L);
		ad.setName("Increible advertisement");
		ad.setDesc("me lo quitan de las manos puri");
		ad.setSo("ANDROID");
		
		Set<AdSize> lAd = new HashSet<>();
		
		AdSize adL = new AdSize();
		//adL.setId(0L);;
		adL.setSize("320x50");
		adL.setUrl("https://www.zcivic.com/sites/www.zcivic.com/files/styles/promo/public/Natchez-website-badges-Mobile_Banner_320x50.png?itok=uqRuIzJt");
		//adL.setId_ad(0L);
		
		lAd.add(adL);
		ad.setSizesList(lAd);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String json = objectMapper.writeValueAsString(ad);
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<String>(json,headers);
		
		ResponseEntity<String> response = rest.exchange(URL, HttpMethod.POST, entity, String.class);
		
	
			
		String ad_response= response.getBody();
		System.out.println("[ADSERVICE] STATUS (" + response.getStatusCodeValue() + ") RESPONSE (" + ad_response + ")");
		
	
		
		

	}

}
