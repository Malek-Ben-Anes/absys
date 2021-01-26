package com.absys.test.service;

import com.absys.test.dto.UserDto;
import com.absys.test.model.UserEntity;
import com.absys.test.repository.UserRepository;
import com.absys.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.absys.test.model.UserStatusEnum.CREATED;
import static com.absys.test.model.UserStatusEnum.DONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private final List<UserEntity> list = new ArrayList<>();
    @MockBean
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    public void findAll() {
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
}
