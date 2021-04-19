package myMavTestProj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PlanitTest {

	WebDriver driver;
	String appURL = "http://jupiter.cloud.planittesting.com";
	String chromeDriverPath = "C:\\Users\\Multiculture NZ2\\Documents\\Alag\\ChromeDriver\\chromedriver.exe";
	String expErrMsg1 = "We welcome your feedback - but we won't get it unless you complete the form correctly.";
	String expErrMsg2 = "Forename is required";
	String expErrMsg3 = "Email is required";
	String expErrMsg4 = "Message is required";
	String validMsg1 = "We welcome your feedback - tell it how it is.";
	String expInvEmailErrMsg = "Please enter a valid email";
	WebElement contactLink;
	WebElement shopLink;
	WebElement element;
	
	@BeforeMethod
	public void openURL()
	{
		//set the path of webdriver
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		
		//create an object of webdriver
		driver = new ChromeDriver();
		
		//maximize the window
		driver.manage().window().maximize();
		
		//open planit testing link
		driver.get(appURL);
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		//close driver
		driver.close();
	}
	
	public void gotoContact() {
		
		//open application URL
		openURL();

		//find element "Contact" and click at it
		contactLink = driver.findElement(By.linkText("Contact"));
		contactLink.click();
	}
	
	public void gotoShop() {
		
		//open application URL
		openURL();

		//find element "Shop" and click at it
		shopLink = driver.findElement(By.linkText("Shop"));
		shopLink.click();
	}
	
	public boolean validateError() {

			//navigate to contact page		
			gotoContact();
			
			////wait for Submit link to become available and click at it
			element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.linkText("Submit")));
			element.click();
		
			boolean cond1=false, cond2=false, cond3=false, cond4=false;
			
			//compare actual error message at page top with expected page top error message
			String actErrMsg1 = driver.findElement(By.cssSelector("#header-message")).getText();
			if (expErrMsg1.equals(actErrMsg1))
				cond1=true;
			
			//compare actual forename error message with expected forename error message
			String actErrMsg2 = driver.findElement(By.id("forename-err")).getText();
			if (expErrMsg2.equals(actErrMsg2))
				cond2=true;
			
			//compare actual email error message with expected email error message
			String actErrMsg3 = driver.findElement(By.id("email-err")).getText();
			if (expErrMsg3.equals(actErrMsg3))
				cond3=true;
			
			//compare actual "message field" error with expected "message field" error
			String actErrMsg4 = driver.findElement(By.id("message-err")).getText();
			if (expErrMsg4.equals(actErrMsg4))
				cond4=true;
			
			//if all actual errors match expected errors return true, else return false
			if (cond1 && cond2 && cond3 && cond4)
				return true;
			else
				return false;
	}
	
	public boolean populateMandatoryFields() {
		
		boolean cond;
		
		//wait for forename field to become available
		element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("forename")));
				
		//populate mandatory fields with valid data
		element.sendKeys("Alag");
		driver.findElement(By.id("email")).sendKeys("alag@gmail.com");
		driver.findElement(By.id("message")).sendKeys("The site looks cool, user friendly");
		
		//check if the actual "valid message" matches the expected "valid message" and return true, else return false
		String actMsg1 = driver.findElement(By.cssSelector("#header-message")).getText();
		if (validMsg1.equals(actMsg1))
			cond=true;
		else
			cond=false;
		
		return cond;
	}
	
	public boolean chkSucSub() {

		//navigate to contact page
		gotoContact();

		//populate mandatory fields with valid data and click Submit
		if (populateMandatoryFields())
			driver.findElement(By.linkText("Submit")).click();
		
		//wait for the Back button in the page to become available
		element = (new WebDriverWait(driver, 15)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn']")));
		
		//check the successful message at page top contains string "Thanks" and return true, else return false
		String actText = driver.findElement(By.tagName("strong")).getText();
		String expText = "Thanks";
		boolean cond = false;
		if (actText.contains(expText))
			cond=true;
		else
			cond=false;		

		return cond;
	}
	
	public boolean chkInvalidData() {
		
		//navigate to Contact page
		gotoContact();
		
		boolean cond1=false, cond2=false;
		
		//wait for forename field to become available
		element = (new WebDriverWait(driver, 15)).until(ExpectedConditions.elementToBeClickable(By.id("forename")));
		
		//populate mandatory fields with invalid data
		element.sendKeys("###");
		driver.findElement(By.id("email")).sendKeys("a");
		driver.findElement(By.id("message")).sendKeys("#@$");
		
		//capture actual error message at page top and compare with expected page top error message
		String actErrMsg1 = driver.findElement(By.cssSelector("#header-message")).getText();
		if (expErrMsg1.equals(actErrMsg1))
			cond1=true;
		
		//only email field displays error message on invalid data, forename and message fields do no display any error message on invalid data
		//capture actual invalid email error message and compare with expected invalid email error message
		String actInvEmailErrMsg = driver.findElement(By.id("email-err")).getText();
		if (expInvEmailErrMsg.equals(actInvEmailErrMsg))
			cond2=true;
		
		//if both error messages are as expected return true, else return false
		if (cond1 && cond2)
			return true;
		else
			return false;
	}
	
	public boolean verifyShoppingCart() {
		
		//navigate to Shop page
		gotoShop();
		
		//wait for the first instance of Buy button to become available in the page
		element = (new WebDriverWait(driver, 15)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn btn-success']")));

		//Click at Funny Cow twice
		for (int i=0; i<2; i++)
		{
			driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[6]/div/p/a[@class='btn btn-success']")).click();
		}
		
		//click at Fluffy Bunny once
		driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[4]/div/p/a[@class='btn btn-success']")).click();
		
		//click the at the Cart link
		driver.findElement(By.cssSelector("li#nav-cart")).click();
		
		//wait for "Empty Cart" element to become available
		element = (new WebDriverWait(driver, 15)).until(ExpectedConditions.elementToBeClickable(By.linkText("Empty Cart")));
	
		//check all individual item description, unit price, units, subtotal and total are displayed as expected
		if ((driver.findElement(By.xpath("//td[contains(text(),'Funny Cow')]"))).isDisplayed() && (driver.findElement(By.xpath("//td[contains(text(),'$10.99')]"))).isDisplayed() && (driver.findElement(By.xpath("//input[@value='2']"))).isDisplayed() && (driver.findElement(By.xpath("//td[contains(text(),'$21.98')]"))).isDisplayed() && (driver.findElement(By.xpath("//td[contains(text(),'Fluffy Bunny')]"))).isDisplayed() && (driver.findElement(By.xpath("//td[contains(text(),'$9.99')]"))).isDisplayed() && (driver.findElement(By.xpath("//input[@value='1']"))).isDisplayed() && (driver.findElement(By.xpath("//td[contains(text(),'$9.99')]"))).isDisplayed() && (driver.findElement(By.xpath("//strong[contains(text(),'31.97')]"))).isDisplayed())
			return true;
		else
			return false;

	}

}
