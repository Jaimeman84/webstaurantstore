# Java + Selenium 4: Template
````
- Automation template for Java using Selenium 4 + Junit 5.
- Examples for test cases, test steps and page objects (POM).
- Support for several browsers using WebDriver Manager.
- Parallel executions.
````
# Pre-Requisites
- __Any IDE__ *(IntelliJ Preferred)*
- __Java__ *(Preferred version 8)*
- __Maven__ ``brew install mvn``
- __Allure__ ``brew install allure``

# Project Structure

- __/driver__ *Folder that includes webdriver manager, capabilities and driver setup.*
  - BrowserType.java
  - DriverFactory.java
  - DriverManager.java
  - DriverSetup.java
- __/pages__ *Includes the PageObject class with generic methods to interact with the DOM elements.*
  - WebstaurantCartPage.java
  - WebstaurantHomePage.java
  - PageObject.java
- __/steps__ *Stores the steps implementations from the POM that will be called in the tests.*
  - WebstaurantstoreSteps.java
- __/tests__ *Classes that will be executed to run the tests.*
  - WebstaurantstoreTest.java
- __/util__ *Folder that includes classes to support the framework.*
  - Helper.java
  - PropertyReader.java
  - PropKey.java
  - Watcher.java
- __/resources__ *Folder that includes files to control the application's execution and features of the tests/reports.*
  - allure.properties
  - application.properties
  - junit-platform.properties
- __pom.xlm__

>All Pages, Steps and Tests described in this section are only examples.

# Driver Folder

- __DriverSetup.java__ </br>
Interface that contains the basic methods to control the driver, browser and capabilities.

- __BrowserType.java__ </br>
Enum class file that implements the ``DriverSetup`` interface which contains the different browsers supported and methods required to set capabilities.

- __DriverFactory.java__ </br>
Abstract class that includes the methods ``getDriver()``, ``quitDriver()`` and ``getBrowser()``.

- __DriverManager.java__ </br>
Class that extends from DriverFactory that includes the basic JUnit5 annotations to control the test's executions, such as ``BeforeAll``, ``AfterAll``, ``BeforeEach``, etc.

# Pages Folder

__PageObject.java__ </br>
Class that extends from the ``DriverManager`` class that includes the basic methods to interact with web elements such as find, click, type and select from dropdown.

All new pages must extend from the ``PageObject`` class, these pages will include all web elements to interact with as well as the methods to execute actions from the page.

These are some of elements referenced with different locators, make sure to add the ``@FindBy`` selenium annotation.

__CSS Selector__ </br>
    ``@FindBy``(css = "#idAttribute") </br>
    private WebElement webElementByCss; </br>
__ID Locator__ </br>
    ``@FindBy``(id = "idAttribute") </br>
    private WebElement webElementById; </br>
__Xpath Expression__ </br>
    ``@FindBy``(xpath = "//div[@id='idAttribute']") </br>
    private WebElement webElementByXpath; </br>

# Steps Folder

The same way we did in the PageObject class, all Steps classes must extend from the ``Driver`` class, besides this the Steps calss will include all the instances of the pages required.

This is an example that shows how to create the instance of an example page.

``private final ExamplePage examplePage = new ExamplePage(getDriver());``

In short terms all methods in this class would be Steps that can be executed within the tests, so adding the allure annotation ``@Step`` above the method that contains page/elements interactions we will be able to use this as step.

__Custom Step Example__ </br>
    ``@Step``("Name of the step as it will be reported in Allure")  </br>
    public void exampleStepAsMethod() {  </br>
        examplePage.customActions();  </br>
    }  </br>

# Console Execution
* __Test Execution(Default Browser):__ ``mvn clean test``
* __Test Execution(Custom Browser):__ ``mvn clean test -DBrowser"<browserName>"``
* __Generate Allure Report:__ ``allure server target/allure-results``
