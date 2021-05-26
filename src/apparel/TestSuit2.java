package apparel;

import browsertesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TestSuit2 extends BaseTest {
    @Before
    public void verifyOpenBrowserAndRegister() throws InterruptedException {
        String baseUrl = "https://demo.nopcommerce.com/";
        openBrowser(baseUrl);
        clickOnElement(By.xpath("//a[@class='ico-register']"));
        Thread.sleep(2000);
        sendTextToElement(By.id("FirstName"), "Sheldon");   //firstname
        sendTextToElement(By.id("LastName"), "Cooper");   //lastname
        sendTextToElement(By.id("Email"), "sheldon98@gmail.com"); //email id has to be changed before running
        sendTextToElement(By.id("Password"), "sheldonisgreat");  //password
        sendTextToElement(By.id("ConfirmPassword"), "sheldonisgreat");//confirm password
        clickOnElement(By.id("register-button"));                                    //register button
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));

    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfullyWithExistingUser() throws InterruptedException {
        mouseHoverToElement(By.xpath("//body[1]/div[6]/div[2]/ul[1]/li[3]/a[1]"));
        clickOnElement(By.xpath("//a[text()='Accessories ']"));         //clickon accesorries
        String accesories = "Accessories";
        String actualText = getTextFromElement(By.xpath("//body[1]/div[6]/div[3]/div[1]/div[3]/div[1]/div[1]/h1[1]"));
        Assert.assertEquals("User Is Not On Accessories page ", accesories, actualText);
        Thread.sleep(1000);
        clickOnElement(By.xpath("//a[text()='Ray Ban Aviator Sunglasses']"));        //rayban glasses
        String rayBantext = "Ray Ban Aviator Sunglasses";
        String realRayBanText = getTextFromElement(By.xpath("//body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/h1[1]"));
        Assert.assertEquals("Customer Is Not On RayBan Glass Page", rayBantext, realRayBanText);
        driver.findElement(By.id("product_enteredQuantity_33")).clear();
        sendTextToElement(By.id("product_enteredQuantity_33"), "3");   //change quantity to 3
        clickOnElement(By.id("add-to-cart-button-33"));      //add to cart button
        String topbarMessage = "The product has been added to your shopping cart";    //Green top bar.
        String realTopBarMessage = getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        Assert.assertEquals("product Not Added To The Cart", topbarMessage, realTopBarMessage);
        clickOnElement(By.xpath("//span[@class='close']"));                       //click on cross button on green bar
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));//mousehover on shopping cart
        clickOnElement(By.xpath("//button[text()='Go to cart']"));           //click on go to cart
        String shoppingCartPage = "Shopping cart";             //assert for shopping cart
        String actualShoppingCartPage = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        Assert.assertEquals("Shopping Cart Page Is Not Displayed", shoppingCartPage, actualShoppingCartPage);
        String expectedQuantity = "(3)";                                  //verifying quantity is 3
        String actualQuantity = getTextFromElement(By.xpath("//span[@class='cart-qty']"));
        Assert.assertEquals("Quantity Is Wrong", expectedQuantity, actualQuantity);
        String expectedTotal = "$75.00";
        String acutalTotal = getTextFromElement(By.className("product-subtotal"));
        Assert.assertEquals("Total Is Incorrect", expectedTotal, acutalTotal);
        Thread.sleep(3000);
        clickOnElement(By.id("termsofservice")); //i agree checkbox
        clickOnElement(By.id("checkout"));        //checkout button
        String expectedVerifyWelcome = "Welcome, Please Sign In!";
        String realwelcomeText = getTextFromElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        Assert.assertEquals("Welcome Message Is Wrong", expectedVerifyWelcome, realwelcomeText);
        Thread.sleep(3000);
        sendTextToElement(By.id("Email"), "sheldon98@gmail.com"); //email to be changed before running
        sendTextToElement(By.id("Password"), "sheldonisgreat");    //password
        clickOnElement(By.xpath("//button[contains(text(),'Log in')]"));  //click on login button
        Thread.sleep(3000);
        clickOnElement(By.id("termsofservice"));  //click I agree,again
        clickOnElement(By.id("checkout"));      //click checkout,again
        clickOnElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"));
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "157");
        sendTextToElement(By.id("BillingNewAddress_City"), "Sydney");   //city
        sendTextToElement(By.id("BillingNewAddress_Address1"), "42,Wallby"); //address
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "12121"); //zip code
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "3434342"); //phone number
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));       //click on continue button
        clickOnElement(By.id("shippingoption_2"));   //click on 2nd day air shipping method
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']")); //continue after shipping method
        Thread.sleep(3000);
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));                //credit card radio button
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']")); //click on continue button after shipping method
        selectByValueFromDropDown(By.id("CreditCardType"), "Amex");  // dropdown of card
        sendTextToElement(By.id("CardholderName"), "Mr.S COOPER");  //name on card
        sendTextToElement(By.id("CardNumber"), "341158029662487"); //card number
        selectByValueFromDropDown(By.id("ExpireMonth"), "5"); //month
        selectByValueFromDropDown(By.id("ExpireYear"), "2024");
        sendTextToElement(By.id("CardCode"), "5634");            //cvc cardcode
        clickOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));         //click on continue
        String paymentCard = "Payment Method: Credit Card";
        String actualPaymentCard = getTextFromElement(By.xpath("//li[@class='payment-method']"));
        Assert.assertEquals("Payment method Is Wrong", paymentCard, actualPaymentCard);
        String shippingMethod = "Shipping Method: 2nd Day Air";
        String actualShippingMethod = getTextFromElement(By.xpath("//li[@class='shipping-method']"));
        Assert.assertEquals("Shipping method is Incorrect", shippingMethod, actualShippingMethod);
        String totalOrder = "Total: $75.00";
        String actualTotalOrder = getTextFromElement(By.xpath("//tr[@class='order-total']"));
        Assert.assertEquals("Total Order Is Wrong", totalOrder, actualTotalOrder);
        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));    //click on continue
        String thanks = "Thank you";
        String actualThanksPage = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
        Assert.assertEquals("Thank you Page Not Displayed", thanks, actualThanksPage);
        String orderConfirmation = "Your order has been successfully processed!";
        String realorderConfirmation = getTextFromElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        Assert.assertEquals("Order Has Not Been Confirmed", orderConfirmation, realorderConfirmation);
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));  //last continue click
        clickOnElement(By.xpath("//a[@class='ico-logout']"));                  //logout
        String expectedWelcomeMessage = "Welcome to our store";
        String actualWelcomeMessage = getTextFromElement(By.xpath("//h2[text()='Welcome to our store']"));
        Assert.assertEquals("User Is Not On Main Page", expectedWelcomeMessage, actualWelcomeMessage);
        clickOnElement(By.xpath("//a[@class='ico-logout']"));
        String url = "https://demo.nopcommerce.com/";
        String realUrl = driver.getCurrentUrl();
        Assert.assertEquals("Wrong Url", url, realUrl);

    }

    @After
    public void tearDown() {
        closeBrowser();  // closing browser
    }

}
