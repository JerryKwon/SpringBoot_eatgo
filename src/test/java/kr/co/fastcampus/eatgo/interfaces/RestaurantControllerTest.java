package kr.co.fastcampus.eatgo.interfaces;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
// import kr.co.fastcampus.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
// import kr.co.fastcampus.eatgo.domain.RestaurantRespositoryImpl;

import org.junit.Before;    

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    // @SpyBean(classes =  RestaurantService.class)
    // private RestaurantService restaurantService;

    @MockBean
    private RestaurantService restaurantService;

    // @SpyBean(classes = RestaurantRespositoryImpl.class)
    // private RestaurantRepository restaurantRespository;

    // @SpyBean(classes = MenuItemRepositoryImpl.class)
    // private MenuItemRepository menuItemRepository;

    @Before
    public void setup(){

    }
        
    @Test
    public void test() {
        
    }

    @Test
    public void list() throws Exception{

        List<Restaurant> restaurants = new ArrayList<>();

        restaurants.add(new Restaurant(1004L,"Bob zip","Seoul"));

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants")).andExpect(status().isOk())
                                        .andExpect(content().string(containsString("\"id\":1004")))
                                        .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
                                        .andExpect(content().string(containsString("\"address\":\"Seoul\"")));
    }

    @Test
    public void detail() throws Exception{

        Restaurant restaurant1 = new Restaurant(1004L,"Bob zip","Seoul");
        restaurant1.addMenuItem(new MenuItem("Kimchi"));

        Restaurant restaurant2 = new Restaurant(2020L,"Bob zip","Seoul");
        restaurant2.addMenuItem(new MenuItem("Kimchi"));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);

        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004")).andExpect(status().isOk())
                                        .andExpect(content().string(containsString("\"id\":1004")))
                                        .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
                                        .andExpect(content().string(containsString("\"address\":\"Seoul\"")))
                                        .andExpect(content().string(containsString("Kimchi")));
        
        mvc.perform(get("/restaurants/2020")).andExpect(status().isOk())
                                            .andExpect(content().string(containsString("\"id\":2020")))
                                            .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
                                            .andExpect(content().string(containsString("\"address\":\"Seoul\"")))
                                            .andExpect(content().string(containsString("Kimchi")));

        }

        
    @Test
    public void create() throws Exception{

        // Restaurant restaurant = new Restaurant(1234L,"BeRyong","Seoul");

        // restaurantService.addRestaurant(restaurant);

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\",\"address\":\"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1234"))
                .andExpect(content().string("{}"));

        
        verify(restaurantService).addRestaurant(any());
    }

}
    