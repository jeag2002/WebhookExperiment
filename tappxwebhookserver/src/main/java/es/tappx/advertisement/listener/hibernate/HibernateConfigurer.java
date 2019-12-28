package es.tappx.advertisement.listener.hibernate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateConfigurer {
	
	//@PersistenceContext
	//private EntityManager entityManager;
	
    @Autowired
    private HibernateEntityManagerFactory entityManagerFactory;

    @Autowired
    private AdvertisementEventAdapter adEventAdapter;
    
    @PostConstruct
    public void registerListeners() {
    	
    	//Session session = entityManager.unwrap(Session.class);
    	//SessionFactory sfi = session.getSessionFactory();
    	//EventListenerRegistry elr = sfi.getSessionFactory().getServiceRegistry().getService(EventListenerRegistry.class);
    	
    	EventListenerRegistry elr = entityManagerFactory.getSessionFactory().getServiceRegistry().getService(EventListenerRegistry.class);
    	  
    
        elr.setListeners(EventType.PRE_INSERT, adEventAdapter);
        elr.setListeners(EventType.PRE_UPDATE, adEventAdapter);
        elr.setListeners(EventType.PRE_DELETE, adEventAdapter);
        elr.setListeners(EventType.POST_INSERT, adEventAdapter);
        elr.setListeners(EventType.POST_UPDATE, adEventAdapter);
        elr.setListeners(EventType.POST_DELETE, adEventAdapter);
    }
}
