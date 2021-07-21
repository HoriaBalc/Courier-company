
import controller.LoginController;
import view.LoginView;
import entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.UserRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
class Tests {

    @Test
    void findByName() {

        User user= UserRepo.findUserByUsername("a");
        assertEquals("a", user.getUsername(), "should be a");
    }

    @Test
    void loginSuccess(){
        User user= UserRepo.findUserByUsername("a");

        assertEquals("a", user.getUsername(), "should be a");
        assertEquals("a", user.getPassword(), "should be a");
        assertEquals("a", user.getName(), "should be a");
        assertEquals(0, user.getType(), "should be 0");
    }
   /* @Test
    public void givenInvalidUsernameAndPassword_login_showErrorMessage() {
        LoginView loginView = mock(LoginView.class, Mockito.RETURNS_DEEP_STUBS);
        when(loginView.getUsername().getText()).thenReturn("notUsername");
        when(loginView.getPassword().getPassword()).thenReturn("none".toCharArray());

        //LoginController controller = new LoginController(loginView, con);

       // controller.login();

        verify(loginView).showErrorMessage("Invalid username/password");
    }*/

}
