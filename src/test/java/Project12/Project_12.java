package Project12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Project_12 extends UtilityClass{

    /**
     * 1- Go to "https://demo.openmrs.org/openmrs/login.htm".
     * 2- Try to log in with username="Admin" and password="Admin123"
     *    without choosing a location and verify that the error
     *    message is "You must choose a location!".
     * 3- Enter the same username and password.
     * 4- Hover over the location names one by one and check if
     *    the background color changes.
     * 5- Choose one of the locations randomly and click on
     *    "Log In" button.
     * 6- Click on the location icon
     * 7- Click on every location one by one and check if it is
     *    among the locations on the login page and current location changes.
     * 8- Verify that admin button is displayed
     * 9- Hover over "Admin" button and verify that "My Account"
     *    button is displayed.
     * 10- Click on "My Account" button and verify that the title of
     *     the page is "My Account".
     * 11- Click on "My Languages" button.
     * 12- Verify that the title of the page is "My Languages"
     * 13- Select a random language from the drop down menu.
     * 14- Check and uncheck the boxes one by one. Verify
     *     that the box is checked and unchecked each time.
     * 15- Check all of the boxes and verify that they are all checked
     * 16- Click on The "Save" button and verify error message is
     *     displayed.
     */

    public static void main(String[] args) {

//  1- Go to "https://demo.openmrs.org/openmrs/login.htm".

        driver.get("https://demo.openmrs.org/openmrs/login.htm");

//  2- Try to log in with username="Admin" and password="Admin123"
//  without choosing a location and verify that the error message is "You must choose a location!".

        WebElement userName= driver.findElement(By.id("username"));
        userName.sendKeys("Admin");

        WebElement password= driver.findElement(By.id("password"));
        password.sendKeys("Admin123");

        WebElement loginButton= driver.findElement(By.id("loginButton"));
        loginButton.click();

        WebElement message=driver.findElement(By.id("sessionLocationError"));
        System.out.println("Error message: " + message.getText());

//  3- Enter the same username and password.

        driver.navigate().refresh();
        WebElement userName2= driver.findElement(By.id("username"));
        userName2.sendKeys("Admin");

        WebElement password2= driver.findElement(By.id("password"));
        password2.sendKeys("Admin123");

//  4- Hover over the location names one by one and check if the background color changes.

        List<WebElement> locationItems = driver.findElements(By.xpath("//li[@tabindex='0']"));
        List<String> locationItemNames = new ArrayList<>();

        for (WebElement element: locationItems) {
            hardWait(2);
            Actions actions = new Actions(driver);

            String beforeColorChange = element.getCssValue("background-color");

            Action hoverOverLocation = actions.moveToElement(element).build();
            hoverOverLocation.perform();

            String afterColorChange = element.getCssValue("background-color");

            System.out.println("Are the colors before/after same?: " +  beforeColorChange.equals(afterColorChange));

            locationItemNames.add(element.getText());

        }

        System.out.println("Location Icon Names: " + locationItemNames);

//  5- Choose one of the locations randomly and click on "Log In" button.

        int randomIndex = (int)(Math.random()*locationItems.size());

        locationItems.get(randomIndex).click();

        WebElement loginButton2= driver.findElement(By.id("loginButton"));
        loginButton2.click();

//  6- Click on the location icon

        WebElement locationIcon = driver.findElement(By.id("selected-location"));
        locationIcon.click();

//  7- Click on every location one by one and check if it is
//  among the locations on the login page and current location changes.

        List<WebElement> locationsAfterLogin = driver.findElements(By.cssSelector("div>.select>li"));
        List<String> locationsAfterLoginNames = new ArrayList<>();

        for (WebElement location: locationsAfterLogin) {
            location.click();
            locationIcon.click();

            System.out.println("Does the location name equal? " + locationItemNames.contains(location.getText()));
        }
        locationsAfterLogin.get(0).click();

//  8- Verify that admin button is displayed

        WebElement adminButton = driver.findElement(By.cssSelector(".nav-item.identifier"));
        System.out.println("Admin Button is displayed: " + adminButton.isDisplayed());

//  9- Hover over "Admin" button and verify that "My Account" button is displayed.

        Actions actions = new Actions(driver);
        Action hoverOverAdmin = actions.moveToElement(adminButton).build();
        hoverOverAdmin.perform();

        WebElement myAccount = driver.findElement(By.xpath("//ul[@id='user-account-menu']//li//a"));
        System.out.println("My Account Button is displayed: " + myAccount.isDisplayed());

//  10- Click on "My Account" button and verify that the title of the page is "My Account".

        myAccount.click();

        String currentTitle = driver.getTitle();
        String expectedTitle = "My Account";

        System.out.println("My Account Page title is: " + currentTitle.equals(expectedTitle));

//  11- Click on "My Languages" button.

        WebElement languages = driver.findElement(By.linkText("My Languages"));
        languages.click();

//  12- Verify that the title of the page is "My Languages"

        String actualTitle = driver.getTitle();
        String expectedTitleLanguage = "My Languages";

        System.out.println("My Languages Page title is: " + actualTitle.equals(expectedTitleLanguage));

//  13- Select a random language from the dropdown menu.

        WebElement selectLanguage = driver.findElement(By.id("default-locale-field"));

        int randomSelectLanguage = (int) ((Math.random() * 5)+1);

        Select select = new Select(selectLanguage);
        select.selectByIndex(randomSelectLanguage);

//  14- Check and uncheck the boxes one by one. Verify that the box is checked and unchecked each time.

        List<WebElement> checkLanguages = driver.findElements(By.xpath("//input[@type='checkbox']"));

        for (WebElement checkLanguageElements : checkLanguages) {
            if (checkLanguageElements.isSelected()) {
                checkLanguageElements.click();
            }
            checkLanguageElements.click();
            System.out.println("Language is checked: " + checkLanguageElements.isSelected());
            checkLanguageElements.click();
            System.out.println("Language is unchecked: "+ checkLanguageElements.isDisplayed());
            hardWait(2);
        }

//  15- Check all the boxes and verify that they are all checked

        for (WebElement checkLanguageElements : checkLanguages) {
            checkLanguageElements.click();

            System.out.println("Language is checked: " + checkLanguageElements.isSelected());
        }

//  16- Click on The "Save" button and verify error message is displayed.

        WebElement saveButton = driver.findElement(By.cssSelector("input[type='submit']"));
        saveButton.click();

        WebElement text = driver.findElement(By.xpath("//div[@class='text']//p"));

        System.out.println("Error message is: " + text.getText());
        System.out.println("Error message is displayed: " + text.isDisplayed());


    }
}
