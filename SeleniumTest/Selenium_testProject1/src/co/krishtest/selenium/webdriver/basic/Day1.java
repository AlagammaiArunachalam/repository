package co.krishtest.selenium.webdriver.basic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day1 {
	
	WebDriver driver;
	JavascriptExecutor jse;
	
	public void invokeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Multiculture NZ2\\Documents\\Alag\\ChromeDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.get("http://www.google.co.nz");
			searchCourse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchCourse() {
		try {
			driver.findElement(By.name("q")).sendKeys("Selenium");
			Thread.sleep(3000);
			driver.findElement(By.name("btnK")).click();
			jse = (JavascriptExecutor)driver;
			Thread.sleep(3000);
			jse.executeScript("scroll(0,1500)");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//aria-label[contains(text(),'Page 2')]")).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello God");
		Day1 test1 = new Day1();
		test1.invokeBrowser();
	}

}
