package es.tappx.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import es.tappx.domain.Webhook;
import es.tappx.domain.constants.EntityName;
import es.tappx.domain.constants.EventType;
import es.tappx.services.CallbackService;

@Configuration
public class WebhookConfig {
	
	
	private final Logger LOG = LoggerFactory.getLogger( WebhookConfig.class);

	final String uri = "http://localhost:8080/wkservice/insert";
	
	@Bean(name = "app-objectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new Jdk8Module());
    }	
	
	
	@Bean
	@Qualifier("whinsert")
	public Webhook setWebHookPostInsert() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Webhook webhook = new Webhook();
		
		webhook.setEntityName(EntityName.ADVERTISEMENT.getValue());
		webhook.setEventType(EventType.POSTINSERT.getValue());
		webhook.setUrl("http://localhost:8081/callback");
		//webhook.setIdwh("");
		
		ResponseEntity<Webhook> response = restTemplate.postForEntity(uri, webhook, Webhook.class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			LOG.info("[WEBHOOKCONFIG] WEBHOOK POST_INSERT ID [" + response.getBody().getIdwh() + "]");
			return response.getBody();
		}else {
			return new Webhook();
		}
	}
	
	
	@Bean
	@Qualifier("whupdate")
	public Webhook setWebHookPostUpdate() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Webhook webhook = new Webhook();
		
		webhook.setEntityName(EntityName.ADVERTISEMENT.getValue());
		webhook.setEventType(EventType.POSTUPDATE.getValue());
		webhook.setUrl("http://localhost:8081/callback");
		//webhook.setId("");
		
		ResponseEntity<Webhook> response = restTemplate.postForEntity(uri, webhook, Webhook.class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			LOG.info("[WEBHOOKCONFIG] WEBHOOK POST_UPDATE ID [" + response.getBody().getIdwh() + "]");
			return response.getBody();
		}else {
			return new Webhook();
		}
	}
	
	
	@Bean
	@Qualifier("whdelete")
	public Webhook setWebHookPostDelete() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Webhook webhook = new Webhook();
		
		webhook.setEntityName(EntityName.ADVERTISEMENT.getValue());
		webhook.setEventType(EventType.POSTDELETE.getValue());
		webhook.setUrl("http://localhost:8081/callback");
		//webhook.setId("");
		
		ResponseEntity<Webhook> response = restTemplate.postForEntity(uri, webhook, Webhook.class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			LOG.info("[WEBHOOKCONFIG] WEBHOOK POST_DELETE ID [" + response.getBody().getIdwh() + "]");
			return response.getBody();
		}else {
			return new Webhook();
		}
	}
	
}
