package view;


import javax.swing.*;

interface IRegularUserDataProvider {

    String getUrgency();
    String getSource();
    String getDestination();
    String getSizes();
    String getStatus();
    String getName();
    String getNameCar();
    String getSearch();

    String getPackageTxt();
    int getTip();
    void setTip(int i);
    // String getSize();
//    String getDefect();


}

interface IUserProvider {

    void showErrorMessage(String message);
}

public interface IRegularUserView extends IRegularUserDataProvider,IUserProvider{

}