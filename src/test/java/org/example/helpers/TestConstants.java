package org.example.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TestConstants {
    protected WebDriver driver = new EdgeDriver();
    protected static String BASE_URL = "https://magento.softwaretestingboard.com";
    protected static String SIGNUP_URL = "https://magento.softwaretestingboard.com/customer/account/create/";
    protected static String SIGNIN_URL = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";
    protected static String LOGOUT_URL = "https://magento.softwaretestingboard.com/customer/account/logout/";
    protected static String CUSTOMER_EMAIL = "ramsbantagveone@gmail.com";
    protected static String CUSTOMER_PASSWORD = "passwordpassword_@123";
    protected static String CUSTOMER_FIRSTNAME = "Kamel";
    protected static String CUSTOMER_LASTNAME = "Essa";
    protected static String CUSTOMER_STREET = "John West Way";
    protected static String CUSTOMER_CITY = "Aurora";
    protected static String CUSTOMER_STATE = "Ontario";
    protected static String CUSTOMER_POSTAL_CODE = "905";
    protected static String CUSTOMER_COUNTRY = "Canada";
    protected static String CUSTOMER_PHONE = "0123456789";




}
