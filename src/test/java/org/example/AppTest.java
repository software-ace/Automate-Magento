package org.example;

import org.example.helpers.TestConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class AppTest extends TestConstants {

    @BeforeTest
    public void Setup() {
        driver.get(BASE_URL);
    }

    @Test(priority = 1, enabled = false, description = "This test checks the Sign Up functionality.")
    public void SignUp() {
        // Heading to the create new customer page
        driver.navigate().to(SIGNUP_URL);

        // locating the form input fields
        WebElement firstname_input_field = driver.findElement(By.xpath("//*[@id=\"firstname\"]"));
        WebElement lastname_input_field = driver.findElement(By.id("lastname"));
        WebElement email_address_input_field = driver.findElement(By.id("email_address"));
        WebElement password_input_field = driver.findElement(By.id("password"));
        WebElement password_confirmation_input_field = driver.findElement(By.id("password-confirmation"));

        // Filling the form input fields
        firstname_input_field.sendKeys(CUSTOMER_FIRSTNAME);
        lastname_input_field.sendKeys(CUSTOMER_LASTNAME);
        email_address_input_field.sendKeys(CUSTOMER_EMAIL);
        password_input_field.sendKeys(CUSTOMER_PASSWORD);
        password_confirmation_input_field.sendKeys(CUSTOMER_PASSWORD);

        // locating Submit button then click on it
        WebElement create_an_account_button = driver.findElement(By.className("submit"));
        create_an_account_button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // Making sure the process is successful
        Assert.assertEquals(driver.getCurrentUrl(), "https://magento.softwaretestingboard.com/customer/account/");

        // Disable the test if the assertion passes
        if (driver.getCurrentUrl().equals("https://magento.softwaretestingboard.com/customer/account/")) {
            System.out.println("Sign Up test passed. Disabling test...");
            throw new SkipException("Skipping the test because it passed once, and it signed up successfully.");
        }
    }

    @Test(priority = 2, description = "This test checks the login functionality.")
    public void SignIn() {
        // Making sure we are not in logged session
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get(LOGOUT_URL);

        // Heading to sign in customer page
        driver.navigate().to(SIGNIN_URL);

        // Locating the form fields
        WebElement email_input_field = driver.findElement(By.id("email"));
        WebElement password_input_field = driver.findElement(By.id("pass"));
        WebElement signin_button = driver.findElement(By.id("send2"));

        // Filling the required data to login
        email_input_field.sendKeys(CUSTOMER_EMAIL);
        password_input_field.sendKeys(CUSTOMER_PASSWORD);

        // Logging In
        signin_button.click();
    }

    @Test(priority = 3, dependsOnMethods = "SignIn", enabled = false)
    public void Verify_Random_Search() {
        String[] items = {"jeans for men", "jeans for women", "Jacket", "t-shirt", "pants"};

        // Locating Search Input Field
        WebElement search_input_field = driver.findElement(By.id("search"));

        // Selecting a random item from items
        int random_item = new Random().nextInt(0, items.length);

        // Searching for the random item
        search_input_field.sendKeys(items[random_item] + Keys.ENTER);

        // Getting the Searched item string
        WebElement searched_for_message = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/h1/span"));

        // Extracting the item name
        String item_name = searched_for_message.getText().replaceAll(".*'([^']*)'.*", "$1");

        // Print the Item in the Console
        System.out.println("The name of the randomly selected item: " + item_name);

        // Assertion
        Assert.assertEquals(items[random_item], item_name);
    }

    @Test(priority = 4, dependsOnMethods = "SignIn", description = "This test case verifies that the specified items with random size and color are correctly added to the cart.")
    public void Verify_Adding_Items_To_The_Cart() {

        driver.get(BASE_URL);

        for (int i = 0; i < ITEMS_URLS.length; i++) {
            // Navigating to every item page
            driver.get(ITEMS_URLS[i]);
            // waiting the size and color to appear
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

            // locating random arguments for the item
            WebElement chosenSized = driver.findElement(By.id(ITEM_SIZES[random.nextInt(0, 5)]));
            WebElement chosenColor = driver.findElement(By.id(ITEM_COLORS[randomColorIndex[i]]));

            // locating quantity number field
            WebElement quantity_number_field = driver.findElement(By.id("qty"));

            // Filling the arguments for the item
            chosenSized.click();
            chosenColor.click();
            quantity_number_field.clear();
            quantity_number_field.sendKeys(String.valueOf(ITEM_QUANTITIES[i]));

            // adding every item to cart
            WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
            addToCartButton.click();
        }
        driver.get(CART_URL);
        // Declare WebDriverWait to explicitly wait 3 seconds for the element to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // locating the ordered items total number in the cart
        WebElement items_in_cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/header/div[2]/div[1]/a/span[2]/span[1]")));
        System.out.println("items in cart :" + items_in_cart.getText());
        // Assertion
        Assert.assertEquals(items_in_cart.getText(), "10");
    }

    @Test(priority = 5, dependsOnMethods = "Verify_Adding_Items_To_The_Cart", description = "This test case verifies that the checkout process is successful after adding items to the cart, and that the shipping address is correctly processed.")
    public void Verify_Checkout_Process() {
        WebElement proceed_to_checkout_button = driver.findElement(By.xpath("/html/body/div[1]/main/div[3]/div/div[2]/div[1]/ul/li[1]/button"));
        proceed_to_checkout_button.click();
        WebElement street_input_field = driver.findElement(By.id("G2T22OY"));
        WebElement city_input_field = driver.findElement(By.id("O4AF7R7"));
        WebElement postal_code_input_field = driver.findElement(By.id("XMYQFQV"));
        WebElement phone_input_field = driver.findElement(By.id("WUOBHEH"));

    }

    @Test(priority = 6, dependsOnMethods = "Verify_Checkout_Process", description = "This test case verifies that the final price displayed on the order confirmation page is correct after clicking the 'Place Order' button.")
    public void Verify_Final_Price() {

    }

    @Test(priority = 7, dependsOnMethods = "Verify_Final_Price", description = "Description: This test case verifies that a previous order can be reordered multiple times from 'My Orders' page.")
    public void Verify_Reordering_of_Previous_Order() {

    }

    @Test(priority = 8, dependsOnMethods = "Verify_Reordering_of_Previous_Order", description = "This test case verifies that the price of the five reordered invoices is consistent when viewed from the 'My Orders' section.")
    public void Verify_Price_Consistency_Across_Reordered_Invoices() {

    }

    @AfterTest
    public void QuitSession() {
        driver.quit();
    }

}
