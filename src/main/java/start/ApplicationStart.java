package start;

import view.LoginView;









public class ApplicationStart {



    public static void main(String[] args) {



        //PackageRepo.insertNewPackage(p);
       /* PackageRepo.findPackageById(2);
        ArrayList<Package> pack=(ArrayList<Package>)PackageRepo.findPackageByDestination("d");
        for(Package i:pack){
            System.out.println(i.getSource()+" "+i.getDestination()+" "+i.getId());


        }
*/
       /* TransportCar tp=new TransportCar(3,"duta", false);
        Package p=new Package(2, "dadas", "dest", 2,true, tp);
        Package p1=new Package(2, "dadas", "dest", 2,true, tp);

        //User u=new User("a","a", 0, "a");
        //UserRepo.insertNewUser(u);
        TransportCarRepo.insertNewCar(tp);
        PackageRepo.insertNewPackage(p);
        PackageRepo.insertNewPackage(p1);
        TransportCar t=TransportCarRepo.findCarByName(tp.getNume());
        //Hibernate.initialize(t.getPack());
        List<Package> packageList=t.getPack();*/
/*
        TransportCar tt=TransportCarRepo.findCarById(t.getId());


        if(tt.getPack()!=null){
           // ArrayList<Package> packageList=(ArrayList<Package>)t.getPack();
           // for(Package pac : packageList){
           //     System.out.println(pac.getId()+" "+pac.getTranspCar());
            for(int i=0;i<tt.getPack().size();i++){
                    System.out.println(tt.getPack().get(i).getId()+" "+tt.getPack().get(i).getTranspCar());


            }



            System.out.println("dadada");
        }*/

        Client.Connection con = null;//new Client.Connection(new Socket("localhost", 3000));
        new LoginView(con);

    }

}
