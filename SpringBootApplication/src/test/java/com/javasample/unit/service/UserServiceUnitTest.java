package com.javasample.unit.service;

import com.javasample.model.User;
import com.javasample.repository.UserRepository;
import com.javasample.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceUnitTest {
    @Autowired
    private UserService service;

    @LocalServerPort
    int randomServerPort;

    @MockBean
    private UserRepository repository;

    @Test
    public void getUsersTest() {
        when(repository.findAll()).thenReturn(Stream.of(new User(1001,"T01", "T02", "T03", 1000), new User(2001,"T03", "T04", "T05", 1000)).collect(Collectors.toList()));
        assertEquals(2, service.getAll().size());
    }

    @Test
    public void getByIdTest() {
        int id = 11;
        User user = new User(id,"T01", "T02", "T03", 1000);
        when(repository.findById(id)).thenReturn(Optional.of(user));
        assertEquals(user, service.findById(id));
    }

    @Test
    public void saveUser() {
        Random rand = new Random();
        int n = rand.nextInt(1000);
        User user = new User(n,"T01", "T02", "T03", 1000);
        service.create(user);
        verify(repository, times(1)).save(user);
    }

    @Test
    public void deleteUser() {
        Random rand = new Random();
        int n = rand.nextInt(1000);
        User user = new User(n,"T01", "T02", "T03", 1000);
        service.create(user);
        service.delete(user.getId());
        verify(repository, times(1)).deleteById(user.getId());
    }
}
