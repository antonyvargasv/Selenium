package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    @FindBy(xpath = "//input[@id='cb1-edit']")
    private WebElement searchInput;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void searchFor(String item){
        searchInput.sendKeys(item);
        searchInput.sendKeys(Keys.ENTER);
    }

}
