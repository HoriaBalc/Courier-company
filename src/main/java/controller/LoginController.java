package controller;

import entity.Subscription;
import entity.User;
import repository.SubscriptionRepo;

import start.Client;
import view.ILoginView;


import java.io.IOException;

public class LoginController {
    private ILoginView view;
    private Client.Connection con;
    public LoginController(ILoginView v, Client.Connection con) {
        this.con=con;
        this.view = v;

    }

    public void signUp() throws IOException, ClassNotFoundException {
        String username = view.getUsernameSignUp().getText();
        String name = view.getNameSignUp().getText();
        String password = String.valueOf(view.getPasswordSignUp().getPassword());


        if (username.isEmpty()){
            view.showErrorMessage("Nu ati introdus username-ul");

        }
        if (password.isEmpty()){
            view.showErrorMessage("Nu ati introdus parola");

        }
        if (name.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele");

        }
        User user=null;
        con.sendMessageToServer("findUserByUsername");
        con.sendMessageToServer(username);

        boolean serverHasData = false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        user = (User) con.getInput().readObject();
        if(user==null){
            con.sendMessageToServer("insertNewUser");
            con.sendMessageToServer(username);
            con.sendMessageToServer(name);
            con.sendMessageToServer(password);
            Subscription subscription= SubscriptionRepo.findSubscriptionByType("regular");
            User u=new User(username, name,2, password, subscription);
            view.showClientView(u);
        }else { view.showErrorMessage("Exista deja in baza de date"); }

    }



    public void login() throws IOException, ClassNotFoundException {
        String username = view.getUsername().getText();
        String password = String.valueOf(view.getPassword().getPassword());

        User user=null;
        con.sendMessageToServer("findUserByUsername");
        con.sendMessageToServer(username);
        boolean serverHasData = false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }

        user = (User) con.getInput().readObject();
        if (user!=null && user.getPassword().equals(password)&& user.getType()==0 ){
            view.showAdminView();

        }

        else if (user!=null && user.getPassword().equals(password) && user.getType()==1){
            view.showRegularView(user);

        }
        else if (user!=null && user.getPassword().equals(password) && user.getType()==2){
           view.showClientView(user);

        }
        else
            view.showErrorMessage("Invalid username/password");
    }
}