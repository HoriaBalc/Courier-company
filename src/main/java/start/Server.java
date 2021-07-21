package start;

import entity.*;
import entity.Package;

import repository.*;
import view.ClientView;
;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;

import java.util.List;

public class Server
{
    static class Connection extends Thread {
        private final Socket clientSocket;
        private long lastSentMessageMillis;
        private static List<ClientView> clients=new ArrayList<ClientView>();
        public Connection(Socket clientSocket)
        {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run()
        {
            try(ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream()))
            {
                while (clientSocket.isConnected())
                {
                                 boolean clientHasData = clientSocket.getInputStream().available() > 0;
                    if (clientHasData) {
                        String msg = (String) input.readObject();
                        System.out.println(msg);
                        if (msg.equals("findUserByUsername")) {
                            String username = (String) input.readObject();
                            System.out.println(username);
                            User user = UserRepo.findUserByUsername(username);
                            output.writeObject(user);
                        }

                        if (msg.equals("findPackageByName")) {
                            String name = (String) input.readObject();
                            System.out.println(name);
                            Package pack = PackageRepo.findPackageByName(name);
                            output.writeObject(pack);
                        }

                        if(msg.equals("findSubByType")){
                            String type=(String) input.readObject();
                            System.out.println(type);
                            Subscription subscription = SubscriptionRepo.findSubscriptionByType(type);
                            output.writeObject(subscription);
                        }

                        if (msg.equals("login")) {
                            ClientView view=(ClientView)input.readObject();
                            clients.add(view);
                        }

                        if (msg.equals("logout")) {
                            ClientView view=(ClientView)input.readObject();
                            clients.remove(view);
                        }

                        if (msg.equals("request")) {
                            Package p=(Package) input.readObject();
                            User user=(User) input.readObject();
                            ClientView v =(ClientView) input.readObject();
                            UserRepo.updateUser(user);
                            Package pack = PackageRepo.findPackageByName(p.getName());
                            if (pack == null) {
                                PackageRequest request = new PackageRequest(user, p.getId());
                                PackageRepo.insertNewPackage(p);
                                PackageRequestRepo.insertNewPackRequest(request);
                            }else{ v.showErrorMessage("Exista deja in baza de date"); }




                        }

                        if (msg.equals("request1")) {
                            Package p=(Package) input.readObject();
                            User user=(User) input.readObject();
                            int m=(int) input.readObject();
                            user.setMoney(m);
                            UserRepo.updateUser(user);
                            ClientView v =(ClientView) input.readObject();
                            UserRepo.updateUser(user);
                            Package pack = PackageRepo.findPackageByName(p.getName());
                            if (pack == null) {
                                PackageRequest request = new PackageRequest(user, p.getId());
                                PackageRepo.insertNewPackage(p);
                                PackageRequestRepo.insertNewPackRequest(request);
                            }else{ v.showErrorMessage("Exista deja in baza de date"); }


                        }

                        if(msg.equals("notificare")){
                            //User user = (User) input.readObject();
                            Package p=(Package) input.readObject();
                            PackageRequest packageRequest=PackageRequestRepo.findByPackageName(p.getName());
                            User user=packageRequest.getClient();
                            for (ClientView c:clients){
                                if(user.getUsername().equals(c.getUser().getUsername())){
                                    c.showNotification("Status schimbat in delivered pentru pachetul: "+p.getName());
                                }

                            }

                        }


                        if(msg.equals("ListaTabelPackageRequest")){
                            User user = (User) input.readObject();
                            List<PackageRequest> packageRequest=new ArrayList<PackageRequest>();

                            List<PackageRequest> request=PackageRequestRepo.findAllPackageRequest();
                            for(PackageRequest p:request){
                                if(p.getClient().getId().equals(user.getId()))
                                    packageRequest.add(p);

                            }

                            output.writeObject(packageRequest);

                        }

                        if(msg.equals("TabelPackage")){
                            String idPackage=(String) input.readObject();
                            Package pack=PackageRepo.findPackageById(idPackage);
                            output.writeObject(pack);


                        }

                        if (msg.equals("findCarByName")) {
                            String name = (String) input.readObject();
                            System.out.println(name);
                            TransportCar car = TransportCarRepo.findCarByName(name);
                            output.writeObject(car);
                        }

                        if(msg.equals("searchPackageByDestination")){
                            String s=(String) input.readObject();
                            ArrayList<Package> pL=(ArrayList<Package>)PackageRepo.findPackageByDestination(s);
                            output.writeObject(pL);

                        }



                        if(msg.equals("searchPackageBySource")){
                            String s=(String) input.readObject();
                            ArrayList<Package> pL=(ArrayList<Package>)PackageRepo.findPackageBySource(s);
                            output.writeObject(pL);

                        }

                        if(msg.equals("searchPackageBySize")){
                            String s=(String) input.readObject();
                            ArrayList<Package> pL=(ArrayList<Package>)PackageRepo.findPackageBySize(Integer.parseInt(s));
                            output.writeObject(pL);

                        }

                        if(msg.equals("searchPackageByUrgency")){
                            String s=(String) input.readObject();
                            ArrayList<Package> pL=(ArrayList<Package>)PackageRepo.findPackageByUrgency(Boolean.valueOf(s));
                            output.writeObject(pL);

                        }


                        if (msg.equals("insertNewUser")) {
                            String username = (String) input.readObject();
                            String name = (String) input.readObject();
                            String password = (String) input.readObject();
                            System.out.println(username);
                            Subscription subscription=SubscriptionRepo.findSubscriptionByType("regular");
                            User user= new User(username, name, 2, password, subscription);
                            UserRepo.insertNewUser(user);
                        }

                        if (msg.equals("addUser")) {
                            String username = (String) input.readObject();
                            String name = (String) input.readObject();
                            String password = (String) input.readObject();
                            String str =(String) input.readObject();
                            int type=Integer.parseInt(str);
                            System.out.println(username);
                            Subscription subscription=SubscriptionRepo.findSubscriptionByType("regular");
                            User user= new User(username, name, type, password, subscription);
                            UserRepo.insertNewUser(user);

                        }

                        if (msg.equals("updateUser")) {
                            String username = (String) input.readObject();
                            String name = (String) input.readObject();
                            String password = (String) input.readObject();
                            String str =(String) input.readObject();
                            int type=Integer.parseInt(str);
                            System.out.println(username);
                            User user=UserRepo.findUserByUsername(username);
                            user.setName(name);
                            user.setPassword(password);
                            user.setType(type);
                            UserRepo.updateUser(user);

                        }

                        if (msg.equals("deleteUser")) {
                            User user = (User) input.readObject();
                            List<PackageRequest> packageRequestList= PackageRequestRepo.findAllPackageRequest();
                            //Iterator<PackageRequest> i = packageRequestList.iterator();
                            List<PackageRequest> packageRequestsDeleted=new ArrayList<PackageRequest>();
                            for(PackageRequest packageRequest:packageRequestList){
                                if(packageRequest.getClient().getUsername().equals(user.getUsername())){
                                    packageRequestsDeleted.add(packageRequest);
                                    PackageRequestRepo.deletePackageRequest(packageRequest);

                                }

                            }
                            packageRequestList.removeIf(p -> p.getClient().getUsername().equals(user.getUsername()));


                            Subscription s=user.getSubscription();
                            List<User> users=s.getUsers();
                            users.removeIf(us -> us.getUsername().equals(user.getUsername()));
                            s.setUsers(users);
                            SubscriptionRepo.updateSubscription(s);

                            UserRepo.deleteUser(user);

                        }



                        if(msg.equals("addCar")){
                            String name=(String) input.readObject();
                            String d=(String) input.readObject();
                            String s=(String) input.readObject();

                            int siz=Integer.parseInt(s);
                            boolean def=Boolean.valueOf(d);
                            TransportCar car=new TransportCar(siz,name,def);
                            TransportCarRepo.insertNewCar(car);
                        }

                        if (msg.equals("updateCar")) {
                            String name = (String) input.readObject();
                            String d = (String) input.readObject();
                            String s = (String) input.readObject();
                           // String str =(String) input.readObject();
                            int siz=Integer.parseInt(s);
                            boolean def=Boolean.valueOf(d);
                            System.out.println(name);
                            //User user= new User(username, name, type, password);
                            TransportCar transportCar=TransportCarRepo.findCarByName(name);
                            transportCar.setNume(name);
                            transportCar.setSize(siz);
                            transportCar.setDefect(def);
                            TransportCarRepo.updateCar(transportCar);

                        }

                        if (msg.equals("deleteCar")) {
                            TransportCar car = (TransportCar) input.readObject();
                            TransportCarRepo.deleteCar(car);


                        }

                        if(msg.equals("dadada")){
                            TransportCar t=(TransportCar) input.readObject();
                            List<Package> packages = t.getPack();
                            for (Package p : packages) {
                                if (p.getTranspCar().getId().equals(t.getId())) {
                                    p.setTranspCar(null);

                                    PackageRepo.updatePackage(p);
                                }

                            }
                            output.writeObject(t);
                        }

                        if(msg.equals("nununu")){
                            List<Package> packages;
                            TransportCar tp=(TransportCar) input.readObject();
                            packages= tp.getPack();
                            packages.removeIf(o->o.getTranspCar().equals(tp));
                            output.writeObject(packages);


                        }

                        if(msg.equals("getUsers")){
                            List<User> users =UserRepo.getUsers();
                            System.out.println(users);
                            output.writeObject(users);
                        }

                        if(msg.equals("getCars")){
                            List<TransportCar> cars = TransportCarRepo.getCars();
                            System.out.println(cars);
                            output.writeObject(cars);
                        }

                        if(msg.equals("getSubs")){
                            List<Subscription> subscriptions = SubscriptionRepo.getSubs();
                            System.out.println(subscriptions);
                            output.writeObject(subscriptions);
                        }

                        if(msg.equals("getPacks")){
                            List<Package> packs = PackageRepo.getAllPackages();
                            System.out.println(packs);
                            output.writeObject(packs);
                        }

                        if(msg.equals("addToList")){
                            Package p=(Package) input.readObject();
                            User u=(User) input.readObject();
                            List<Package> packages=new ArrayList<Package>();
                            if(p.getIdUser()==null && p.getStatus()==1){
                                p.setIdUser(u.getId());
                                PackageRepo.updatePackage(p);

                                for(Package pac:PackageRepo.getAllPackages()){
                                    if(u.getId().equals(pac.getIdUser())){
                                        packages.add(pac);
                                    }

                                }

                            }
                            output.writeObject(packages);

                        }

                        if(msg.equals("changeStatus")){
                            Package p=(Package) input.readObject();
                            List<Package> packs=(List<Package>) input.readObject();
                            for(Package pack:packs){
                                if(p.getId().equals(pack.getId())){
                                    if(p.getStatus()==1){
                                        p.setStatus(2);
                                        PackageRepo.updatePackage(p);

                                    }
                                    System.out.println("a intrat in lista");
                                }
                            }
                            output.writeObject(p);

                        }

                        if(msg.equals("getPacksByUser")){
                            String id=(String) input.readObject();
                            User u=UserRepo.findUserById(id);
                            List<Package> packages=new ArrayList<Package>();
                            for(Package p:PackageRepo.getAllPackages()){
                                if(u.getId().equals(p.getIdUser())){
                                    packages.add(p);
                                }
                            }
                            output.writeObject(packages);
                        }

                        if (msg.equals("addSub")) {
                            Subscription subscription = (Subscription) input.readObject();
                            SubscriptionRepo.insertNewSubscription(subscription);

                        }

                        if (msg.equals("updateSub")) {
                            Subscription subscription = (Subscription) input.readObject();
                            SubscriptionRepo.updateSubscription(subscription);

                        }

                        if (msg.equals("updateUser1")) {
                            User user = (User) input.readObject();
                            UserRepo.updateUser(user);

                        }

                        if (msg.equals("updateUser2")) {
                            User user = (User) input.readObject();
                            int m=(int) input.readObject();
                            user.setMoney(m);
                            UserRepo.updateUser(user);

                        }

                        if (msg.equals("updateUser3")) {
                            User user = (User) input.readObject();
                            int m=(int) input.readObject();
                            Subscription s=(Subscription) input.readObject();
                            user.setMoney(m);
                            user.setSubscription(s);
                            UserRepo.updateUser(user);
                            //output.writeObject(user);

                        }


                        if (msg.equals("deleteSub")) {
                            Subscription subscription = (Subscription) input.readObject();
                            List<User> users=UserRepo.findUserBySubscription(subscription);
                            for(User u:users){
                                if(subscription.getType().equals(u.getSubscription().getType())){
                                    Subscription subscription1=SubscriptionRepo.findSubscriptionByType("regular");
                                    u.setSubscription(subscription1);
                                    UserRepo.updateUser(u);
                                }

                            }
                            SubscriptionRepo.deleteSubscription(subscription);

                        }

                        if (msg.equals("addPackage")) {
                            Package pack = (Package) input.readObject();
                            PackageRepo.insertNewPackage(pack);

                        }

                        if (msg.equals("updatePackage")) {
                            Package pack = (Package) input.readObject();
                            PackageRepo.updatePackage(pack);

                        }

                        if (msg.equals("deletePackage")) {
                            Package pack = (Package) input.readObject();
                            PackageRepo.deletePackage(pack);


                        }



                    }

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                System.out.println(Instant.now() + " Client disconnected?");
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }

            try
            {
                clientSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException
    {
        try (ServerSocket socket = new ServerSocket(3000))
        {
            while (true)
            {
                System.out.println(Instant.now() + " Waiting for client...");
                Socket clientSocket = socket.accept();
                new Connection(clientSocket).start();
            }
        }
    }
}

