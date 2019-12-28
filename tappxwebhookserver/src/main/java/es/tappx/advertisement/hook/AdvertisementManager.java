package es.tappx.advertisement.hook;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import es.tappx.domain.Ad;
import es.tappx.domain.Webhook;
import es.tappx.repositories.AdvertisementRepository;
import es.tappx.repositories.WebhookRepository;
import es.tappx.webhook.hook.WebhookManager;

@Component
public class AdvertisementManager {
	
private final Logger LOG = LoggerFactory.getLogger(AdvertisementManager.class);
	
	public AdvertisementRepository adRepository;
	
	@Autowired
    public AdvertisementManager(AdvertisementRepository _adRepository) {
        this.adRepository = _adRepository;
    }
	
	 @Cacheable("advertisement")
	 public List<Ad> findAll() {
	        return adRepository.findAll();
	 }
	 
	 public Ad findOne(Long _id) {
		 Optional<Ad> opt = adRepository.findById(_id);
		 if (opt.isPresent()) {
			 return opt.get();
		 }else {
			 return null;
		 }
	 }
	 
	 
	 public Ad save(Ad adv) throws Exception {
		 
		 try {
		 
			 return adRepository.save(adv);
		 
		 }catch(Exception e) {
			 
			 LOG.warn("[WEBHOOKMANAGER] cannot create new advertisement (" + e.getMessage() + ")",e);
			 throw new Exception("Something happened " + e.getMessage());
		 }
		 
	 }
	 
	 public void delete(Long id) {
		 try {
			 adRepository.deleteById(id);
		 }catch(Exception e) {
			 LOG.warn("[WEBHOOKMANAGER] cannot delete hook (" + id + ")",e);
		 }
	 }

	
	
}
