package kr.co.fastcampus.eatgo.application;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
// import kr.co.fastcampus.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
// import kr.co.fastcampus.eatgo.domain.RestaurantRespositoryImpl;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.core.Is;

import org.junit.Before;    
    
public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setup(){

        MockitoAnnotations.initMocks(this);

        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L,"Bob zip","Seoul");
        restaurants.add(restaurant);
        given(restaurantRepository.findAll()).willReturn(restaurants);

        // restaurantRepository = new RestaurantRespositoryImpl();
        // menuItemRepository = new MenuItemRepositoryImpl();

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem = new MenuItem("Kimchi");
        menuItems.add(menuItem);
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);

        restaurantService = new RestaurantService(restaurantRepository,menuItemRepository);
    }
        
    @Test
    public void test() {
        
    }

    @Test
    public void getRestaurants(){

        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(),Is.is(1004L));

    }

    @Test
    public void getRestaurant(){

        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName(), Is.is("Kimchi"));

    }

    @Test
    public void addRestaurant(){

        Restaurant restaurant = new Restaurant("BeRyong","Seoul");
        
        Restaurant saved = new Restaurant(1234L,"BeRyong","Seoul");

        given(restaurantRepository.save(any())).willReturn(saved);

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(),Is.is(1234L));
    }
}
    