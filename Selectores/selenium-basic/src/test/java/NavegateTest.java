import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.time.Instant;

import java.util.concurrent.TimeUnit;

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
       // driver.get("https://demo.automationtesting.in/Register.html");
   //OTRA FORMA DE NAVEGAR:
        driver.navigate().to("https://demo.automationtesting.in/Register.html");

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
//============ ESPERAS ============================================
    //Implícitas: nos permite indicar al WebDriver cuanto debe esperar por un elemento antes de arrojar una excepción
    // al no encontrar el elemento, este tiempo es usado por WebDriver para todos los elementos.

    @Test
    public void implicitWaitExample() {
        driver.get("https://www.google.com");
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        Instant start = Instant.now();

        try {
            driver.findElement(By.id("//img[@alt='Google']ERROR"));
        } catch (Exception exc) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("************************");
            System.out.println("time 1: " + timeElapsed.getSeconds() + " seconds");
            System.out.println("************************");

        }
        start = Instant.now();
        try {
            driver.findElement(By.id("//img[@alt='Google']ERROR"));
        } catch (Exception exc) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("************************");
            System.out.println("time 2: " + timeElapsed.getSeconds() + " seconds");
        }
    }
    //La espera por defecto es 0 si no colocamos nada:
    @Test
    public void defaultWait(){
        driver.get("https://www.google.com");
        Instant start = Instant.now();
        try {
            driver.findElement(By.id("//img[@alt='Google']ERROR"));
        } catch (Exception exc) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("************************");
            System.out.println("time 1: " + timeElapsed.getSeconds() + " seconds");
            System.out.println("************************");
        }

    }

    //Esperas explícitas: Nos permite indicar al WebDriver cuanto debe esperar por un elemento de forma
    //específica hasta que una condición específica se cumpla. Dicha condición es llamada con cierta
    //frecuencia hasta que transcurre el tiempo de espera.
    //condiciones de espera: https://github.com/SeleniumHQ/selenium/blob/trunk/java/src/org/openqa/selenium/support/ui/ExpectedConditions.java
    @Test
    public void explicitWaitExample(){
        driver.get("https://www.google.com");
        driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        Instant start = Instant.now();
        try {
            driver.findElement(By.id("//img[@alt='Google']ERROR"));
        } catch (Exception exc) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("************************");
            System.out.println("time 1: " + timeElapsed.getSeconds() + " seconds");
            System.out.println("************************");
        }
        //Se recomienda colocar en partes especificas donde sabemos que va demorar en cargar
        Instant start2 = Instant.now();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("//img[@alt='Google']ERROR")));
        } catch (Exception exc) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start2, end);
            System.out.println("************************");
            System.out.println("time explicit: " + timeElapsed.getSeconds() + " seconds");
            System.out.println("************************");
        }
    }
    @Test
    public void explicitWaitExamplevsImplicit(){
        driver.get("https://www.google.com");
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        Instant start = Instant.now();
        try {
            driver.findElement(By.id("//img[@alt='Google']ERROR"));
        } catch (Exception exc) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("************************");
            System.out.println("time 1: " + timeElapsed.getSeconds() + " seconds");
            System.out.println("************************");
        }
        //Se recomienda colocar en partes especificas donde sabemos que va demorar en cargar
        Instant start2 = Instant.now();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("//img[@alt='Google']ERROR")));
        } catch (Exception exc) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start2, end);
            System.out.println("************************");
            System.out.println("time explicit: " + timeElapsed.getSeconds() + " seconds");
            System.out.println("************************");
        }
    }

    //Fluent waits, nos permite definir el tiempo máximo a esperar por una determinada condición del elemento.
    //También nos permite definir la frecuencia con la que el driver validará si la condición se cumple antes de arrojar una excepción
    //normalmente la frecuencia es de 500 milisegundos pero con Fluent waits se puede cambiar.
    @Test
    public void fluentWaitExample(){
        driver.get("https://www.google.com");

        FluentWait fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(10L));
        fluentWait.pollingEvery(Duration.ofSeconds(2L));
        fluentWait.ignoring(NoSuchElementException.class);

        Instant start = Instant.now();

        try{
            fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.id("algo")));
        }
        catch (Exception ex){
            System.out.println("******** exception *******: " + ex.getMessage());
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("************************");
        System.out.println("time fluent: " + timeElapsed.getSeconds() + " seconds");
        System.out.println("************************");

    }

//Navegar a una URL String con el index de forma local.
    @Test
    public void navegarURLLocal() throws InterruptedException {
        driver.navigate().to("file://C://Users//Antony//Downloads//formcontact.html");
        WebElement select = driver.findElement(By.xpath("//select[@id='id_contact']"));
        Select sel = new Select(select);
        sel.selectByIndex(2);

        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("antony@gmail.com");

        WebElement order = driver.findElement(By.xpath("//input[@id='id_order']"));
        order.sendKeys("8");

        WebElement mensaje = driver.findElement(By.xpath("//textarea[@id='message']"));
        mensaje.sendKeys("mensaje de prueba");

        Thread.sleep(3000);
        WebElement btn = driver.findElement(By.xpath("//button[@type='submit']"));
        btn.click();

        String msj = driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText();

        System.out.println("el mensaje es: ********: "+msj);
        assertTrue(msj.contains("Your message has been successfully sent to our"),"se espera que el mensaje tenga:Your message has been successfully sent to our, sin embargo fue enviado"+msj);



    }











    }










