package org.example.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class TestConstants {
    protected WebDriver driver = new EdgeDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
    protected static String BASE_URL = "https://magento.softwaretestingboard.com";
    protected static String SIGNUP_URL = "https://magento.softwaretestingboard.com/customer/account/create/";
    protected static String SIGNIN_URL = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";
    protected static String LOGOUT_URL = "https://magento.softwaretestingboard.com/customer/account/logout/";
    protected static String CART_URL = "https://magento.softwaretestingboard.com/checkout/cart/";
    protected static String CUSTOMER_EMAIL = "ramsbantagveone@gmail.com";
    protected static String CUSTOMER_PASSWORD = "passwordpassword_@123";
    protected static String CUSTOMER_FIRSTNAME = "Kamel";
    protected static String CUSTOMER_LASTNAME = "Essa";
    protected static String CUSTOMER_STREET = "John West Way";
    protected static String CUSTOMER_CITY = "Aurora";
    protected static String CUSTOMER_STATE = "Ontario";
    protected static String CUSTOMER_POSTAL_CODE = "A1B2C3";
    protected static String CUSTOMER_COUNTRY = "Canada";
    protected static String CUSTOMER_PHONE = "0123456789";
    protected Random random = new Random();
    protected static String[] ITEMS_URLS = {
            "https://magento.softwaretestingboard.com/radiant-tee.html",            // 0
            "https://magento.softwaretestingboard.com/breathe-easy-tank.html",      // 1
            "https://magento.softwaretestingboard.com/argus-all-weather-tank.html", // 2
            "https://magento.softwaretestingboard.com/hero-hoodie.html",            // 3
            "https://magento.softwaretestingboard.com/fusion-backpack.html"         // 4
    };
    protected static String[] ITEM_SIZES = {
            "option-label-size-143-item-166", // xs
            "option-label-size-143-item-167", // s
            "option-label-size-143-item-168", // m
            "option-label-size-143-item-169", // l
            "option-label-size-143-item-170"  // xl
    };
    protected static String[] ITEM_COLORS = {
            "option-label-color-93-item-50", // Blue
            "option-label-color-93-item-56", // Orange
            "option-label-color-93-item-57", // Purple
            "option-label-color-93-item-59", // White
            "option-label-color-93-item-60", // Yellow
            "option-label-color-93-item-52", // Grey
            "option-label-color-93-item-49", // Black
            "option-label-color-93-item-52", // Grey
            "option-label-color-93-item-53", // Green
    };

    protected int[] randomColorIndex = {
            // This array will have a random index for every color that every item have,
            // and it's based on the colors availability for each item
            random.nextInt(0, 3),
            random.nextInt(2, 5),
            5,
            random.nextInt(6, 9),
    };
    protected static int[] ITEM_QUANTITIES = {3, 2, 4, 1, 5};


}
