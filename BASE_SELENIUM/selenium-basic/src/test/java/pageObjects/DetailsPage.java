package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DetailsPage {
     @FindBy(xpath = "//span[@itemprop='offers']")
     private WebElement priceInDetail;

     @FindBy(xpath = "//h1[@class='ui-pdp-title']")
     private WebElement nameInDetail;

     public DetailsPage(WebDriver driver){
         PageFactory.initElements(driver,this);
     }

     public ResultModel getDetailInformation(){
         ResultModel resultModel = new ResultModel();
         resultModel.setName(nameInDetail.getText());
         resultModel.setPrice(priceInDetail.getText());
         return resultModel;
     }


}
