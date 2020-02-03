package kr.co.fastcampus.eatgo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {
    
    public List<Restaurant> findAll();

    // public <Optional>Restaurant findById(Long id);

    public Restaurant save(Restaurant restaurant);
}