package kr.co.fastcampus.eatgo.interfaces;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class RestaurantController{

    // private List<Restaurant> restaurants = new ArrayList<>();

    @Autowired
    private RestaurantService restaurantService;

    // @Autowired
    // private RestaurantRepository restaurantRespository;

    // @Autowired
    // private MenuItemRepository menuItemRepository;

    public RestaurantController() {
        
        // Restaurant restaurant = new Restaurant(1004L,"Bob zip","Seoul");
        // restaurants.add(new Restaurant(1004L,"Bob zip","Seoul"));

        // Restaurant restaurant = new Restaurant(2020L,"Cyber food","Daegu");
        // restaurants.add(new Restaurant(2020L,"Cyber food","Daegu"));
    }

    @GetMapping("/restaurants")
    public List<Restaurant> list(){

        // List<Restaurant> restaurants = restaurantRespository.findAll();

        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){

        // Restaurant restaurant = restaurants.get(0).getId().equals(1004L);

        // Restaurant restaurant = restaurantRespository.findById(id);

        // List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);

        // restaurant.setMenuItems(menuItems);

        // Restaurant restaurant = restaurants.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
        
    }
    
    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource) throws Exception{

        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant = new Restaurant(name,address);

        restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/"+restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }
}