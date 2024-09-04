import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.DetailsPage;
import pageObjects.ResultModel;
import pageObjects.HomePage;
import pageObjects.ResultsPage;

import java.util.concurrent.TimeUnit;

public class ListaMercadoRunner extends BaseWebTest{

    @Test
    public void testMercadoLibreListas(){

        HomePage homePage = new HomePage(driver);
        homePage.searchFor("guitarra electrica");

        ResultsPage resultsPage = new ResultsPage(driver);
        ResultModel expectedResultModel = resultsPage.clickOnRandomItem();

       DetailsPage detailsPage = new DetailsPage(driver);
       ResultModel acResultModel = detailsPage.getDetailInformation();

       Assertions.assertAll(
                () -> Assertions.assertEquals(expectedResultModel.getPrice(), acResultModel.getPrice(),"price dont match"),
               () -> Assertions.assertEquals(expectedResultModel.getName(),acResultModel.getName(),"name dont match")
        );




    }



}
