package controller;

import entity.*;
import entity.Package;
import repository.PackageRequestRepo;
import repository.SubscriptionRepo;
import repository.UserRepo;
import start.Client;
import view.IClientUserView;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ClientController implements Serializable {

    private IClientUserView view;
    private Client.Connection con;
    private User user;
    public  ClientController(IClientUserView v, Client.Connection c, User u){
        this.view=v;
        this.con=c;
        this.user=u;

    }


    public void logout() throws IOException {
        con.sendMessageToServer("logout");
        con.sendObjectToServer(view);

    }

    public void request() throws IOException {
        int ok=1;
        String packName=view.getPackName().getText();
        if (packName.isEmpty()){
            view.showErrorMessage("Nu ati introdus username-ul");
            ok=0;
        }
        String packDestination=view.getPackDestination().getText();
        if (packDestination.isEmpty()){
            view.showErrorMessage("Nu ati introdus destinatia");
            ok=0;
        }
        String packSource=view.getPackSource().getText();
        if (packSource.isEmpty()){
            view.showErrorMessage("Nu ati introdus Sursa");
            ok=0;
        }
        String packUrgency=view.getPackUrgency().getText();
        if (packUrgency.isEmpty()){
            view.showErrorMessage("Nu ati introdus Urgenta");
            ok=0;
        }
        String packSize=view.getPackSize().getText();
        if (packSize.isEmpty()){
            view.showErrorMessage("Nu ati introdus size-ul");
            ok=0;
        }

        if(ok==1){
            Package p= new Package(1,packSource,packName,packDestination,Integer.parseInt(packSize),Boolean.valueOf(packUrgency),null,null);

            List<PackageRequest> packageRequestList=PackageRequestRepo.findAllPackageRequest();
            int contor=0;
            for(PackageRequest pR:packageRequestList){
                Date date=new Date(System.currentTimeMillis());
                if(pR.getData().toString().equals(date.toString())){
                    contor++;

                }
            }
            if(user.getSubscription()==null){
                Subscription s= SubscriptionRepo.findSubscriptionByType("regular");
                user.setSubscription(s);
                UserRepo.updateUser(user);
            }
            if(contor>=user.getSubscription().getNrPosts()){
                view.showErrorMessage("Nu mai ai requesturi pentru azi pentru subscritia ta");

            }else{
                con.sendMessageToServer("request");
                con.sendObjectToServer(p);
                con.sendObjectToServer(user);
                con.sendObjectToServer(view);
            }

        }
    }


    public void request1() throws IOException {
        int ok=1;
        String packName=view.getPackName().getText();
        if (packName.isEmpty()){
            view.showErrorMessage("Nu ati introdus username-ul");
            ok=0;
        }
        String packDestination=view.getPackDestination().getText();
        if (packDestination.isEmpty()){
            view.showErrorMessage("Nu ati introdus destinatia");
            ok=0;
        }
        String packSource=view.getPackSource().getText();
        if (packSource.isEmpty()){
            view.showErrorMessage("Nu ati introdus Sursa");
            ok=0;
        }
        String packUrgency=view.getPackUrgency().getText();
        if (packUrgency.isEmpty()){
            view.showErrorMessage("Nu ati introdus Urgenta");
            ok=0;
        }
        String packSize=view.getPackSize().getText();
        if (packSize.isEmpty()){
            view.showErrorMessage("Nu ati introdus size-ul");
            ok=0;
        }

        if(ok==1){
            Package p= new Package(1,packSource,packName,packDestination,Integer.parseInt(packSize),Boolean.valueOf(packUrgency),null,null);
            con.sendMessageToServer("request");
            con.sendObjectToServer(p);
            con.sendObjectToServer(user);
            con.sendObjectToServer(view);
            /*Package pack= PackageRepo.findPackageByName(p.getName());
            if(pack==null){



                PackageRequest request=new PackageRequest(user,p.getId());
                PackageRepo.insertNewPackage(p);
                PackageRequestRepo.insertNewPackRequest(request);*/
            }
        }

    public  void changeSubs() throws IOException, ClassNotFoundException {
         int ok=1;
         String s=(String) view.getSubscriptionList();
        if (s.isEmpty()){
            view.showErrorMessage("Nu ati introdus subscription type-ul");
            ok=0;
        }

        con.sendMessageToServer("findSubByType");
        con.sendMessageToServer(s);
        Subscription subscription=null;// Subscription(type,posts,price);

        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        subscription = (Subscription) con.getInput().readObject();
        if(subscription!=null){
        Subscription sub=user.getSubscription();
        int m=user.getMoney();
        if(!sub.getType().equals(subscription.getType())){
            user.setSubscription(subscription);
            if(sub.getPret()<subscription.getPret()){

                m=user.getMoney()-subscription.getPret();

            }
            con.sendMessageToServer("updateUser3");
            user.setMoney(m);
            con.sendObjectToServer(user);
            con.sendObjectToServer(m);
            con.sendObjectToServer(subscription);



        }
        }



    }


    public void addMoney() throws IOException {
        int ok=1;
        int money=0;

        try {
            money = Integer.parseInt(view.getMoneyTxt().getText());
        }
        catch (NumberFormatException numberFormatException){
            view.showErrorMessage("Nu ati introdus corect suma");
            ok=0;
        }
        //String money=view.getMoneyTxt().getText();
        if (money<0){
            view.showErrorMessage("Nu ati introdus corect suma");
            ok=0;
        }

        con.sendMessageToServer("updateUser2");
        con.sendObjectToServer(user);
        con.sendObjectToServer(user.getMoney()+money);
        user.setMoney(user.getMoney()+money);

    }


    public void payExtra() throws IOException {
        int ok=1;
        String packName=view.getPackName().getText();
        if (packName.isEmpty()){
            view.showErrorMessage("Nu ati introdus username-ul");
            ok=0;
        }
        String packDestination=view.getPackDestination().getText();
        if (packDestination.isEmpty()){
            view.showErrorMessage("Nu ati introdus destinatia");
            ok=0;
        }
        String packSource=view.getPackSource().getText();
        if (packSource.isEmpty()){
            view.showErrorMessage("Nu ati introdus Sursa");
            ok=0;
        }
        String packUrgency=view.getPackUrgency().getText();
        if (packUrgency.isEmpty()){
            view.showErrorMessage("Nu ati introdus Urgenta");
            ok=0;
        }
        String packSize=view.getPackSize().getText();
        if (packSize.isEmpty()){
            view.showErrorMessage("Nu ati introdus size-ul");
            ok=0;
        }
        if (user.getMoney()-5<0){
            ok=0;
        }


        if(ok==1){
            Package p= new Package(1,packSource,packName,packDestination,Integer.parseInt(packSize),Boolean.valueOf(packUrgency),null,null);
            con.sendMessageToServer("request1");
            con.sendObjectToServer(p);
            //if (user.getMoney()-5>0)
            int m=user.getMoney()-5;
            user.setMoney(m);
            con.sendObjectToServer(user);
            con.sendObjectToServer(m);
            con.sendObjectToServer(view);

        }
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

    public Package getPack(String id) throws IOException, ClassNotFoundException {
        con.sendMessageToServer("TabelPackage");
        con.sendMessageToServer(id);

        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        Package p = (Package) con.getInput().readObject();
        return  p;
    }


    public List<PackageRequest> getPacks() throws IOException, ClassNotFoundException {
        con.sendMessageToServer("ListaTabelPackageRequest");
        con.sendObjectToServer(this.user);

        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        List<PackageRequest> p = (List<PackageRequest>) con.getInput().readObject();
        return  p;
    }

    public User getUser() throws IOException, ClassNotFoundException {

        con.sendMessageToServer("findUserByUsername");
        con.sendMessageToServer(user.getUsername());
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        User u=(User) con.getInput().readObject();


        return u;
    }
}
