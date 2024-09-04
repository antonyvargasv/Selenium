import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.ContactUSPageFactory;
import pageObjects.ContactUSsinFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactUsRunner {
    private WebDriver driver;
    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterEach
    public void tearDown(){
       driver.quit();
    }

    @Test
    public void runnerContactUSsinFacyory (){
        driver.navigate().to("file://C://Users//Antony//Downloads//formcontact.html");
        //Creación de contactUs de la clase ContactUs enviando al constructor el driver:
        ContactUSsinFactory contactUs = new ContactUSsinFactory(driver);
        //Con el objeto creado, se utiliza sus métodos enviando los parámetros requeridos
        contactUs.writeSelect(2);
        contactUs.writeEmail("antony@gmail.com");
        contactUs.writeMensaje("mensaje de prueba");
        contactUs.writeOrder("8");
        contactUs.submit();
        contactUs.validar("Your message has been successfully sent to our team.");
    }

    @Test
    public void runnerContactUSconPageFactory (){
        driver.navigate().to("file://C://Users//Antony//Downloads//formcontact.html");
        //Creación de contactUs de la clase ContactUs enviando al constructor el driver:
        ContactUSPageFactory contactUs2 = new ContactUSPageFactory(driver);
        //Con el objeto creado, se utiliza sus métodos enviando los parámetros requeridos
        contactUs2.writeSelect("Webmaster");
        contactUs2.writeEmail("antony@gmail.com");
        contactUs2.writeMensaje("mensaje de prueba");
        contactUs2.writeOrder("8");
        contactUs2.submit();
       contactUs2.validar("Your message has been successfully sent to our team.");
    }
}
