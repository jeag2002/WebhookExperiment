package es.tappx.services;

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

@Component
public class CallbackService {
	
	private final Logger LOG = LoggerFactory.getLogger( CallbackService.class);
	
	public AdvertisementRepository adRepo;
	
	@Autowired
    public CallbackService(AdvertisementRepository _adRepo) {
        this.adRepo = _adRepo;
    }
	
	
	public List<Ad> findAll() {
	       return adRepo.findAll();
	}
	
	
	public Ad findOne(Long _id) {
		 Optional<Ad> opt = adRepo.findById(_id);
		 if (opt.isPresent()) {
			 return opt.get();
		 }else {
			 return null;
		 }
	}
	
	public void save(Ad ad) {
		 
		 try {
			 LOG.info("[CALLBACKSERVICE] SAVE ADVERTISEMENT " + ad);
			 adRepo.save(ad);
		 }catch(Exception e) {
			 LOG.warn("[CALLBACKSERVICE] cannot create new Ad (" + e.getMessage() + ")",e);
		 }
	}
	
	public void delete(Long id) {
		 try {
			 LOG.info("[CALLBACKSERVICE] DELETE ADVERTISEMENT (" + id + ")");
			 adRepo.deleteById(id);
		 }catch(Exception e) {
			 LOG.warn("[CALLBACKSERVICE] cannot delete Ad (" + id + ")",e);
		 }
	 }
	
	
	

}
