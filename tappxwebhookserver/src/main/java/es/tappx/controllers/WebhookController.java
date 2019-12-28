package es.tappx.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.tappx.domain.Webhook;
import es.tappx.webhook.hook.WebhookManager;

@Controller
@RequestMapping("/wkservice")
public class WebhookController {
	
	private final Logger LOG = LoggerFactory.getLogger(WebhookController.class);
	
	@Autowired
	WebhookManager manager;
	
	
	@RequestMapping(value="/insert", method= RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public HttpEntity<Webhook> insertWebHook(@RequestBody Webhook input) {
		LOG.info("[WEBHOOKCONTROLLER] INSERT [" + input.toString() +  "]");
		
		try {
			Webhook response = manager.save(input);
			LOG.info("[WEBHOOKCONTROLLER] RETURN [" + response+ "]");
			return new ResponseEntity<Webhook>(response, HttpStatus.OK);
		}catch(Exception e) {
			LOG.error("[WEBHOOKCONTROLLER] ERROR " + e.getMessage(),e);
			return new ResponseEntity<Webhook>(new Webhook(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value="/delete/{id}", method= RequestMethod.DELETE)
	public void deleteWebHook(@PathVariable String id) {
		LOG.info("[WEBHOOKCONTROLLER] DELETE WEBHOOK ID [" + id +  "]");
		manager.delete(id);
	}
	
	

}
