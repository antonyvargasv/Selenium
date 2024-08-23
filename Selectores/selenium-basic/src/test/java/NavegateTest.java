import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavegateTest {
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

//Test para practicar selectores
    @Test
    public void navegateToRegister() throws InterruptedException {
        driver.get("https://demo.automationtesting.in/Register.html");

//====================  Xpath - Ctrl+F: ==================================
        //Buscar por el nombre del atributo elemento[@propiedad/atributo='nombreDePropiedad']

        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Antony");
        driver.findElement(By.xpath("//input[@ng-model='LastName']")).sendKeys("Vargas");
        driver.findElement(By.xpath("//input[@value='Male']")).click();
        driver.findElement(By.xpath("//input[@type='tel']"));

        //Buscar por el contenido del elemento elemento[contains(text(),'textoDelElemento')]:
        //h2[contains(text(),'Register')]

        //Navegar entre elemento y buscar por parte del contenido
        driver.findElement(By.xpath("//div//input[contains(@type,'em')]")).sendKeys("correo@gmail.com");
        //select[contains(@id,'year')]

        //otros ejemplos xpath:
        //**
        // 	//Navegar entre elementos con mismo valor:
        //	//input[@type='checkbox'][2]   -- https://the-internet.herokuapp.com/checkboxes
        //	//li[@class='rct-node rct-node-parent rct-node-collapsed'][3]
        //  //body/div[@id='app']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/ol[1]/li[1]/span[1]/label[1]/span[1]/*[1]
        // **//

//====================  CSS Selector - Ctrl+F: **recomendable utilizar ==================================
        //Por clase  elemento.nombreClase
        // eje: button.btn-primary
        //Por ID  elemento#nombre_id
        driver.findElement(By.cssSelector("input[id='firstpassword']")).sendKeys("A.123456");
        driver.findElement(By.cssSelector("input[id='secondpassword']")).sendKeys("A.123456");
        driver.findElement(By.cssSelector("button#submitbtn")).click();
        //**
        // 	Ubicar por el nombre de la clase => input.inputtext
        //	Ubicar por el valor del id => input#email ****LO MAS RECOMENDABLE
        //	Ubicar por otro atributo => button[name='login']
        //	Ubicar por class que tengan varios el mismo nombre(Caso lista) =>  ol[class*='ui-search-layout'] li
        //	Navegar entre elementos =>div ol li label[for='tree-node-home'] span.rct-checkbox
        // **//
        Thread.sleep(3000);

    }

//Test para practicar Assert https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html
    @Test
    public void navigateToMeradoLibre(){

        driver.get("https://mercadolibre.com/");
        driver.findElement(By.xpath("//a[@id='PE']")).click();

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl="https://www.mercadolibre.";
        assertTrue(currentUrl.contains(expectedUrl),
                "se espera que la url contenga "+expectedUrl+" pero se obtuvo "+currentUrl);
    }

//Test para practicar Select
    @Test
    public void contactUsSelect() throws InterruptedException {

        driver.get("http://www.automationpractice.pl/index.php?controller=contact");
        //Primero volver el elemento en un WebElement y después a un tipo Select:
        WebElement select = driver.findElement(By.xpath("//select[@id='id_contact']"));
        Select selectWeb = new Select(select);
        //Escoger por índice desde el valor 0:
        selectWeb.selectByIndex(2);
        Thread.sleep(3000);
        selectWeb.selectByIndex(0);
        Thread.sleep(3000);
        selectWeb.selectByIndex(1);
        Thread.sleep(3000);

        //Escoger por el texto visible
        selectWeb.selectByVisibleText("Webmaster");
        Thread.sleep(3000);

        //Escoger por value, hace referencia al valor de la propiedad en html:
        selectWeb.selectByValue("0");
        Thread.sleep(3000);

    }



}
