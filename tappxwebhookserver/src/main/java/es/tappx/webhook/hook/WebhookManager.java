package es.tappx.webhook.hook;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import es.tappx.domain.Webhook;
import es.tappx.repositories.WebhookRepository;


@Component
public class WebhookManager {
	
	private final Logger LOG = LoggerFactory.getLogger(WebhookManager.class);
	
	public WebhookRepository webhookRepository;
	
	@Autowired
    public WebhookManager(WebhookRepository webhookRepository) {
        this.webhookRepository = webhookRepository;
    }
	
	 @Cacheable("webhooks")
	 public List<Webhook> findAll() {
	        return webhookRepository.findAll();
	 }
	 
	 public Webhook findOne(String _id) {
		 Optional<Webhook> opt = webhookRepository.findById(_id);
		 if (opt.isPresent()) {
			 return opt.get();
		 }else {
			 return null;
		 }
	 }
	 
	 @Cacheable("webhook_func")
	 public List<Webhook> retrieveWebhooksByEntityNameAndEventType(String entityName, String eventType) {
		 return webhookRepository.findByEntityNameAndEventType(entityName, eventType);
	 }
	 
	 public Webhook save(Webhook hook) throws Exception {
		 
		 try {
		 
			 UUID uuid = UUID.randomUUID();
			 hook.setIdwh(uuid.toString());
			 hook = webhookRepository.saveAndFlush(hook);
			 return hook;
		 
		 }catch(Exception e) {
			 
			 LOG.warn("[WEBHOOKMANAGER] cannot create new hook (" + e.getMessage() + ")");
			 throw new Exception("Something happened " + e.getMessage());
		 }
		 
	 }
	 
	 public void delete(String id) {
		 try {
			 webhookRepository.deleteById(id);
		 }catch(Exception e) {
			 LOG.warn("[WEBHOOKMANAGER] cannot delete hook (" + id + ")");
		 }
	 }
	 
	 
	
	

}
