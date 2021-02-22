package com.qa.tdlassignmentbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FrontEndToDoListSelenuimTest {
private  WebDriver driver;
    
	@BeforeAll
	
	public  void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366,768));
	}
	
	@Test
	public void createToDoListFrontEndTest() throws InterruptedException{
		driver.get("http://localhost:9094/index.html");
        
		WebElement toDoListNavBarLink = driver.findElement(By.xpath("/html/body/div[1]/button[1]"));
        toDoListNavBarLink.click();
        
        WebElement createToDoListPageLink = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a[1]"));
        createToDoListPageLink.click();
        
        WebElement toDoListTextBox = driver.findElement(By.xpath("/html/body/form/div/input"));
        toDoListTextBox.click();
        toDoListTextBox.sendKeys("SHOPPING LIST");
        
        WebElement pageBackground = driver.findElement(By.xpath("/html/body/div"));
        pageBackground.click();
        
        WebElement createNewListButton = driver.findElement(By.xpath("/html/body/form/div/button"));
        createNewListButton.click();
        
        WebElement listCreatedText = driver.findElement(By.xpath("/html/body/form/div/p[1]"));
        
		
		assertEquals("List Created!", listCreatedText.getText());
	}
	
	@Test
	public void updateToDoListNameFrontEndTest() throws InterruptedException{
		driver.get("http://localhost:9094/index.html");
		   
	   WebElement toDoListNavBarLink = driver.findElement(By.xpath("/html/body/div[1]/button[1]"));
	   toDoListNavBarLink.click();
	   
	   WebElement updateToDoListPageLink = driver.findElement(By.xpath("/html/body/div/div[1]/a[2]"));
       updateToDoListPageLink.click();
       
       WebElement getToDoListIDBox = driver.findElement(By.xpath("/html/body/form/div/label/input"));
       getToDoListIDBox.click();
       getToDoListIDBox.sendKeys("1");
       
       WebElement getToDoListIDButton = driver.findElement(By.xpath("/html/body/form/div/button"));
       getToDoListIDButton.click();
       
       WebElement updateListNameTextBox = driver.findElement(By.xpath("/html/body/form/div/form/div/label[2]/input"));
       updateListNameTextBox.click();
       updateListNameTextBox.clear();
       updateListNameTextBox.sendKeys("Updated List Name");
       
       WebElement UpdateListNameButton = driver.findElement(By.xpath("/html/body/form/div/form/div/button"));
       UpdateListNameButton.click();
		
       WebElement listUpdatedText = driver.findElement(By.xpath("/html/body/form/div/form/div/p[1]"));
		
		assertEquals("List updated!", listUpdatedText.getText());
	}
	
	
	@Test
	public void displayToDoListNameFrontEndTest() throws InterruptedException{
		driver.get("http://localhost:9094/index.html");
		   
	   WebElement toDoListNavBarLink = driver.findElement(By.xpath("/html/body/div[1]/button[1]"));
	   toDoListNavBarLink.click();
	   
	   WebElement displayToDoListPageLink = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a[3]"));
       displayToDoListPageLink.click();
       
       WebElement getToDoListIDBox = driver.findElement(By.xpath("/html/body/form/div/label/input"));
       getToDoListIDBox.click();
       getToDoListIDBox.sendKeys("1");
       
       WebElement pageBackground = driver.findElement(By.xpath("/html/body/div"));
       pageBackground.click();
       
       
       WebElement getToDoListIDButton = driver.findElement(By.xpath("/html/body/form/div/button[1]"));
       getToDoListIDButton.click();
       
     
		
       WebElement listDisplayedText = driver.findElement(By.xpath("/html/body/form/div/p[1]"));
       
       WebElement displayedTable = driver.findElement(By.xpath("/html/body/form/div/div[1]/table"));
		
		assertEquals("List Displayed!", listDisplayedText.getText());
		assertTrue(displayedTable.isDisplayed());
	}
	
	
	
	
	
	
	
	@AfterAll
	public void tearDown()
	{
		driver.close();
	}

}
