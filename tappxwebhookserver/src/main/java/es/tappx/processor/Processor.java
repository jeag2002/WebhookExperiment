package es.tappx.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;

import es.tappx.controllers.WebhookController;
import es.tappx.domain.Webhook;

/**
 * 
 * @author jeag2
 *
 */

@Service
public class Processor {
	
	private AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
	
	private final Logger LOG = LoggerFactory.getLogger(Processor.class);
	
	@Async("threadPoolTaskExecutor")
    public void notifyAd(Webhook webhook, Object entity) {
        HttpHeaders httpHeaders = new HttpHeaders();
        
        httpHeaders.add("webhook-entity-name", webhook.getEntityName());
        httpHeaders.add("webhook-event-type", webhook.getEventType());
        httpHeaders.add("webhook-id",webhook.getIdwh().toString());
        
        LOG.info("[WEBHOOKPROCESSOR - "+Thread.currentThread().getName()+"] Notify entity-name: " + webhook.getEntityName() + " event-type: " + webhook.getEventType() + " to id: " + webhook.getIdwh());
        
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        asyncRestTemplate.exchange(webhook.getUrl(), HttpMethod.POST, new HttpEntity<>(entity, httpHeaders), Object.class);
    }
    
    
}
