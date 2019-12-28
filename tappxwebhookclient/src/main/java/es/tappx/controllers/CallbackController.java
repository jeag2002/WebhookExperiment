package es.tappx.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.tappx.domain.Ad;
import es.tappx.domain.Webhook;
import es.tappx.domain.constants.EntityName;
import es.tappx.domain.constants.EventType;
import es.tappx.services.AdService;
import es.tappx.services.CallbackService;




@RestController
public class CallbackController {
	
	@Autowired
    ObjectMapper objectMapper;
	
	
	@Autowired
	CallbackService cS;
	
	private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);
	
	@RequestMapping(method = RequestMethod.POST, value = "callback", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void webhookCallbackHandler(HttpEntity<String> httpEntity) throws IOException {
		
		LOG.info("[CALLBACKCONTROLLER] GET callback entity_name (" + httpEntity.getHeaders().getFirst("webhook-entity-name") + ")"
				+ " event_type (" + httpEntity.getHeaders().getFirst("webhook-event-type") + ")"
				+ " event_id (" +  httpEntity.getHeaders().getFirst("webhook-id") + ")");
		
		try {
			process(httpEntity.getHeaders().getFirst("webhook-entity-name"), httpEntity.getHeaders().getFirst("webhook-event-type"),httpEntity.getBody());
		}catch(Exception e) {
			LOG.warn("[CALLBACKCONTROLLER] something happened (" + e.getMessage() + ")");
		}
		
    }
	
	
	
	private void process(String entityName, String entityEvent, String body) throws Exception{
		if ((entityEvent.equalsIgnoreCase(EventType.POSTINSERT.getValue())) || (entityEvent.equalsIgnoreCase(EventType.POSTUPDATE.getValue()))) {
			saveInLocal(entityName,body);
		}
	}
	
	
	private void saveInLocal(String entityName, String body) throws Exception {
		if (entityName.equalsIgnoreCase(EntityName.ADVERTISEMENT.getValue())) {
			Ad response = objectMapper.readValue(body, Ad.class);
			cS.save(response);	
		}
	}
	
	

}
