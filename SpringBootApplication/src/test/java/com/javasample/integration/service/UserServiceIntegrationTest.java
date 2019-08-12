package com.javasample.integration.service;

import com.javasample.model.User;
import com.javasample.controller.UserController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import static junit.framework.TestCase.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceIntegrationTest {
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    UserController controller;

    // =========================================== Get All Users ==========================================
    @Test
    public void testGetAllSuccess() {
        RestTemplate template = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/users";
        User newUser = createNewUser(template, baseUrl);

        ResponseEntity<User[]> response = template.getForEntity(baseUrl, User[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        deleteUser(newUser.getId());
    }

    // =========================================== Get User By ID =========================================
    @Test
    public void testGetByIdSuccess() {
        RestTemplate template = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/users";
        User newUser = createNewUser(template, baseUrl);

        ResponseEntity<User> response = template.getForEntity(baseUrl + "/" + newUser.getId(), User.class);
        User user = response.getBody();

        assertThat(user.getId(), is(newUser.getId()));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        deleteUser(newUser.getId());
    }

    @Test
    public void testGetByIdFailureNotFound() {
        RestTemplate template = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/users";

        try {
            template.getForEntity(baseUrl + "/" + UNKNOWN_ID, User.class);
            fail("should return 404 not found");
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            assertThat(e.getMessage(), is(HttpStatus.NOT_FOUND));
        }
    }

    // =========================================== Create New User ========================================
    private User createNewUser(RestTemplate template, String baseUrl) {
        Random rand = new Random();
        int n = rand.nextInt(1000);
        User newUser = new User(n, "T01", "T02", "T03", 1000);

        ResponseEntity<User> response = template.postForEntity(baseUrl, newUser, User.class);
        if (HttpStatus.CREATED.equals(response.getStatusCode()))
            return newUser;
        return null;
    }

    @Test
    public void testCreateNewUserForEntitySuccess() {
        RestTemplate template = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/users";

        Random rand = new Random();
        int n = rand.nextInt(1000);
        User newUser = new User(n, "T01", "T02", "T03", 1000);
        ResponseEntity<User> response = template.postForEntity(baseUrl, newUser, User.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        deleteUser(newUser.getId());
    }

    @Test
    public void testCreateNewUserFailExists() {
        RestTemplate template = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/users";
        User existingUser = createNewUser(template, baseUrl);
        try {
            template.postForLocation(baseUrl, existingUser, User.class);
            fail("should return 409 conflict");
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode(), is(HttpStatus.CONFLICT));
        }
    }

    // =========================================== Update Existing User ===================================
    @Test
    public void testUpdateUserSuccess() {
        RestTemplate template = new RestTemplate();
        String baseUrl = "http://localhost:" + randomServerPort + "/users";
        User existingUser = createNewUser(template, baseUrl);
        baseUrl = baseUrl + "/" + existingUser.getId();

        template.put(baseUrl, existingUser);

        deleteUser(existingUser.getId());
    }

    @Test
    public void testUpdateUserFail() {
        RestTemplate template = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/users";
        User existingUser = new User(UNKNOWN_ID, "T01", "T02", "T03", 1000);
        try {
            template.put(baseUrl + "/" + existingUser.getId(), existingUser);
            fail("should return 404 not found");
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
        }
    }

    // =========================================== Delete User ============================================
    private void deleteUser(int id) {
        RestTemplate template = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/users";
        template.delete(baseUrl + "/" + id);
    }

    @Test
    public void testDeleteUserSuccess() {
        RestTemplate template = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/users";
        User newUser = createNewUser(template, baseUrl);
        template.delete(baseUrl + "/" + newUser.getId());
    }

    @Test
    public void testDeleteUserFail() {
        RestTemplate template = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/users";
        try {
            template.delete(baseUrl + "/" + UNKNOWN_ID);
            fail("should return 404 not found");
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
        }
    }

    // =========================================== Load context ============================================
    @Test
    public void contextLoads() throws URISyntaxException {
        RestTemplate template = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/users";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = template.getForEntity(uri, String.class);
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}
