package week4day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IRTCWindowHandling {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.irctc.co.in/");
		Thread.sleep(5000);
		//SweetAlert
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		//FlightsTab
		driver.findElement(By.linkText("FLIGHTS")).click();
		//Collection of Windows
		Set<String>totalWindows=driver.getWindowHandles();
		List<String>WindowsList=new ArrayList<String>(totalWindows);
		//Switching Acess New Window
		driver.switchTo().window(WindowsList.get(1));
		//Retriving E-mail
		String customerCareEmail = driver.findElement(By.xpath("//a[@class='d-block font-weight-bold']")).getText();
		System.out.println("Customer Care EMail ID:"+customerCareEmail);
		//Switching back t parent Window
		driver.switchTo().window(WindowsList.get(0));
		//close
		driver.close();

	}

}
