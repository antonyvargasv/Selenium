package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactUSsinFactory {
    //Creación de un constructor para la clase, tiene que recibir como parámetro el driver.
    private WebDriver driver;
    private Wait wait;
    private String selectLocator ="//select[@id='id_contact']";
    private String emailLocator ="//input[@id='email']";
    private String orderLocator = "//input[@id='id_order']";
    private String mensajeLocator ="//textarea[@id='message']";
    private String btnLocator ="//button[@type='submit']";
    private String msjLocator ="//p[@class='alert alert-success']";

    public ContactUSsinFactory(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(5L));
    }

    public void writeSelect(Integer valor){
        WebElement select = driver.findElement(By.xpath(selectLocator));
        Select sel = new Select(select);
        sel.selectByIndex(valor);
    }
    public void writeEmail(String emailText){
        WebElement email = driver.findElement(By.xpath(emailLocator));
        email.sendKeys(emailText);
    }
    public void writeOrder(String orderText){
        WebElement order = driver.findElement(By.xpath(orderLocator));
        order.sendKeys(orderText);
    }

    public void writeMensaje(String msjText){
        WebElement mensaje = driver.findElement(By.xpath(mensajeLocator));
        mensaje.sendKeys(msjText);
    }
    public void submit(){
        WebElement btn = driver.findElement(By.xpath(btnLocator));
        btn.click();
    }
    public void validar(String expectedText){
        String msj = driver.findElement(By.xpath(msjLocator)).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(msjLocator)));
        System.out.println("el mensaje es: ********: "+msj);
        assertTrue(msj.contains(expectedText), "se espera que el mensaje tenga: "+expectedText+" , sin embargo fue enviado: "+msj);

    }





}
