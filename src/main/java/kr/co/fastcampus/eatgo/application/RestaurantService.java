package kr.co.fastcampus.eatgo.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;

@Service
public class RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRespository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,MenuItemRepository menuItemRepository){
        this.restaurantRespository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Restaurant> getRestaurants(){

        List<Restaurant> restaurants = restaurantRespository.findAll();

        return restaurants;

    }

    public Restaurant getRestaurant(Long id){

        Restaurant restaurant = restaurantRespository.findById(id).orElse(null);

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);

        restaurant.setMenuItems(menuItems);

        return restaurant;

    }

    public Restaurant addRestaurant(Restaurant restaurant){
        Restaurant saved = restaurantRespository.save(restaurant);

        return saved;
    }

}