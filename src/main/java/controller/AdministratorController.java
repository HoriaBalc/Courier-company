package controller;

import entity.Subscription;
import entity.TransportCar;
import entity.User;
import presentation.Report;
import presentation.ReportFactory;
import start.Client;
import view.IAdministratorView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorController {

private IAdministratorView view;
private Client.Connection con;

public AdministratorController(IAdministratorView v, Client.Connection con){
    this.view=v;
    this.con=con;
}





    public List<User> getUsers()throws IOException, ClassNotFoundException{
    con.sendMessageToServer("getUsers");
    List<User> users=new ArrayList<User>();
    boolean serverHasData = false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        users = (ArrayList<User>) con.getInput().readObject();

    return users;
}

    public List<TransportCar> getCars()throws IOException, ClassNotFoundException{
        con.sendMessageToServer("getCars");
        List<TransportCar> cars=new ArrayList<TransportCar>();
        boolean serverHasData = false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        cars = (ArrayList<TransportCar>) con.getInput().readObject();

        return cars;


    }

    public List<Subscription> getSubs() throws IOException, ClassNotFoundException {

        con.sendMessageToServer("getSubs");
        List<Subscription> subscriptions=new ArrayList<Subscription>();
        boolean serverHasData = false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        subscriptions = (ArrayList<Subscription>) con.getInput().readObject();

        return subscriptions;

    }

    public void addSubs() throws IOException, ClassNotFoundException{
        String type = view.getTypeSub();
        int ok=1;
        if (type.isEmpty()){
            view.showErrorMessage("Nu ati introdus type-ul pentru subsciption");
            ok=0;
        }

        int posts=0;
        int price=0;

        try {
            posts = Integer.parseInt(view.getPostSub());
        }
        catch (NumberFormatException numberFormatException){
            view.showErrorMessage("Nu ati introdus corect numarul de postari");
            ok=0;
        }
        if (posts<=0&& ok==1){
            view.showErrorMessage("Nu ati introdus corect numarul de postari");
            ok=0;
        }

        if(view.getPostSub().equals(""))
            view.showErrorMessage("Nu ati introdus pretul");

        try {
            price =Integer.parseInt(view.getPriceSub());
        }
        catch (NumberFormatException numberFormatException){
            view.showErrorMessage("Nu ati introdus corect pretul");
            ok=0;
        }

        if (price<=0&& ok==1){
            view.showErrorMessage("Nu ati introdus corect pretul");
            ok=0;
        }

        //User user=null;
        con.sendMessageToServer("findSubByType");
        con.sendMessageToServer(type);
        Subscription subscription=null;// Subscription(type,posts,price);

        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        subscription = (Subscription) con.getInput().readObject();
        if(subscription==null && ok==1){
            con.sendMessageToServer("addSub");
            Subscription s=new Subscription(type,posts,price);
            con.sendObjectToServer(s);


        }else { if(ok==1) view.showErrorMessage("Exista deja in baza de date"); }

    }

    public void updateSubs() throws IOException, ClassNotFoundException{
        String type = view.getTypeSub();
        int ok=1;
        if (type.isEmpty()){
            view.showErrorMessage("Nu ati introdus type-ul pentru subsciption");
            ok=0;
        }
        if(view.getPostSub().equals(""))
            view.showErrorMessage("Nu ati introdus numarul de postari");

        int posts=0;
        int price=0;

        try {
             posts = Integer.parseInt(view.getPostSub());
        }
        catch (NumberFormatException numberFormatException){
            view.showErrorMessage("Nu ati introdus corect numarul de postari");
            ok=0;
        }
        if (posts<=0&& ok==1){
            view.showErrorMessage("Nu ati introdus corect numarul de postari");
            ok=0;
        }

        if(view.getPostSub().equals(""))
            view.showErrorMessage("Nu ati introdus pretul");

        try {
            price =Integer.parseInt(view.getPriceSub());
        }
        catch (NumberFormatException numberFormatException){
            view.showErrorMessage("Nu ati introdus corect pretul");
            ok=0;
        }

        if (price<0 && ok==1){
            view.showErrorMessage("Nu ati introdus corect pretul");
            ok=0;
        }

        //User user=null;
        con.sendMessageToServer("findSubByType");
        con.sendMessageToServer(type);
        Subscription subscription=null;// Subscription(type,posts,price);

        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        subscription = (Subscription) con.getInput().readObject();
        if(subscription!=null && ok==1){

            con.sendMessageToServer("updateSub");
            subscription.setPret(price);
            subscription.setNrPosts(posts);
            //Subscription s=new Subscription(type,posts,price);
            con.sendObjectToServer(subscription);


        }else { if(ok==1) view.showErrorMessage("Nu Exista in baza de date"); }

    }

    public void deleteSubs() throws IOException, ClassNotFoundException {

        String typeSub = view.getTypeSub();
        int ok=1;
        if (typeSub.isEmpty()){
            view.showErrorMessage("Nu ati introdus type subscription-ul");
            ok=0;
        }
        con.sendMessageToServer("findSubByType");
        con.sendMessageToServer(typeSub);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        Subscription subscription = (Subscription) con.getInput().readObject();

        //User u=UserRepo.findUserByUsername(username);
        if(!subscription.getType().equals("regular")) {
            con.sendMessageToServer("deleteSub");
            if (subscription != null && ok == 1) {

                con.sendObjectToServer(subscription);
            } else view.showErrorMessage("Nu exista user-ul cu username-ul cautat");
        }

        /*if(u!=null && ok==1)
        UserRepo.deleteUser(u);
        else             view.showErrorMessage("Nu exista user-ul cu username-ul cautat");*/
    }


    public void changeSubs() throws IOException, ClassNotFoundException{

        String username = view.getUserUsernameTxt();
        int ok=1;
        if (username.isEmpty()){
            view.showErrorMessage("Nu ati introdus username-ul");
            ok=0;
        }
        String type = view.getTypeSub();
        if (type.isEmpty()){
            view.showErrorMessage("Nu ati introdus type-ul pentru subsciption");
            ok=0;
        }


        //User user=null;
        con.sendMessageToServer("findSubByType");
        con.sendMessageToServer(type);
        Subscription subscription=null;// Subscription(type,posts,price);

        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        subscription = (Subscription) con.getInput().readObject();
        con.sendMessageToServer("findUserByUsername");
        con.sendMessageToServer(username);
        User user=null;// Subscription(type,posts,price);

        serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        user = (User) con.getInput().readObject();
        if(subscription!=null && user!=null && ok==1){

            user.setSubscription(subscription);
            con.sendMessageToServer("updateUser1");
            con.sendObjectToServer(user);
        }else { if(ok==1) view.showErrorMessage("Nu Exista in baza de date"); }

    }

    public void addUser() throws IOException, ClassNotFoundException{
        String username = view.getUsername().getText();
        int ok=1;
        if (username.isEmpty()){
            view.showErrorMessage("Nu ati introdus username-ul");
            ok=0;
        }
        String password = String.valueOf(view.getPassword().getPassword());
        if (password.isEmpty()){
            view.showErrorMessage("Nu ati introdus parola");
            ok=0;
        }
        String type =view.getTypes();
        if (!type.equals("1")&&(!type.equals("0"))){
            view.showErrorMessage("Nu ati introdus tipul");
            ok=0;
        }
        String name = view.getName();
        if (name.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele");
            ok=0;
        }

        User user=null;
        con.sendMessageToServer("findUserByUsername");
        con.sendMessageToServer(username);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        user = (User) con.getInput().readObject();
        if(user==null && ok==1){
            con.sendMessageToServer("addUser");
            con.sendMessageToServer(username);
            con.sendMessageToServer(name);
            con.sendMessageToServer(password);
            con.sendMessageToServer(type);


        }else { if(ok==1) view.showErrorMessage("Exista deja in baza de date"); }

    }


    public void updateUser() throws IOException, ClassNotFoundException{

        String username = view.getUsername().getText();
        int ok=1;
        if (username.isEmpty()){
            view.showErrorMessage("Nu ati introdus username-ul");
            ok=0;
        }
        String password = String.valueOf(view.getPassword().getPassword());
        if (password.isEmpty()){
            view.showErrorMessage("Nu ati introdus parola");
            ok=0;
        }
        String type =view.getTypes();
        if (!type.equals("1")&&(!type.equals("2"))&&(!type.equals("3"))){
            view.showErrorMessage("Nu ati introdus tipul");
            ok=0;
        }
        String name = view.getName();
        if (name.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele");
            ok=0;
        }

        User user=null;
        con.sendMessageToServer("findUserByUsername");
        con.sendMessageToServer(username);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        user = (User) con.getInput().readObject();
        if(user!=null){
            con.sendMessageToServer("updateUser");
            con.sendMessageToServer(username);
            con.sendMessageToServer(name);
            con.sendMessageToServer(password);
            con.sendMessageToServer(type);

        }else { view.showErrorMessage("Nu exista in baza de date"); }

    }

    public void deleteUser() throws IOException, ClassNotFoundException {

        String username = view.getUsername().getText();
        int ok=1;
        if (username.isEmpty()){
            view.showErrorMessage("Nu ati introdus username-ul");
            ok=0;
        }
        con.sendMessageToServer("findUserByUsername");
        con.sendMessageToServer(username);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        User u = (User) con.getInput().readObject();

        con.sendMessageToServer("deleteUser");
        if(u!=null && ok==1){

            con.sendObjectToServer(u);
        }
        else             view.showErrorMessage("Nu exista user-ul cu username-ul cautat");


    }

    public void logout() {
    }

    public void addCar() throws IOException, ClassNotFoundException {
        int ok=1;
        String nameCar = view.getNume();
        if (nameCar.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele masinii");
            ok=0;
        }

        String def = view.getDefect();
        if ((!def.equals("true"))&&(!def.equals("false"))){
            view.showErrorMessage("Nu ati introdus in coloana functionare a masinii");
            ok=0;
        }

        String siz = view.getSizes();
        if ((!siz.equals("1"))&&(!siz.equals("2"))&&(!siz.equals("3"))){
            view.showErrorMessage("Nu ati introdus in coloana de marime a masinii");
            ok=0;
        }

        TransportCar transportCar=null;
        con.sendMessageToServer("findCarByName");
        con.sendMessageToServer(nameCar);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        transportCar = (TransportCar) con.getInput().readObject();
        if(transportCar==null&& ok==1){
            con.sendMessageToServer("addCar");
            con.sendMessageToServer(nameCar);
            con.sendMessageToServer(def);
            con.sendMessageToServer(siz);


        }else {if(ok==1) view.showErrorMessage("Exista deja in baza de date"); }


    }


    public void updateCar() throws IOException, ClassNotFoundException {
        String nameCar = view.getNume();
        int ok=1;
        if (nameCar.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele masinii");
            ok=0;
        }
        String def = view.getDefect();
        if ((!def.equals("true"))&&(!def.equals("false"))){
            view.showErrorMessage("Nu ati introdus in coloana functionare a masinii");
            ok=0;
        }

        String siz = view.getSizes();
        if ((!siz.equals("1"))&&(!siz.equals("2"))&&(!siz.equals("3"))){
            view.showErrorMessage("Nu ati introdus in coloana de marime a masinii");
            ok=0;
        }

        TransportCar transportCar=null;
        con.sendMessageToServer("findCarByName");
        con.sendMessageToServer(nameCar);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        transportCar = (TransportCar) con.getInput().readObject();
        if(transportCar!=null){
            con.sendMessageToServer("updateCar");
            con.sendMessageToServer(nameCar);
            con.sendMessageToServer(def);
            con.sendMessageToServer(siz);


        }else { view.showErrorMessage("Nu exista in baza de date"); }

        }

     public void generateReport(String tip) throws IOException {
         ReportFactory reportFactory=new ReportFactory();
         Report rep=reportFactory.getReport(tip);
         rep.generateReport(con);


     }


    public void deleteCar() throws IOException, ClassNotFoundException {
    int ok=1;
        String nameCar = view.getNume();
        if (nameCar.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele masinii");
            ok=0;
        }
        if(ok==1) {
            TransportCar t=null;
            con.sendMessageToServer("findCarByName");
            con.sendMessageToServer(nameCar);
            boolean serverHasData=false;
            while (!serverHasData) {
                serverHasData = con.getSocket().getInputStream().available() > 0;
            }
            t = (TransportCar) con.getInput().readObject();

            if (t != null) {
                con.sendMessageToServer("dadada");
                con.sendObjectToServer(t);
                serverHasData=false;
                while (!serverHasData) {
                    serverHasData = con.getSocket().getInputStream().available() > 0;
                }
                t = (TransportCar) con.getInput().readObject();


                }
                con.sendMessageToServer("deleteCar");
                con.sendObjectToServer(t);
            }
            else   view.showErrorMessage("Nu este prezenta in baza de date masina cu numele introdus");

        }


}















