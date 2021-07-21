package view;


import javax.swing.*;

interface IAdministratorDataProvider {

    JTextField getUsername();
    JPasswordField getPassword();
    String getTypes();
    String getName();

    void updateTable();


    String getSizes();
    String getDefect();
    String getNume();

    String getTypeSub();
    String getPostSub();
    String getPriceSub();
    String getUserUsernameTxt();

}

interface IAdminViewProvider {



        void showErrorMessage(String message);
        }


public interface IAdministratorView extends IAdministratorDataProvider,IAdminViewProvider{
}