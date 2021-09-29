package week4day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String desiredTitle = "View Contact | opentaps CRM";
		
		driver.get("http://leaftaps.com/opentaps/control/main");
		//Locate & Enter UserName, Password
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		
		driver.findElement(By.linkText("CRM/SFA")).click();
	
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//ul[@class='shortcuts']/li[4]/a")).click();
		
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
		
		Set<String> windowsCountSet = driver.getWindowHandles();
		List<String> windowsCountList = new ArrayList<String>(windowsCountSet);
		
		driver.switchTo().window(windowsCountList.get(1));
		driver.findElement(By.name("firstName")).sendKeys("Shobhana");
		driver.findElement(By.xpath("//button[text()='Find Contacts']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//a[@class='linktext'])[1]")).click();
		driver.switchTo().window(windowsCountList.get(0));
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Set<String> windowsCountSetTo = driver.getWindowHandles();
		List<String> windowsCountListTo = new ArrayList<String>(windowsCountSetTo);
		driver.switchTo().window(windowsCountListTo.get(1));
		driver.findElement(By.name("firstName")).sendKeys("Shobhana");
		driver.findElement(By.xpath("//button[text()='Find Contacts']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//a[@class='linktext'])[6]")).click();
		driver.switchTo().window(windowsCountListTo.get(0));
		
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String title = driver.getTitle();
		if(title.equals(desiredTitle)){
			System.out.println("Title is Matched!");
		}else {
			System.out.println("Title is not Matched!");

	}

}
}


	