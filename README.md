# Playwright Automation: A Comprehensive Guide to Java API Testing

## Introduction

In today's digital age, automation testing has become an essential aspect of software development. One of the most popular tools that have come to the forefront is Playwright, a Node.js library used for browser automation. In this blog post, we will discuss how to use Playwright for Java API testing, covering its installation, basic functionalities, and how to use it for API testing.

## Prerequisites to learn Playwright

*   Solid understanding of JavaScript
*   Familiarity with HTML and CSS
*   Experience with Node.js and npm
*   Understanding of asynchronous programming
*   Familiarity with testing principles and methodologies
*   Experience with debugging tools
*   Basic understanding of HTTP and RESTful APIs
*   Familiarity with containerization (optional)
*   Understanding of CI/CD pipelines (optional)

## Playwright Introduction

[Playwright](https://playwright.dev/) was created specifically to accommodate the needs of end-to-end testing. Playwright supports all modern rendering engines including Chromium, WebKit, and Firefox. Test on Windows, Linux, and macOS, locally or on CI, headless or headed with native mobile emulation.

## Installation of Playwright

The first step in using Playwright is getting it installed in your environment. Playwright is a Node.js library, so you'll need to have Node.js and npm (node package manager) installed on your machine. Once Node.js is installed, you can install Playwright using the npm install command: `npm install playwright`. This will download Playwright and browsers (Chromium, Firefox, and WebKit) that it will automate.

## **Supported languages**

*   JavaScript and TypeScript
*   Python
*   Java
*   .NET

## Which will run fast - Java / Nodejs

According to some sources, Node.js may have an edge over Java in terms of speed and concurrency, as it is based on JavaScript, which is a lightweight and interpreted language that runs on a single thread with an event-driven architecture

## Basic Functionalities of Playwright

Playwright has several powerful functionalities that make browser automation easy and efficient. It supports multiple browsers, allowing you to switch between Chromium, Firefox, and WebKit with ease. It also supports headless mode, which means you can run your automation scripts without opening the browser. Other key features include network interception, which allows you to monitor and alter network requests and responses, and the ability to emulate different devices and geolocations.

## Playwright for Java API Testing

Playwright can be used effectively for Java API testing. An API (Application Programming Interface) is a set of rules that allows different software applications to communicate with each other. Playwright can be used to automate these API calls, making testing more efficient. You can use Playwright to send HTTP requests to your API, check the responses, and validate that your API is working as expected. This can be done using Playwright's built-in Fetch API or using external libraries like axios.

## Why API Testing is Important

*   Functionality testing
*   Testing the Database
*   Testing for Cross-Compatibility - Browsers, OS and Mobile Devices
*   Testing the UI and Visual Elements
*   Testing for web security
*   Testing for performance and loading speed
*   ~~API testing~~

## Testing pyramid vs Testing trophy

  

![](https://t9002023706.p.clickup-attachments.com/t9002023706/ac04bd1f-28d8-4dc2-8795-dcbb4ac67378/image.png)

## Browser engine

| Browser | Rendering Engine | JavaScript Engine |
| ---| ---| --- |
| Chrome | Blink | V8 |
| Firefox | Gecko | SpiderMonkey |
| Safari | WebKit | JavaScriptCore |
| Edge | Blink | V8 |
| Opera | Blink | V8 |
| IE | Trident | Chakra |

## NodeJS / V8

Node.js is a cross-platform, open-source JavaScript runtime environment that can run on Windows, Linux, Unix, macOS, and more. Node.js runs on the V8 JavaScript engine, and executes JavaScript code outside a web browser. Node.js lets developers use JavaScript to write command line tools and for server-side scripting.

## Asynchronous in JavaScript

> Although it’s synchronous by nature

JavaScript is a single-threaded, non-blocking, asynchronous, concurrent programming language with lots of flexibility.

[https://www.freecodecamp.org/news/content/images/2021/09/fourth-flow.gif](https://www.freecodecamp.org/news/content/images/2021/09/fourth-flow.gif)

## Asynchronous programming techniques in JavaScript

*   callbacks
*   promises
*   rxjs observables
*   async/ await

## Architecture - Selenium / Playwright

![](https://t9002023706.p.clickup-attachments.com/t9002023706/043b0b6d-132f-4915-853f-2bdd66ff7255/image.png)

![](https://t9002023706.p.clickup-attachments.com/t9002023706/e158e103-bebe-467b-b531-7ad24ebfeac8/image.png)

![](https://t9002023706.p.clickup-attachments.com/t9002023706/f64142ad-da89-4f72-bea7-71b7f35e9459/image.png)

## Playwright java - compatible unit testing frameworks

*   JUnit
*   TestNG
*   Spock
*   Appium
*   Cucumber
*   JBehave
*   Mockito

## REST API Testing - POSTMAN / Open API

GitHub link API Testing using Java / Nodejs

confusion in task, thread, core, program, Parallel, concurrent and other relevant terminologies

## **Create a Project from Maven Template**

```routeros
mvn archetype:generate 
	-DgroupId={project-packaging}
	-DartifactId={project-name}
	-DarchetypeArtifactId={maven-template} 
	-DinteractiveMode=false
mvn archetype:generate -DgroupId=com.kgisl.playwrightsample -DartifactId=playwrightsample -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## Add Java version

```vim
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

## Add Dependencies

```xml
<dependency>
      <groupId>com.microsoft.playwright</groupId>
      <artifactId>playwright</artifactId>
      <version>1.40.0</version>
</dependency>
<dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>5.10.0</version>
      <scope>test</scope>
</dependency>
```

## Add Plugins

```xml
<build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.4.1</version>
        <configuration>
          <!-- put your configurations here -->
        </configuration>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
</build>
```

## JUnit class structure

```typescript
package com.kgisl.playwrightsample;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SbgrafanaApplicationAPI {

  private Playwright playwright;
  private APIRequestContext request;

  @BeforeAll
  void beforeAll() {
    createPlaywright();
    createAPIRequestContext();
  }

  void createPlaywright() {
    playwright = Playwright.create();
  }

  void createAPIRequestContext() {
    
  }

  void disposeAPIRequestContext() {
    if (request != null) {
      request.dispose();
      request = null;
    }
  }

  void closePlaywright() {
    if (playwright != null) {
      playwright.close();
      playwright = null;
    }
  }

  @AfterAll
  void afterAll() {
    disposeAPIRequestContext();
    closePlaywright();
  }

  @Test
  void shouldCreateCustomer() {
     
  }

  @Test
  void shouldGetAllCustomers() {
    
  }

  @Test
  void shouldGetCustomer() {
    
  }

  @Test
  void shouldDeleteCustomer() {
    
  }

  @Test
  void shouldUpdateCustomer() {
      
  }
}
```

## REST API endpoints

![](https://t9002023706.p.clickup-attachments.com/t9002023706/6925d106-7414-4538-a020-1b9692c2ac87/image.png)

## Playwright API Testing using Junit

```java
package com.kgisl.playwrightsample;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SbgrafanaApplicationAPI {

  private Playwright playwright;
  private APIRequestContext request;
  // private static final String BaseURL = "<http://localhost:10000/customer>";

  @BeforeAll
  void beforeAll() {
    createPlaywright();
    createAPIRequestContext();
  }

  void createPlaywright() {
    playwright = Playwright.create();
  }

  void createAPIRequestContext() {
    Map<String, String> headers = new HashMap<>();
    // We set this header per GitHub guidelines.
    // headers.put("Accept", "application/vnd.github.v3+json");
    // Add authorization token to all requests.
    // Assuming personal access token available in the environment.
    // headers.put("Authorization", "token " + API_TOKEN);

    request = playwright.request().newContext(new APIRequest.NewContextOptions()
        // All requests we send go to this API endpoint.
        .setBaseURL("<http://localhost:10000>")
        .setExtraHTTPHeaders(headers));
  }

  void disposeAPIRequestContext() {
    if (request != null) {
      request.dispose();
      request = null;
    }
  }

  void closePlaywright() {
    if (playwright != null) {
      playwright.close();
      playwright = null;
    }
  }

  @AfterAll
  void afterAll() {
    disposeAPIRequestContext();
    closePlaywright();
  }

  @Test
  void shouldCreateCustomer() {
    Map<String, String> data = new HashMap<>();
    data.put("firstName", "PlaywrightF1");
    data.put("lastName", "PlaywrightL1");
    data.put("email", "email1@email.com");

    APIResponse response = request.post("/customer", RequestOptions.create().setData(data));

    int responseCode = response.status();
        String responseStatusText = response.statusText();
        String responseText = response.text();

        System.out.println(responseCode);
        System.out.println(responseStatusText);
        System.out.println(responseText);  
  }

  @Test
  void shouldGetAllCustomers() {
    // Map<String, String> headers = new HashMap<>();

        APIResponse response = request.get("/customer");
        int responseCode = response.status();
        String responseStatusText = response.statusText();
        String responseText = response.text();

        System.out.println(responseCode);
        System.out.println(responseStatusText);
        System.out.println(responseText);
  }

  @Test
  void shouldGetCustomer() {
    APIResponse response = request.get("/customer/4");
        int responseCode = response.status();
        String responseStatusText = response.statusText();
        String responseText = response.text();

        System.out.println(responseCode);
        System.out.println(responseStatusText);
        System.out.println(responseText);
  }

  @Test
  void shouldDeleteCustomer() {
    APIResponse response = request.delete("/customer/4");
        int responseCode = response.status();
        String responseStatusText = response.statusText();
        String responseText = response.text();

        System.out.println(responseCode);
        System.out.println(responseStatusText);
        System.out.println(responseText);
  }

  @Test
  void shouldUpdateCustomer() {
    Map<String, String> data = new HashMap<>();
    data.put("id", "85");
    data.put("firstName", "PlaywrightF85");
    data.put("lastName", "PlaywrightL85");
    data.put("email", "email85@email.com");

    APIResponse response = request.put("/customer/85", RequestOptions.create().setData(data));

    int responseCode = response.status();
        String responseStatusText = response.statusText();
        String responseText = response.text();

        System.out.println(responseCode);
        System.out.println(responseStatusText);
        System.out.println(responseText);  
  }
}
```

## Run test

![](https://t9002023706.p.clickup-attachments.com/t9002023706/5b5ab038-64d7-4107-8df1-24ed749172d3/image.png)

## Test Explorer in VS Code

![](https://t9002023706.p.clickup-attachments.com/t9002023706/32d9946c-8435-43d4-9b47-a9b8fefe3548/image.png)

## Maven commands to run JUnit tests

```stata
By default maven uses maven-surefire-plugin:2.12.4. Some of the maven commands may not work with the following error.
**********************************************************************************
Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test
**********************************************************************************

<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-surefire-plugin</artifactId>
<version>3.0.0-M3</version>
</plugin>

// Run all test methods with in test package
mvn test
// Run all test methods in a class
mvn -Dtest=StudentDemoTest test
// Run one test method in a class
mvn -Dtest=StudentDemoTest#getAllStudentsTest test
// Run multiple test methods in a class
mvn -Dtest=StudentDemoTest#getAllStudentsTest+getStudentTest test
// Run all test methods in multiple test classes
mvn -Dtest="StudentDemoTest,CalculatorTest" test
// Run selected test methods in multiple test classes
mvn -Dtest="StudentDemoTest#getAllStudentsTest+getStudentTest,CalculatorTest" test
// Run all test methods in multiple test classes using expression
mvn -Dtest="*Test" test
// Run all test Clases from subdirectory, eg: /doc/ You can use command:
mvn -Dtest=/doc/ test
```

## Test Result / Reports

*   List ReporterLine
*   ReporterDot Reporter
*   HTML Reporter
*   JSON Reporter
*   JUnit Reporter
*   GitHub Actions annotations
*   Third-party Reporters - Allure, Testrail, …

## Integration

*   Docker
*   Azure pipelines
*   GitHub Actions
*   GitLab CI
*   Circle CI
*   Jenkins

## GitHub Link

[https://github.com/baraneetharan/playwrightsample](https://github.com/baraneetharan/playwrightsample)

## Conclusion

In conclusion, Playwright is a powerful tool for browser automation and Java API testing. With its easy installation process, robust functionalities, and capabilities for automating API calls, Playwright can greatly enhance your testing process. Whether you're new to automation testing or an experienced tester looking for a new tool, Playwright is definitely worth considering.

## Q&A

## Improvements

*   Test execution order
*   mvn command to run test
*   Working with JSON using GSON
*   Test data management - Builder class / Database

##
