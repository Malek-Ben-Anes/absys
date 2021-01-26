package com.absys.test.service;

import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.CreateUserRequest;
import com.absys.test.exception.NotFoundException;
import com.absys.test.model.CriminalEntity;
import com.absys.test.model.UserEntity;
import com.absys.test.repository.CriminalRepository;
import com.absys.test.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.absys.test.model.UserStatusEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private final List<UserEntity> list = new ArrayList<>();
    @MockBean
    UserRepository userRepository;
    @MockBean
    CriminalRepository criminalRepository;
    @MockBean
    SimpMessagingTemplate messageTemplate;
    @Autowired
    UserService userService;

    @Test
    public void should_createNewUser() {
        when(userRepository.save(any())).thenReturn(new UserEntity("JHXZ677", "JOHN", "DOE", new Date(), "TEXAS", "CRAFTER", CREATED));

        CreateUserRequest request = new CreateUserRequest("JOHN", "DOE", new Date(), "TEXAS", "CRAFTER");
        //test
        UserDto userDto = userService.createUser(request);

        assertEquals("JHXZ677", userDto.getId());
        assertEquals("JOHN", userDto.getFirstName());
        assertEquals("DOE", userDto.getLastName());
        assertEquals("TEXAS", userDto.getEarthCountry());
        assertEquals("CRAFTER", userDto.getEarthJob());
        assertEquals(CREATED, userDto.getStatus());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void should_findAllUsers() {
        UserEntity userOne = new UserEntity("JHXZ677", "JOHN", "DOE", new Date(), "TEXAS", "CRAFTER", CREATED);
        UserEntity userTwo = new UserEntity("JHXZ678", "JOHN1", "DOE1", new Date(), "TEXAS", "CRAFTER", DONE);
        UserEntity userThree = new UserEntity("JHXZ679", "JOHN2", "DOE2", new Date(), "TEXAS", "CRAFTER", DONE);
        list.add(userOne);
        list.add(userTwo);
        list.add(userThree);
        when(userRepository.findAll()).thenReturn(list);

        //test
        List<UserDto> empList = userService.findAll();

        assertEquals(3, empList.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void should_launchWorkFlow_Return_DoneUser() {
        when(userRepository.findById(any())).thenReturn(
                Optional.ofNullable(new UserEntity("JHXZ677", "JOHN", "DOE", new Date(), "TEXAS", "CRAFTER", CREATED)));

        when(userRepository.save(any())).thenReturn(new UserEntity("JHXZ677", "JOHN", "DOE", new Date(), "TEXAS", "CRAFTER", DONE));

        when(criminalRepository.findCriminalByCriteria(anyString(), anyString(), any()))
                .thenReturn(Optional.ofNullable(new CriminalEntity("JEAN", "DUPONT", new Date())));
        //test
        UserDto userDto = userService.workflow("JHXZ677");

        assertEquals("JHXZ677", userDto.getId());
        assertEquals("JOHN", userDto.getFirstName());
        assertEquals("DOE", userDto.getLastName());
        assertEquals("TEXAS", userDto.getEarthCountry());
        assertEquals("CRAFTER", userDto.getEarthJob());
        assertEquals(DONE, userDto.getStatus());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void should_launchWorkFlow_Return_RefusedUser() {
        when(userRepository.findById(any())).thenReturn(
                Optional.ofNullable(new UserEntity("JHXZ677", "JOHN", "DOE", new Date(), "TEXAS", "CRAFTER", CREATED)));
        when(userRepository.save(any())).thenReturn(new UserEntity("JHXZ677", "JOHN", "DOE", new Date(), "TEXAS", "CRAFTER", REFUSED));

        //test
        UserDto userDto = userService.workflow("JHXZ677");

        assertEquals("JHXZ677", userDto.getId());
        assertEquals("JOHN", userDto.getFirstName());
        assertEquals("DOE", userDto.getLastName());
        assertEquals("TEXAS", userDto.getEarthCountry());
        assertEquals("CRAFTER", userDto.getEarthJob());
        assertEquals(REFUSED, userDto.getStatus());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void should_launchWorkFlow_launch_notFoundException() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.workflow(any()));
    }
}
