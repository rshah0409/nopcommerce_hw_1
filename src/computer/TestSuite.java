package computer;

import browsertesting.BaseTest;
import com.sun.deploy.association.Action;
import homepage.TopMenuTest;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class TestSuite extends BaseTest {

    @Before
    public void verifyproductArrangeInAlphaBaticalOrder() {
        String baseUrl = "https://demo.nopcommerce.com/";
        openBrowser(baseUrl);
        Actions actions = new Actions(driver);
        WebElement computer = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));    //mouse hover on element
        WebElement desktop = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        actions.moveToElement(computer).moveToElement(desktop).click().build().perform();      //with action class mouse hover and element click
        WebElement sortBy = driver.findElement(By.id("products-orderby"));
        Select select = new Select(sortBy);
        select.selectByValue("6");
        String expectedOrder = "Name: Z to A";
        WebElement actualOrder = driver.findElement(By.xpath("//option[contains(text(),'Name: Z to A')]"));
        String realOrder = actualOrder.getText();
        Assert.assertEquals("The List Is not In Descending Order", expectedOrder, realOrder);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        WebElement sortBy = driver.findElement(By.id("products-orderby"));
        Select select = new Select(sortBy);
        select.selectByValue("5");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@onclick='return AjaxCart.addproducttocart_catalog(\"/addproducttocart/catalog/1/1/1\"),!1']"));
        String expectedDisplayedMessage = "Build your own computer";                     //verify build your own computer text
        WebElement actualDisplayedMessage = driver.findElement(By.xpath("//h1[text()='Build your own computer']"));
        String realDisplayedMessage = actualDisplayedMessage.getText();
        Assert.assertEquals("Customer is not on build your own computer page", expectedDisplayedMessage, realDisplayedMessage);
        WebElement dropDown = driver.findElement(By.xpath("//select[@name='product_attribute_1']"));     //dropdown menu
        select = new Select(dropDown);
        select.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");   //select an item on dropdown menu
        WebElement ram = driver.findElement(By.xpath("//select[@name='product_attribute_2']"));    //drop down menu
        select = new Select(ram);
        select.selectByValue("5");                                              //select on item on dropdown menu
        clickOnElement(By.id("product_attribute_3_7"));                  //click on radio box
        clickOnElement(By.id("product_attribute_4_9"));
        clickOnElement(By.id("product_attribute_5_12"));
        String expectedTotalprice = "$1,475.00";
        WebElement actualTotalPrice = driver.findElement(By.xpath("//span[text()='$1,475.00']"));
        String actualPrice = actualTotalPrice.getText();
        Assert.assertEquals("The Total Price Is Not Correct", expectedTotalprice, actualPrice);
        clickOnElement(By.id("add-to-cart-button-1"));
        String expectedTopBarMessage = "The product has been added to your shopping cart";  //verifying topbar
        WebElement actualTopMessage = driver.findElement(By.className("content"));
        String actualTopBarMessage = actualTopMessage.getText();
        Assert.assertEquals("Your Shopping Has Not Been Added To Cart", expectedTopBarMessage, actualTopBarMessage);
        clickOnElement(By.className("close"));
        Actions actions2 = new Actions(driver);          //mousehover on shopping cart and click on
        WebElement shoppingCart = driver.findElement(By.xpath("//span[text()='Shopping cart']"));
        actions2.moveToElement(shoppingCart).build().perform();
        clickOnElement(By.xpath("//button[@class='button-1 cart-button']"));
        String expectedCartPageMessage = "Shopping cart";
        WebElement actualPageMsg = driver.findElement(By.xpath("//h1[text()='Shopping cart']"));
        String actualCartPageMessage = actualPageMsg.getText();
        Assert.assertEquals("Customer Is Not On Shopping Cart Page", expectedCartPageMessage, actualCartPageMessage);
        driver.findElement(By.xpath("//input[@value='1']")).clear();                          //deleting 1 quantity
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='1']")).sendKeys("2");       //adding 2 quantity
        clickOnElement(By.xpath("//button[text()='Update shopping cart']"));
        String expectedTotal = "$2,950.00";                                                 //verifying total
        WebElement actualTotal = driver.findElement(By.xpath("//span[@class='product-subtotal']"));
        String actualTotalValue = actualTotal.getText();
        Assert.assertEquals("Total Is Wrong", expectedTotal, actualTotalValue);
        Thread.sleep(2000);
        clickOnElement(By.xpath("//label[text()='I agree with the terms of service and I adhere to them unconditionally']"));       //checkbox of I agree
        clickOnElement(By.xpath("//button[@id = 'checkout']"));                                                            //click on button
        String expectedSignInMessage = "Welcome, Please Sign In!";
        WebElement actualSignInMessage = driver.findElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        String actualWelcomeSignInMessage = actualSignInMessage.getText();
        Assert.assertEquals("User Is Not On Sign In Page", expectedSignInMessage, actualWelcomeSignInMessage);
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[text()='Checkout as Guest']"));
        Thread.sleep(1000);
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("Sheldon");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Cooper");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("sheldonc@gmail.com");
        WebElement country = driver.findElement(By.id("BillingNewAddress_CountryId"));    //dropdown menu for country
        select = new Select(country);
        select.selectByValue("157");
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Sydney");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1']")).sendKeys("42,Wallaby way");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address2']")).sendKeys("Sydney");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("322444");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("1212121");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));        //click on continue button
        clickOnElement(By.id("shippingoption_1"));                         //next day air
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        WebElement dropDwn = driver.findElement(By.xpath("//select[@id='CreditCardType']"));
        select = new Select(dropDwn);
        select.selectByValue("MasterCard");
        driver.findElement(By.xpath("//input[@id='CardholderName']")).sendKeys("MR.S COOPER");  //card dteails(name
        driver.findElement(By.xpath("//input[@id='CardNumber']")).sendKeys("5534365663910124"); //card number
        WebElement month = driver.findElement(By.xpath("//select[@id='ExpireMonth']"));   //month
        select = new Select(month);
        select.selectByValue("7");
        WebElement year = driver.findElement(By.xpath("//select[@id='ExpireYear']"));  //year
        select = new Select(year);
        select.selectByValue("2025");
        driver.findElement(By.xpath("//input[@name='CardCode']")).sendKeys("147");  //cva number
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));
        String expectedText = "Payment Method: Credit Card";
        WebElement actualText = driver.findElement(By.xpath("//li[@class='payment-method']"));
        String actualTextOnPage = actualText.getText();
        Assert.assertEquals("Payment Is Not By Credit Card", expectedText, actualTextOnPage);
        String expectedShippingMessage = "Shipping Method: Next Day Air";
        WebElement actualMessage = driver.findElement(By.xpath("//li[@class='shipping-method']"));
        String actualShippingMessage = actualMessage.getText();
        Assert.assertEquals("Shipping Method Is Incorrect", expectedShippingMessage, actualShippingMessage);
        String expectedTotalText = "Total: $2,950.00";
        WebElement actualTotalTxt = driver.findElement(By.xpath("//tr[@class='order-total']"));
        String actualTotalIs = actualTotalTxt.getText();
        Assert.assertEquals("Total Order Is Incorrect", expectedTotalText, actualTotalIs);
        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));
        String expectedThanksMessage = "Thank you";
        WebElement actualThanksMessage = driver.findElement(By.xpath("//h1[text()='Thank you']"));
        String actualThankYouMessage = actualThanksMessage.getText();
        Assert.assertEquals("Wrong ThankYou Message Appearing", expectedThanksMessage, actualThankYouMessage);
        String expectedConfirmationMessage = "Your order has been successfully processed!";
        WebElement actualMessageIs = driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        String actualConfirmationMessage = actualMessageIs.getText();
        Assert.assertEquals("Wrong Confirmation Message", expectedConfirmationMessage, actualConfirmationMessage);
        clickOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));
        String expectedWelcomeText = "Welcome to our store";
        WebElement actualMessge = driver.findElement(By.xpath("//h2[text()='Welcome to our store']"));
        String actualWelcomeText = actualMessge.getText();
        Assert.assertEquals("Welcome Message Is Wrong", expectedWelcomeText, actualWelcomeText);
        }

        @After
        public void tearDown() {
        closeBrowser();   //closing browser
        }

       }
