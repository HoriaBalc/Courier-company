package presentation;

import entity.Package;
import repository.PackageRepo;
import start.Client;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class TXTReport extends Report{

    public  void generateReport(Client.Connection con){

        String path = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a location to save the report");
        FileWriter writer;
        int response = fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
        }

        try {
            writer=new FileWriter(path+".txt");
            writer.write("| Package | ");
            writer.write("CarID | ");
            writer.write("Sursa | ");
            writer.write("Destinatie |\n");

            ArrayList<Package> p =(ArrayList<Package>) PackageRepo.getAllPackages();
            for(Package pac:p){
                String name = pac.getName();
                writer.write("| "+pac.getName()+" | ");
                String carID;
                if(pac.getTranspCar()==null)
                    carID="";
                else
                    carID=pac.getTranspCar().getId();

                writer.write(carID+" | ");

                String source=pac.getSource();
                writer.write(source+" | ");
                String destination=pac.getDestination();
                writer.write(destination+" | \n");

            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
