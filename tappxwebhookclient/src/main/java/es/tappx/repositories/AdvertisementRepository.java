package es.tappx.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import es.tappx.domain.Ad;


public interface AdvertisementRepository extends PagingAndSortingRepository<Ad, Long>{
	
	   @Override
	   List<Ad> findAll();
}
