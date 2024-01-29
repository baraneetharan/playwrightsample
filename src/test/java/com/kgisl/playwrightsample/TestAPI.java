package com.kgisl.playwrightsample;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
public class TestAPI {

  private Playwright playwright;
  private APIRequestContext request;
  // private static final String BaseURL = "http://localhost:10000/customer";

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
    // We set this header per your developer guidelines.
    // headers.put("Accept", "application/json");
    // Add authorization token to all requests.
    // Assuming personal access token available in the environment.
    // headers.put("Authorization", "token " + API_TOKEN);

    request = playwright.request().newContext(new APIRequest.NewContextOptions()
        // All requests we send go to this API endpoint.
        .setBaseURL("http://localhost:10000")
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

    Customer customer = new Gson().fromJson(response.text(), Customer.class);

    assertEquals(201, response.status());
    // assertEquals("OK", response.statusText());
    assertEquals(customer.getFirstName(), "PlaywrightF1");
    assertEquals(customer.getLastName(), "PlaywrightL1");
    assertEquals(customer.getEmail(), "email1@email.com");
  }

  @Test
  void shouldGetAllCustomers() {
    // SELECT count(*) FROM CUSTOMER
    // http://localhost:10000/customer
    // https://jsonpathfinder.com/

    APIResponse response = request.get("/customer");

    JsonArray json = new Gson().fromJson(response.text(), JsonArray.class);
    assertEquals(5,json.size());
    // JsonElement firstValue = json.get(0);
    Customer firstCustomer = new Gson().fromJson(json.get(0), Customer.class);
    
    assertEquals(firstCustomer.getFirstName(), "John");
    assertEquals(firstCustomer.getLastName(), "Doe");
    assertEquals(firstCustomer.getEmail(), "john.doe@example.com");    
  }

  @Test
  void shouldGetCustomer() {
    // http://localhost:10000/customer/5
    // {"id":5,"firstName":"Emily","lastName":"Davis","email":"emily.davis@example.com"}

    APIResponse response = request.get("/customer/5");

    Customer customer = new Gson().fromJson(response.text(), Customer.class);

    assertEquals(200, response.status());
    // assertEquals("OK", response.statusText());
    assertEquals(customer.getFirstName(), "Emily");
    assertEquals(customer.getLastName(), "Davis");
    assertEquals(customer.getEmail(), "emily.davis@example.com");
  }

  @Test
  void shouldDeleteCustomer() {
    // http://localhost:10000/customer/6
    APIResponse response = request.delete("/customer/6");
    assertEquals(204, response.status());
  }

  @Test
  void shouldUpdateCustomer() {
    // {"id":87,"firstName":"Jane","lastName":"Smith","email":"jane.smith@example.com"},{"id":88,"firstName":"Alice","lastName":"Jones","email":"alice.jones@example.com"}
    Map<String, String> data = new HashMap<>();
    data.put("id", "7");
    data.put("firstName", "Baraneetharan");
    data.put("lastName", "Ramasamy");
    data.put("email", "baranee@email.com");

    APIResponse response = request.put("/customer/7", RequestOptions.create().setData(data));
    Customer customer = new Gson().fromJson(response.text(), Customer.class);
    // {"id":87,"firstName":"PlaywrightF87","lastName":"PlaywrightL87","email":"email87@email.com"}
    assertEquals(200, response.status());
    // assertEquals("OK", response.statusText());
    assertEquals(customer.getFirstName(), "Baraneetharan");
    assertEquals(customer.getLastName(), "Ramasamy");
    assertEquals(customer.getEmail(), "baranee@email.com");    
  }
}
