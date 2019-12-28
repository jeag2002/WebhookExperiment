package es.tappx.advertisement.listener.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import es.tappx.domain.Webhook;
import es.tappx.domain.constants.EntityName;
import es.tappx.processor.Processor;
import es.tappx.webhook.hook.WebhookManager;

@Component
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AdvertisementEventAdapter extends HibernateEventAdapter {

    private static final long serialVersionUID = 1L;
   
    private WebhookManager whManager;
    private Processor adProcessor;
    
    private static final Logger LOG = LoggerFactory.getLogger(AdvertisementEventAdapter.class);

    
    @Autowired
    public AdvertisementEventAdapter(WebhookManager whManager, Processor adProcessor) {
        this.whManager = whManager;
        this.adProcessor = adProcessor;
    }

    
    @Override
    public void onPostDelete(PostDeleteEvent event) {
    	LOG.info("[POSTDELETE-HIBERNATE] EVENT: " + event.getEntity());
    	
    	if (event.getEntity().getClass().getSimpleName().equalsIgnoreCase(EntityName.ADVERTISEMENT.getValue())) {
	    	List<Webhook> hooks = whManager.retrieveWebhooksByEntityNameAndEventType(event.getEntity().getClass().getSimpleName(), EventType.POST_DELETE.eventName());
	        hooks.stream().forEach(wh -> adProcessor.notifyAd(wh, event.getEntity()));
    	}
    	
    }

    @Override
    public boolean onPreDelete(PreDeleteEvent event) {
    	LOG.info("[PREDELETE-HIBERNATE] EVENT: " + event.getEntity());
    	
    	if (event.getEntity().getClass().getSimpleName().equalsIgnoreCase(EntityName.ADVERTISEMENT.getValue())) {
	    	List<Webhook> hooks = whManager.retrieveWebhooksByEntityNameAndEventType(event.getEntity().getClass().getSimpleName(), EventType.PRE_DELETE.eventName());
	        hooks.stream().forEach(wh -> adProcessor.notifyAd(wh, event.getEntity()));
    	}
    	
    	
        return false;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
    	LOG.info("[POSTUPDATE-HIBERNATE] EVENT: " + event.getEntity());
    	
    	if (event.getEntity().getClass().getSimpleName().equalsIgnoreCase(EntityName.ADVERTISEMENT.getValue())) {
	    	List<Webhook> hooks = whManager.retrieveWebhooksByEntityNameAndEventType(event.getEntity().getClass().getSimpleName(), EventType.POST_UPDATE.eventName());
	        hooks.stream().forEach(wh -> adProcessor.notifyAd(wh, event.getEntity()));
    	}
    	
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
    	LOG.info("[PREUPDATE-HIBERNATE] EVENT: " + event.getEntity());
    	
    	if (event.getEntity().getClass().getSimpleName().equalsIgnoreCase(EntityName.ADVERTISEMENT.getValue())) {
	    	List<Webhook> hooks = whManager.retrieveWebhooksByEntityNameAndEventType(event.getEntity().getClass().getSimpleName(), EventType.PRE_UPDATE.eventName());
	        hooks.stream().forEach(wh -> adProcessor.notifyAd(wh, event.getEntity()));
    	}
    	
    	
    	
        return false;
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
    	LOG.info("[POSTINSERT-HIBERNATE] EVENT: " + event.getEntity());
    	
    	if (event.getEntity().getClass().getSimpleName().equalsIgnoreCase(EntityName.ADVERTISEMENT.getValue())) {
	    	List<Webhook> hooks = whManager.retrieveWebhooksByEntityNameAndEventType(event.getEntity().getClass().getSimpleName(), EventType.POST_INSERT.eventName());
	        hooks.stream().forEach(wh -> adProcessor.notifyAd(wh, event.getEntity()));
    	}
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
    	LOG.info("[PREINSERT-HIBERNATE] EVENT: " + event.getEntity());
    	
    	if (event.getEntity().getClass().getSimpleName().equalsIgnoreCase(EntityName.ADVERTISEMENT.getValue())) {
	    	List<Webhook> hooks = whManager.retrieveWebhooksByEntityNameAndEventType(event.getEntity().getClass().getSimpleName(), EventType.PRE_INSERT.eventName());
	        hooks.stream().forEach(wh -> adProcessor.notifyAd(wh, event.getEntity()));
    	}
    	
        return false;
    }
}
