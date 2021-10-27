package week4day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnWebTable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://www.leafground.com/pages/table.html");
		
		//findTable
		WebElement webtable =driver.findElement(By.id("table_id"));
		//getting rows
		List<WebElement>tableRows = webtable.findElements(By.tagName("tr"));
		System.out.println("Number Of Rows:" +tableRows.size());
		
		//Get count of Columns
		//pick any single row
		WebElement webFirstRow = tableRows.get(1);
		List<WebElement>tableColumns = webFirstRow.findElements(By.tagName("td"));
		System.out.println("Number Of Columns:" +tableColumns.size());
		
		for(WebElement alldata : tableRows) {
			String text=alldata.getText();
			System.out.println(text);
			
		}
		for(int i=1;i<tableRows.size();i++) {
			WebElement getrow=tableRows.get(i);
			List<WebElement>individual=getrow.findElements(By.tagName("td"));
			String text = individual.get(1).getText();
			System.out.println(text);
			
		}

	}

}
