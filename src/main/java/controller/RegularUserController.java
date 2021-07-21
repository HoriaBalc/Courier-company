package controller;

import entity.Package;

import entity.TransportCar;
import entity.User;

import repository.TransportCarRepo;

import start.Client;

import view.IRegularUserView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegularUserController implements Serializable {

private IRegularUserView view;
private Client.Connection con;
private  User user;
public RegularUserController(IRegularUserView v, Client.Connection con, User user){
    this.view=v;
    this.con=con;
    this.user=user;
}
    public void user(){
        String destination = view.getDestination();
        String source =view.getSource();
        String size = view.getSizes();
        String status = view.getStatus();
        String urgency=view.getUrgency();

    }

    public void addPackage() throws IOException, ClassNotFoundException {
        String destination = view.getDestination();
        int ok=1;
        if (destination.isEmpty()){
            view.showErrorMessage("Nu ati introdus destinatia");
            ok=0;
        }
        String source =view.getSource();
        if (source.isEmpty()){
            view.showErrorMessage("Nu ati introdus sursa");
            ok=0;
        }
        String size = view.getSizes();
        if (!size.equals("3")&&!size.equals("1")&&!size.equals("2")){
            view.showErrorMessage("Nu ati introdus marimea corect");
            ok=0;
        }
        String status = view.getStatus();
        if (!status.equals("0")&&!status.equals("1")&&!status.equals("2")){
            view.showErrorMessage("Nu ati introdus corect statusul");
            ok=0;
        }
        String urgency=view.getUrgency();
        if (urgency.isEmpty()){
            view.showErrorMessage("Nu ati introdus corect daca produsul este urgent");
            ok=0;
        }
        String name=view.getName();
        if (name.isEmpty()){
            view.showErrorMessage("Nu ati introdus name-ul");
            ok=0;
        }

        String nameCar=view.getNameCar();
        if (nameCar.isEmpty()){
            view.showErrorMessage("Nu ati introdus masina");
            ok=0;
        }
        Package pack=null;
        con.sendMessageToServer("findPackageByName");
        con.sendMessageToServer(nameCar);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        pack = (Package) con.getInput().readObject();

        if(pack==null && ok==1) {
            TransportCar car=null;
            con.sendMessageToServer("findCarByName");
            con.sendMessageToServer(nameCar);
            serverHasData=false;
            while (!serverHasData) {
                serverHasData = con.getSocket().getInputStream().available() > 0;

            }
            car = (TransportCar) con.getInput().readObject();

            Package p = new Package(Integer.parseInt(status), source, name, destination,Integer.parseInt(size),Boolean.valueOf(urgency),car,null);
            con.sendMessageToServer("addPackage");
            con.sendObjectToServer(p);

        }else if(ok==1){            view.showErrorMessage("Exista deja in baza de date"); }

    }

    public void updatePackage() throws IOException, ClassNotFoundException {
        int ok=1;
        String destination = view.getDestination();

        if (destination.isEmpty()){
            view.showErrorMessage("Nu ati introdus destinatia");
            ok=0;
        }
        String source =view.getSource();
        if (source.isEmpty()){
            view.showErrorMessage("Nu ati introdus sursa");
            ok=0;
        }
        String size = view.getSizes();
        if (!size.equals("3")&&!size.equals("1")&&!size.equals("2")){
            view.showErrorMessage("Nu ati introdus marimea corect");
            ok=0;
        }
        String status = view.getStatus();
        if (!status.equals("0")&&!status.equals("1")&&!status.equals("2")){
            view.showErrorMessage("Nu ati introdus corect statusul");
            ok=0;
        }
        String urgency=view.getUrgency();
        if (urgency.isEmpty()){
            view.showErrorMessage("Nu ati introdus corect daca produsul este urgent");
            ok=0;
        }
        String name=view.getName();
        if (name.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele");
            ok=0;
        }
        String nameCar=view.getNameCar();
        if (nameCar.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele masinii");
            ok=0;
        };
        if(ok==1) {
            Package p=null;
            con.sendMessageToServer("findPackageByName");
            con.sendMessageToServer(name);
            boolean serverHasData=false;
            while (!serverHasData) {
                serverHasData = con.getSocket().getInputStream().available() > 0;
            }
            p = (Package) con.getInput().readObject();

            //Package p = PackageRepo.findPackageByName(name);
            p.setDestination(destination);
            p.setSource(source);
            p.setSize(Integer.parseInt(size));
            p.setStatus(Integer.parseInt(status));
            p.setUrgency(Boolean.valueOf(urgency));
            p.setTranspCar(TransportCarRepo.findCarByName(nameCar));
            con.sendMessageToServer("updatePackage");
            con.sendObjectToServer(p);

        }
    }

    public void deletePackage() throws IOException, ClassNotFoundException {
        String name=view.getName();
        int ok=1;
        if (name.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele");
            ok=0;
        }
        Package p=null;
        con.sendMessageToServer("findPackageByName");
        con.sendMessageToServer(name);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        p = (Package) con.getInput().readObject();

        //Package p=PackageRepo.findPackageByName(name);
        if(p!=null && ok==1){
            List<Package> packages;
            TransportCar tp=p.getTranspCar();
            if(tp!=null){
                con.sendMessageToServer("nununu");
                con.sendObjectToServer(tp);

                while (!serverHasData) {
                    serverHasData = con.getSocket().getInputStream().available() > 0;
                }

                packages=(List<Package>) con.getInput().readObject();
                con.sendMessageToServer("updateCar");
                con.sendMessageToServer(tp.getNume());
                con.sendMessageToServer(String.valueOf(tp.isDefect()));
                con.sendMessageToServer(String.valueOf(tp.getSize()));

            }
            con.sendMessageToServer("deletePackage");
            con.sendObjectToServer(p);

        }        else   view.showErrorMessage("Nu exista in baza de date");



    }


    public void addToList() throws IOException, ClassNotFoundException {
        String name=view.getPackageTxt();
        int ok=1;
        if (name.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele");
            ok=0;
        }

        con.sendMessageToServer("findPackageByName");
        con.sendMessageToServer(name);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        Package p = (Package) con.getInput().readObject();
        List<Package> pp;
        if(p.getStatus()!=1){
            view.showErrorMessage("Pachetul a fost deja livrat de altcineva!");
            ok=0;
        }


        if(ok==1 && p!=null){
            con.sendMessageToServer("addToList");
            con.sendObjectToServer(p);
            con.sendObjectToServer(user);
            serverHasData=false;
            while (!serverHasData) {
                serverHasData = con.getSocket().getInputStream().available() > 0;
            }
            pp=(List<Package>) con.getInput().readObject();
        }else view.showErrorMessage("Nu exista in baza de date");



    }

    public void changeStatus() throws IOException, ClassNotFoundException {
        String name=view.getPackageTxt();
        int ok=1;
        if (name.isEmpty()){
            view.showErrorMessage("Nu ati introdus numele");
            ok=0;
        }

        con.sendMessageToServer("findPackageByName");
        con.sendMessageToServer(name);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        Package p = (Package) con.getInput().readObject();

        if(ok==1 && p!=null){
            con.sendMessageToServer("getPacksByUser");
            con.sendMessageToServer(user.getId());
            List<Package> packs=new ArrayList<Package>();
            serverHasData = false;
            while (!serverHasData) {
                serverHasData = con.getSocket().getInputStream().available() > 0;

            }
            packs = (List<Package>) con.getInput().readObject();

            con.sendMessageToServer("changeStatus");
            con.sendObjectToServer(p);
            con.sendObjectToServer(packs);
            serverHasData=false;
            while (!serverHasData) {
                serverHasData = con.getSocket().getInputStream().available() > 0;
            }
              p=(Package) con.getInput().readObject();
              con.sendMessageToServer("notificare");
              con.sendObjectToServer(p);

        }else {
            if(ok==1)
                view.showErrorMessage("Nu exista in baza de date");
        }




    }

    public List<Package> getPackagesByUser(String id) throws IOException, ClassNotFoundException {
        con.sendMessageToServer("getPacksByUser");
        con.sendMessageToServer(id);
        List<Package> packs=new ArrayList<Package>();
        boolean serverHasData = false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        packs = (List<Package>) con.getInput().readObject();



        return packs;
    }

    public List<Package> getPackages() throws IOException, ClassNotFoundException {
        con.sendMessageToServer("getPacks");
        List<Package> packs=new ArrayList<Package>();
        boolean serverHasData = false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;

        }
        packs = (ArrayList<Package>) con.getInput().readObject();



        return packs;
    }


    public List<Package> searchByDestinationPackage() throws IOException, ClassNotFoundException {
        String search=view.getSearch();
        List<Package> p=null;
        con.sendMessageToServer("searchPackageByDestination");
        con.sendMessageToServer(search);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        p = (List<Package>) con.getInput().readObject();


        return p;

        }

    public List<Package> searchBySourcePackage() throws IOException, ClassNotFoundException {
        String search=view.getSearch();
        List<Package> p=null;
        con.sendMessageToServer("searchPackageByDestination");
        con.sendMessageToServer(search);
        boolean serverHasData=false;
        while (!serverHasData) {
            serverHasData = con.getSocket().getInputStream().available() > 0;
        }
        p = (List<Package>) con.getInput().readObject();



        return p;

    }
    public List<Package> searchBySizePackage() throws IOException, ClassNotFoundException {
        String search=view.getSearch();
        int ok=1;
        if(!search.equals("1")&&!search.equals("2")&&!search.equals("3")){
            ok=0;
            view.showErrorMessage("Size input error");
        }
        if(ok==1){
            List<Package> p=null;
            con.sendMessageToServer("searchPackageBySize");
            con.sendMessageToServer(search);
            boolean serverHasData=false;
            while (!serverHasData) {
                serverHasData = con.getSocket().getInputStream().available() > 0;
            }
            p = (List<Package>) con.getInput().readObject();

        return p;
        }
        return null;

    }
    public List<Package> searchByUgencyPackage() throws IOException, ClassNotFoundException {
        String search=view.getSearch();
        int ok=1;
        if(!search.equals("true")&&!search.equals("false")){
            ok=0;
            view.showErrorMessage("Urgency input error");
        }
        if(ok==1){

            List<Package> p=null;
            con.sendMessageToServer("searchPackageByUrgency");
            con.sendMessageToServer(search);
            boolean serverHasData=false;
            while (!serverHasData) {
                serverHasData = con.getSocket().getInputStream().available() > 0;
            }
            p = (List<Package>) con.getInput().readObject();

            return p;
        }
        return null;

    }

    public void logout() {
    }
}

