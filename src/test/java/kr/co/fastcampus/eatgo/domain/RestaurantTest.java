package kr.co.fastcampus.eatgo.domain;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.jupiter.api.Test;
    
public class RestaurantTest {

    @Before
    public void setup(){

    }
        
    @Test
    public void test() {
        
    }

    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant(1004L,"Bob zip","Seoul");

        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getName(), is("Bob zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }

    @Test
    public void information(){
        Restaurant restaurant = new Restaurant("Bob zip","Seoul");
        assertThat(restaurant.getInfomation(), is("Bob zip in Seoul"));
    }

    
    @Test
    public void address(){
        Restaurant restaurant = new Restaurant("Bob zip","Seoul");
        assertThat(restaurant.getAddress(), is("Seoul"));
    }

}
    