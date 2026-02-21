import dao.UserDao;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import services.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private  UserDao mockUserDao=new UserDao();
    private AutoCloseable closeable;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
        userService = new UserService(mockUserDao);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }


    @Test
    void testGetUserById(){
        User mockUser = new User ("Vasya","vasya@mail.ru",23,new Date(2025,12,11));
        when(mockUserDao.getUserById(0)).thenReturn(mockUser);
        User result = userService.getUserById(0);
        assertNotNull(Optional.of(result));
        assertEquals("Vasya", result.getName());
        assertEquals("vasya@mail.ru", result.getEmail());
        assertEquals(23,result.getAge());
        assertEquals(new Date(23-10-2025),result.getCreated_at());

    }

    @Test
    void testSaveUser(){
        User mockUser = new User ("Vasya","vasya@mail.ru",23,new Date(2025,12,11));
        when(mockUserDao.saveUser(mockUser)).thenReturn(1);
        Integer result = userService.saveUser(mockUser);
        assertNotNull(Optional.of(result));
        assertEquals(1, result);
    }

    @Test
    void testDeleteUserById(){
        when(mockUserDao.deleteUserById(0)).thenReturn(true);
        boolean result = userService.deleteUserById(0);
        assertNotNull(Optional.of(result));
        assertTrue(result);
    }

    @Test
    void testUpdateUser(){
        User mockUser = new User ("Vasya","vasya@mail.ru",23,new Date(2025,12,11));
        when(mockUserDao.updateUser(mockUser)).thenReturn(true);
        boolean result = userService.updateUser(mockUser);
        assertNotNull(Optional.of(result));
        assertTrue(result);
    }

    @Test
    void testGetAllUsers(){
        User mockUser1 = new User ("Vasya","vasya@mail.ru",23,new Date(2025,12,11));
        User mockUser2 = new User ("Vanya","vanya@mail.ru",22,new Date(2025,12,11));
        User mockUser3 = new User ("Valya","valya@mail.ru",21,new Date(2025,12,11));
        List<User> mockUserList= new ArrayList<User>();
        mockUserList.add(mockUser1);
        mockUserList.add(mockUser2);
        mockUserList.add(mockUser3);
        when(mockUserDao.getAllUsers()).thenReturn(mockUserList);
        List<User> result = userService.getAllUsers();
        assertNotNull(Optional.of(result));
        assertEquals(mockUserList, result);


    }
}
