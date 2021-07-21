package view;

import entity.User;

import javax.swing.*;
import java.io.IOException;

interface ILoginDataProvider {

    JTextField getUsername();

    JPasswordField getPassword();

    JTextField getUsernameSignUp();

    JTextField getNameSignUp();

    JPasswordField getPasswordSignUp();

}

interface IViewProvider {

    void showAdminView();

    void showRegularView(User user) throws IOException, ClassNotFoundException;

    void showClientView(User user) throws IOException;

    void showErrorMessage(String message);
}

public interface ILoginView extends ILoginDataProvider, IViewProvider {

}
