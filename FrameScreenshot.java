package week4day1;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameScreenshot {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/frame.html");
		
        //inside frame
		  driver.switchTo().frame(0);
		  WebElement firstButton = driver.findElement(By.id("Click"));
		  firstButton.click();
		//ScreenShot
		    File src1 = firstButton.getScreenshotAs(OutputType.FILE);
		    File dst = new File("./snaps/frame.png");
		    FileUtils.copyFile(src1, dst);
		 
		//Nested Frame
		    driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			driver.switchTo().frame("frame2");
			driver.findElement(By.id("Click1")).click();
			
		//3. Identifying total number of frames
			driver.switchTo().defaultContent();
			List<WebElement> totalFrames =driver.findElements(By.tagName("iframe"));
			System.out.println("Total Count of Frames: "+totalFrames.size());
			
		
	}

}
