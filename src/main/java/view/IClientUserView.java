package view;

import javax.swing.*;
import java.io.IOException;

interface IClientDataProvider{
    JTextField getPackName();
    JTextField getPackSource();
    JTextField getPackSize();
    JTextField getPackDestination();
    JTextField getPackUrgency();
    JTextField getMoneyTxt();
    String getSubscriptionList();
    void updateTable() throws IOException, ClassNotFoundException;

}

interface IClientViewProvider {



    void showErrorMessage(String message);
}

public interface IClientUserView extends IClientDataProvider, IClientViewProvider{

}
