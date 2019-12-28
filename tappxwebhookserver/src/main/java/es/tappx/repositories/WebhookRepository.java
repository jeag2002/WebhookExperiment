package es.tappx.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.tappx.domain.Webhook;


public interface WebhookRepository extends JpaRepository<Webhook, String>{ 
//PagingAndSortingRepository<Webhook, String>{
	
	   @Override
	   List<Webhook> findAll();
	   
	   List<Webhook> findByEntityNameAndEventType(@Param("entityName") String entityName, @Param("eventType") String eventType);
	
	
}
