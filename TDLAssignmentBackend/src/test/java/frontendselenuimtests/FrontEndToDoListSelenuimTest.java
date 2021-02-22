package frontendselenuimtests;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public void TestCreate() throws InterruptedException{
		driver.get("http://localhost:9094/index.html");
		 WebDriverWait wait = new WebDriverWait(driver, 30);
        
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/form/div/p[1]")));
		
		assertEquals("List Created!", listCreatedText.getText());
	}
	 
	@Test
	public void TestUpdate() throws InterruptedException{
		driver.get("http://localhost:9094/index.html");
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		   
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
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/form/div/form/div/p[1]")));
		
		assertEquals("List updated!", listUpdatedText.getText());
	}
	
	
	@Test
	public void DisplayTest() throws InterruptedException{
		driver.get("http://localhost:9094/index.html");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		   
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
       
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/form/div/p[1]")));
		
		assertEquals("List Displayed!", listDisplayedText.getText());
		
	}
	
	
	
	@Test
	public void TestLastDelete() throws InterruptedException{
		driver.get("http://localhost:9094/index.html");
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		
		 WebElement toDoListNavBarLink = driver.findElement(By.xpath("/html/body/div[1]/button[1]"));
		   toDoListNavBarLink.click();
		   
		   WebElement deleteToDoListPageLink = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a[5]"));
	       deleteToDoListPageLink.click();
	       
	       WebElement getToDoListIDBox = driver.findElement(By.xpath("//*[@id=\"listIDInput\"]"));
	       getToDoListIDBox.click();
	       getToDoListIDBox.sendKeys("1");
	       
	    
	       WebElement pageBackground = driver.findElement(By.xpath("/html/body"));
             pageBackground.click();
	        
	       
	       WebElement deleteListButton = driver.findElement(By.cssSelector("#deleteListButton"));
	      assertTrue(deleteListButton.isEnabled());
	      deleteListButton.click();

	       
	       WebElement listDeletedText = driver.findElement(By.cssSelector("#deletedListSuccessText"));
	       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#deletedListSuccessText")));
			
			assertEquals("List Deleted!", listDeletedText.getText());
	
	}
	
	
	
	@AfterAll
	public void tearDown()
	{
		driver.close();
	}

}
