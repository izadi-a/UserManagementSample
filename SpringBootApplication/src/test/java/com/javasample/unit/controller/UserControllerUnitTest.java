package com.javasample.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javasample.model.User;
import com.javasample.unit.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerUnitTest {
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    private MockMvc mockMvc;

    @Mock
    private UserService<User> userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void testGetAllSuccess() throws Exception {
        int id01 = 1001;
        int id02 = 2001;
        List<User> users = Arrays.asList(new User(id01, "T01", "T02", "T03", 1000), new User(id02, "T03", "T04", "T05", 1000));
        when(userService.getAll()).thenReturn(users);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(id01)))
                .andExpect(jsonPath("$[0].userName", is("T03")))
                .andExpect(jsonPath("$[1].id", is(id02)))
                .andExpect(jsonPath("$[1].userName", is("T05")));
        verify(userService, times(1)).getAll();
        verifyNoMoreInteractions(userService);
    }

    // =========================================== Create New User ========================================
    @Test
    public void testCreateUserSuccess() throws Exception {
        Random rand = new Random();
        int n = rand.nextInt(1000);
        User user = new User(n, "T01", "T02", "T03", 1000);

        doNothing().when(userService).create(user);

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isCreated());

        verify(userService, times(1)).create(user);
    }

    @Test
    public void testCreateUserFail404NotFound() throws Exception {
        Random rand = new Random();
        int n = rand.nextInt(1000);
        User user = new User(n, "T01", "T02", "T03", 1000);

        when(userService.findById(user.getId())).thenReturn(user);

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isConflict());

        verify(userService, times(1)).findById(user.getId());
        verifyNoMoreInteractions(userService);
    }

    // =========================================== Delete User ============================================
    @Test
    public void testDeleteUserSuccess() throws Exception {
        Random rand = new Random();
        int n = rand.nextInt(1000);
        User user = new User(n, "T01", "T02", "T03", 1000);

        when(userService.findById(user.getId())).thenReturn(user);
        doNothing().when(userService).delete(user.getId());

        mockMvc.perform(
                delete("/users/{id}", user.getId()))
                .andExpect(status().isOk());

        verify(userService, times(1)).findById(user.getId());
        verify(userService, times(1)).delete(user.getId());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testDeleteUserFail404NotFound() throws Exception {
        User user = new User(UNKNOWN_ID, "T01", "T02", "T03", 1000);

        when(userService.findById(user.getId())).thenReturn(null);

        mockMvc.perform(
                delete("/users/{id}", user.getId()))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).findById(user.getId());
        verifyNoMoreInteractions(userService);
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}