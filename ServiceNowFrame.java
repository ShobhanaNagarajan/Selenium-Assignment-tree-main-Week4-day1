package week4day1;



import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowFrame {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://dev85707.service-now.com/navpage.do");
		
		driver.get("https://dev85707.service-now.com/navpage.do");
		//Access switch to Frame
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("H1hskMU8OmaO");
		driver.findElement(By.id("sysverb_login")).click();
		driver.findElement(By.id("filter")).sendKeys("Incident"+Keys.ENTER);
		driver.findElement(By.xpath("(//div[text()='Create New'])[1]")).click();
		//Access switch to Frame
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		//Collection of Windows-Lookup
		Set<String> lookupWindowsSet = driver.getWindowHandles();
		List<String> lookupWindowsList = new ArrayList<String>(lookupWindowsSet);
		//Storing a parentWindow for easy recall in further lines of Code
		String parentWindow = driver.getWindowHandle();
		driver.switchTo().window(lookupWindowsList.get(1));
		//Search by name
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Wayne"+Keys.ENTER);
		driver.findElement(By.linkText("Wayne Webb")).click();
		//Back to parentWindow
		driver.switchTo().window(parentWindow);
		//Frame Switch
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("lookup.incident.short_description")).click();
		//Collection Of Windows - Short Description
		Set<String>descrWindowsSet = driver.getWindowHandles();
		List<String>descrWindowsList = new ArrayList<String>(descrWindowsSet);
		//Windows Toggled
		driver.switchTo().window(descrWindowsList.get(1));
		//Required Short Description selected and windows switched back to parent
		driver.findElement(By.xpath("//a[text()='Request for a Blackberry']")).click();
		driver.switchTo().window(parentWindow);
		//FrameSwitch
		driver.switchTo().frame("gsft_main");
		//Retrieval of Incident Number
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		String desiredTitle=incidentNumber+" | Incident | ServiceNow";
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		//Searching with the retrieved Incident Number
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(incidentNumber+Keys.ENTER);
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		//Page ScreenShot
		File pageSrc = driver.getScreenshotAs(OutputType.FILE);
		File pageDstn = new File("./snaps/ImageServiceNow.png");
		FileUtils.copyFile(pageSrc, pageDstn);
		if(driver.getTitle().equals(desiredTitle)) {
			System.out.println("Incident Created Succesfully!");
		} else {
			System.out.println("Error in Incident Creation");
		}
		driver.switchTo().window(parentWindow);
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.description")).sendKeys("OK");
	}
}