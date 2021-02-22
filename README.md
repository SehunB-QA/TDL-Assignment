Test Coverage: 71%

# Firenotes (TDL Assignment) - Sehun Babatunde

A  ToDoList system with a web front end written in (HTMl, CSS, & Javascript) sipported by a backend written in Java & SpringBoot, where data is stored in a memory H2 database. The system deals with managing and CRUD functionality of tasks and storing them into a ToDoList.

## Getting Started

Make sure the prerequisites are installed, ensure environment variables for Java & Maven are set up correctly.

### Prerequisites

```
Java 11 - Run the program
Command Line: (CMD, Mac Terminal ) : Execute the fat Jar file and execute the program.
SpringBoot - Run the H2 database and other spring dependecies 
Git - Clone repo down to your local computer
Apache Maven - Run Unit tests
JavaScript enabled web browser (Google Chrome is best!)  - 
Postman - Test API Requests
Access to opening Port 9094: Where the applicaiton lives (Sends the API requests, hosts the H2 memory database) 
```
### Installing

 First you must clone this repo to your computer  using the git clone command  :

```
git clone https://github.com/SehunB-QA/TDL-Assignment.git
```


Using the same git terminal or a other command line application navigate to the Fat_Jar_File foler and execute the following command : 

```
java -jar ims-0.0.1-jar-with-dependencies.jar

```

## Running the tests

Once your repo is cloned wih the : 

```
git clone https://github.com/SehunB-QA/TDL-Assignment.git
```

Using a command line interface, navigate to the new cloned repo with your terminal/ Command Line interface and you can run maven tests with the command:

```
mvn test
```
### Unit Tests 

Unit Tests were conducted with JUnit, Mockito & Selenium

###  Unit Test Create ToDoList Controller 

```java
 	@Test
	public void createTest() throws Exception {
		// WHEN

		when(this.toDoListService.createList(testToDoList)).thenReturn(this.mapToDTO(testToDoList));

		// THIS
		assertThat(new ResponseEntity<ToDoListDTO>(this.mapToDTO(testToDoList), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(testToDoList));

		
		verify(this.toDoListService, atLeastOnce()).createList(testToDoList);

	}
```
### Selenium Create ToDoList On FrontEnd
```java
@Test
	public void createToDoListFrontEndTest() throws InterruptedException{
		driver.get("http://localhost:9094/index.html");
        
		WebElement toDoListNavBarLink = driver.findElement(By.xpath("/html/body/div[1]/button[1]"));
        toDoListNavBarLink.click();
        
        WebElement toDoListPageLink = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a[1]"));
        toDoListPageLink.click();
        
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
	```
### Postman Create Task API Request

Send an POST request with Postman to the following URL. 
Please make sure a ToDoList has been created with the correct ID to match the task to.
```
http://localhost:9094/tasks/create
```

```json
{
    "colour": "green",
    "description": "very cool",
    "name": "do domething cool",
    "todolist": 
    {
        "id": 1
    }
}

```

## Deployment

The project's H2 database will create the database on execution of the program. But you can reconstruct the Firenotes database structure using the follow SQL statement.

```sql
 drop table if exists `tasks` CASCADE; 
 drop table if exists `to_do_list` CASCADE; 
 
create table to_do_list (id PRIMARY KEY AUTO_INCREMENT, name varchar(255) not null, primary key (id));
create table tasks (id bigint, PRIMARY KEY AUTO_INCREMENT, colour varchar(255) not null, description varchar(255) not null, name varchar(255) not null, todolist_id bigint, primary key (id));
alter table tasks add constraint FKelo7pxri58fjhk4phjx6hgv6n foreign key (todolist_id) references to_do_list on delete cascade;
```


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Sehun Babatunde**  - [SehunB-QA](https://github.com/SehunB-QA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* A big thank you to my team mates:

Cameron  -[CGuthrieQA](https://github.com/CGuthrieQA), 
RaimondsCameron  -[RaimondsMezalsQA](https://github.com/RaimondsMezalsQA),
Henry  - [QAHenryOliverEdwards](https://github.com/QAHenryOliverEdwards), 
for all help, support and laughter during the development of this project. :)

* A thank you to Vinesh, Alan,Reece and my QA Trainers for teaching and support. 


