package movies.tools;

import movies.dal.UsersDao;
import movies.dal.*;
import movies.model.*;

import java.text.ParseException;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
 * @author nikithanagaraj
 */

public class Inserter {

    public static void main(String[] args) throws SQLException, ParseException {
//		// DAO instances.
        UsersDao usersDao = UsersDao.getInstance();


        Users user = new Users("Bruce", "Bruce", "C", "password", "bruce@mail.com", "5555555");
        user = usersDao.create(user);
        Users user1 = new Users("TT", "Tony", "D", "password", "tony@mail.com'@mail.com", "5555555");
        user1 = usersDao.create(user1);
        Users user2 = new Users("DK", "Daniel", "K", "password", "dan@mail.com", "5555555");
        user2 = usersDao.create(user2);
        Users user3 = new Users("James", "James", "M", "password", "james@mail.com", "5555555");
        user3 = usersDao.create(user3);
        Users user4 = new Users("Steve", "Steve", "N", "password", "steve@mail.com", "5555555");
        user4 = usersDao.create(user4);

        System.out.println("Inserted Users");

//        Companies companies1 = new Companies("company1", "about company1");
//        companies1 = companiesDao.create(companies1);
//        Companies companies2 = new Companies("company2", "about company2");
//        companies2 = companiesDao.create(companies2);
//        Companies companies3 = new Companies("company3", "about company3");
//        companies3 = companiesDao.create(companies3);
//
//        System.out.println("Inserted Companies");
//
//        Restaurants restaurants1 = new Restaurants(1, "restaurant1", "about restaurant", "menu", "hours", true, "street1", "street2", "seatle",
//                "wa", 98195, "company1", Restaurants.CuisineType.african);
//        restaurants1 = restaurantsDao.create(restaurants1);
//
//        Restaurants restaurants2 = new Restaurants(2, "restaurant2", "about restaurant", "menu", "hours", true, "street1", "street2", "seatle",
//                "wa", 98195, "company1", Restaurants.CuisineType.american);
//        restaurants2 = restaurantsDao.create(restaurants2);
//
//        Restaurants restaurants3 = new Restaurants(3, "restaurant3", "about restaurant", "menu", "hours", true, "street1", "street2", "seatle",
//                "wa", 98195, "company1", Restaurants.CuisineType.asian);
//        restaurants3 = restaurantsDao.create(restaurants3);
//
//        Restaurants restaurants4 = new Restaurants(4, "restaurant4", "about restaurant", "menu", "hours", true, "street1", "street2", "seatle",
//                "wa", 98195, "company1", Restaurants.CuisineType.european);
//        restaurants4 = restaurantsDao.create(restaurants4);
//
//        Restaurants restaurants5 = new Restaurants(5, "restaurant5", "about restaurant", "menu", "hours", true, "street1", "street2", "seatle",
//                "wa", 98195, "company1", Restaurants.CuisineType.african);
//        restaurants5 = restaurantsDao.create(restaurants5);
//
//        Restaurants restaurants6 = new Restaurants(6, "restaurant6", "about restaurant", "menu", "hours", true, "street1", "street2", "seatle",
//                "wa", 98195, "company2", Restaurants.CuisineType.american);
//        restaurants6 = restaurantsDao.create(restaurants6);
//
//        Restaurants restaurants7 = new Restaurants(7, "restaurant7", "about restaurant", "menu", "hours", true, "street1", "street2", "bellevue",
//                "wa", 98008, "company2", Restaurants.CuisineType.asian);
//        restaurants7 = restaurantsDao.create(restaurants7);
//
//        Restaurants restaurants8 = new Restaurants(8, "restaurant8", "about restaurant", "menu", "hours", true, "street1", "street2", "bellevue",
//                "wa", 98008, "company2", Restaurants.CuisineType.european);
//        restaurants8 = restaurantsDao.create(restaurants8);
//
//        Restaurants restaurants9 = new Restaurants(9, "restaurant9", "about restaurant", "menu", "hours", true, "street1", "street2", "bellevue",
//                "wa", 98008, "company3", Restaurants.CuisineType.hispanic);
//        restaurants9 = restaurantsDao.create(restaurants9);
//
//        Restaurants restaurants10 = new Restaurants(10, "restaurant10", "about restaurant", "menu", "hours", true, "street1", "street2", "bellevue",
//                "wa", 98008, "company3", Restaurants.CuisineType.hispanic);
//        restaurants10 = restaurantsDao.create(restaurants10);
//
//        System.out.println("Inserted Restaurant");
//
//        SitDownRestaurants sitDownRestaurants1 = new SitDownRestaurants(1, 100);
//        sitDownRestaurants1 = sitDownRestaurantsDao.create(sitDownRestaurants1);
//        SitDownRestaurants sitDownRestaurants2 = new SitDownRestaurants(2, 200);
//        sitDownRestaurants2 = sitDownRestaurantsDao.create(sitDownRestaurants2);
//        SitDownRestaurants sitDownRestaurants3 = new SitDownRestaurants(3, 200);
//        sitDownRestaurants3 = sitDownRestaurantsDao.create(sitDownRestaurants3);
//
//        System.out.println("Inserted Sitdown");
//
//        TakeOutRestaurants takeOutRestaurants1 = new TakeOutRestaurants(4, 60);
//        takeOutRestaurants1 = takeOutRestaurantsDao.create(takeOutRestaurants1);
//        TakeOutRestaurants takeOutRestaurants2 = new TakeOutRestaurants(5, 90);
//        takeOutRestaurants2 = takeOutRestaurantsDao.create(takeOutRestaurants2);
//        System.out.println("Inserted takeout");
//
//        FoodCartRestaurants foodCartRestaurants1 = new FoodCartRestaurants(true, 6);
//        foodCartRestaurants1 = foodCartRestaurantsDao.create(foodCartRestaurants1);
//        FoodCartRestaurants foodCartRestaurants2 = new FoodCartRestaurants(true, 7);
//        foodCartRestaurants2 = foodCartRestaurantsDao.create(foodCartRestaurants2);
//        FoodCartRestaurants foodCartRestaurants3 = new FoodCartRestaurants(true, 8);
//        foodCartRestaurants3 = foodCartRestaurantsDao.create(foodCartRestaurants3);
//        FoodCartRestaurants foodCartRestaurants4 = new FoodCartRestaurants(true, 9);
//        foodCartRestaurants4 = foodCartRestaurantsDao.create(foodCartRestaurants4);
//        FoodCartRestaurants foodCartRestaurants5 = new FoodCartRestaurants(false, 10);
//        foodCartRestaurants5 = foodCartRestaurantsDao.create(foodCartRestaurants5);
//        System.out.println("Inserted foodcart");
//
//        Date date = new Date();
//        Reviews reviews1 = new Reviews(date, "Delightful!", 5.0, user, restaurants1);
//        reviews1 = reviewsDao.create(reviews1);
//        Reviews reviews2 = new Reviews(date, "Superb!", 5.0, user, restaurants2);
//        reviews2 = reviewsDao.create(reviews2);
//        Reviews reviews3 = new Reviews(date, "Superb!!", 5.0, user, restaurants4);
//        reviews3 = reviewsDao.create(reviews3);
//        Reviews reviews4 = new Reviews(date, "Not good!", 2.0, user2, restaurants5);
//        reviews4 = reviewsDao.create(reviews4);
//        Reviews reviews5 = new Reviews(date, "Not good at all!", 1.0, user2, restaurants10);
//        reviews5 = reviewsDao.create(reviews5);
//        System.out.println("Inserted reviews");
//
//        Recommendations recommendations1 = new Recommendations(user, restaurants1);
//        recommendations1 = recommendationsDao.create(recommendations1);
//        Recommendations recommendations2 = new Recommendations(user, restaurants2);
//        recommendations2 = recommendationsDao.create(recommendations2);
//        Recommendations recommendations3 = new Recommendations(user, restaurants3);
//        recommendations3 = recommendationsDao.create(recommendations3);
//        Recommendations recommendations4 = new Recommendations(user2, restaurants4);
//        recommendations4 = recommendationsDao.create(recommendations4);
//        Recommendations recommendations5 = new Recommendations(user2, restaurants5);
//        recommendations5 = recommendationsDao.create(recommendations5);
//
//        System.out.println("Inserted recommendations");
//
//        Date exp = new Date(2018, 10, 01);
//        CreditCards card1 = new CreditCards(3499432187650987L, exp, user);
//        card1 = creditCardsDao.create(card1);
//
//        CreditCards card2 = new CreditCards(3488432187650987L, exp, user1);
//        card2 = creditCardsDao.create(card2);
//
//        CreditCards card3 = new CreditCards(3799432187650987L, exp, user2);
//        card3 = creditCardsDao.create(card3);
//
//        CreditCards card4 = new CreditCards(6011432187650987L, exp, user3);
//        card4 = creditCardsDao.create(card4);
//
//        CreditCards card5 = new CreditCards(6011432187650988L, exp, user4);
//        card5 = creditCardsDao.create(card5);
//
//        CreditCards card6 = new CreditCards(6441432187650987L, exp, user);
//        card6 = creditCardsDao.create(card6);
//
//        CreditCards card7 = new CreditCards(6451432187650987L, exp, user1);
//        card1 = creditCardsDao.create(card7);
//
//        CreditCards card8 = new CreditCards(5499432187650987L, exp, user3);
//        card8 = creditCardsDao.create(card8);
//        System.out.println("Inserted credit card");
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date start = dateFormat.parse("2018-08-20 20:00:00");
//        Date end = dateFormat.parse("2018-08-20 22:00:00");
//
//        Reservations reservations1 = new Reservations(start, end, 2, user, sitDownRestaurants1);
//        reservations1 = reservationsDao.create(reservations1);
//        Reservations reservations2 = new Reservations(start, end, 2, user, sitDownRestaurants1);
//        reservations2 = reservationsDao.create(reservations2);
//        Reservations reservations3 = new Reservations(start, end, 4, user2, sitDownRestaurants2);
//        reservations3 = reservationsDao.create(reservations3);
//        Reservations reservations4 = new Reservations(start, end, 2, user3, sitDownRestaurants2);
//        reservations4 = reservationsDao.create(reservations4);
//        Reservations reservations5 = new Reservations(start, end, 3, user2, sitDownRestaurants3);
//        reservations5 = reservationsDao.create(reservations5);

    }
}
