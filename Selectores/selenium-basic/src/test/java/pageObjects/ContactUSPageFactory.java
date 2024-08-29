package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactUSPageFactory {
    //Creación de un constructor para la clase, tiene que recibir como parámetro el driver.
    private WebDriver driver;
    private Wait wait;
    @FindBy(xpath = "//select[@id='id_contact']")
    private WebElement selectLocator;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailLocator;
    @FindBy(xpath = "//input[@id='id_order']")
    private WebElement orderLocator;
    @FindBy(xpath = "//textarea[@id='message']")
    private WebElement mensajeLocator;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnLocator;
    @FindBy(xpath = "//p[@class='alert alert-success']")
    private WebElement msjLocator;

    public ContactUSPageFactory(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        //Agregar el PageFactory
        PageFactory.initElements(driver,this);
    }

    public void writeSelect(String valor){
        Select sel = new Select(selectLocator);
        sel.selectByVisibleText(valor);
    }
    public void writeEmail(String emailText){
        emailLocator.sendKeys(emailText);
    }
    public void writeOrder(String orderText){
        orderLocator.sendKeys(orderText);
    }
    public void writeMensaje(String msjText){
        mensajeLocator.sendKeys(msjText);
    }
    public void submit(){
        btnLocator.click();
    }
    public void validar(String expectedText){
        String msj = msjLocator.getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='alert alert-success']")));
        System.out.println("el mensaje es: ********: "+msj);
        assertTrue(msj.contains(expectedText), "se espera que el mensaje tenga: "+expectedText+" , sin embargo fue enviado: "+msj);

    }


}
