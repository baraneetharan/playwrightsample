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
    // We set this header per GitHub guidelines.
    // headers.put("Accept", "application/vnd.github.v3+json");
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
    Customer customer = new Gson().fromJson(responseText, Customer.class);

    assertEquals(customer.getFirstName(), "Emily");
    assertEquals(customer.getLastName(), "Davis");
    assertEquals(customer.getEmail(), "emily.davis@example.com");

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