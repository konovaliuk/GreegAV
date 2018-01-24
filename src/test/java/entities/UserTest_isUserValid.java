package entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest_isUserValid {

    @Test
    public void isUserValid() {
        User user=User.isUserValid("admin","admin");
        assertTrue(user.isAdmin());
    }
}