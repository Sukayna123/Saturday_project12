package Project12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Project_12 extends UtilityClass{
    public static void main(String[] args) {
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        WebElement userName= driver.findElement(By.id("username"));
        userName.sendKeys("Admin");

        WebElement password= driver.findElement(By.id("password"));
        password.sendKeys("Admin123");

        WebElement loginButton= driver.findElement(By.id("loginButton"));
        loginButton.click();

        WebElement message=driver.findElement(By.id("sessionLocationError"));
        System.out.println(message.getText());

//        4- Hover over the location names one by one and check if
//   the background color changes.

        List<WebElement> locationItems = driver.findElements(By.xpath("//li[@tabindex='0']"));


        for (WebElement element: locationItems) {
            hardWait(2);
            Actions actions = new Actions(driver);

            String beforeColorChange = element.getCssValue("background-color");

            Action hoverOverLocation = actions.moveToElement(element).build();
            hoverOverLocation.perform();

            String afterColorChange = element.getCssValue("background-color");

            System.out.println(beforeColorChange.equals(afterColorChange));
        }

//        5- Choose one of the locations randomly and click on
//   "Log In" button.

        int randomIndex = (int)(Math.random()*locationItems.size());


///trying a code to see
        //Helooooooooooooooooooooooooooooooooooo



       
    }
}
