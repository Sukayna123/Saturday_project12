package Project12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

       
    }
}
