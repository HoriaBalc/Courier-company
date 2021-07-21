package presentation;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import repository.PackageRepo;
import entity.Package;
import start.Client;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.ArrayList;

public class PDFReport extends Report{

    public  void generateReport(Client.Connection con){


        Document document = new Document();
        String path = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a location to save the report");

        int response = fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
        }

            try {
                PdfWriter.getInstance(document, new FileOutputStream(path+".pdf"));
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            document.open();
            PdfPTable table = new PdfPTable(4);
            PdfPCell table_cell;
            table.addCell("Package");
            table.addCell("CarID");
            table.addCell("Sursa");
            table.addCell("Destinatie");
            ArrayList<Package> p =(ArrayList<Package>) PackageRepo.getAllPackages();
            for(Package pac:p){
                String name = pac.getName();
                table_cell=new PdfPCell(new Phrase(name));
                table.addCell(table_cell);
                String carID;
                if(pac.getTranspCar()==null)
                    carID="";
                else
                    carID=pac.getTranspCar().getId();
                table_cell=new PdfPCell(new Phrase(carID));
                table.addCell(table_cell);
                String source=pac.getSource();
                table_cell=new PdfPCell(new Phrase(source));
                table.addCell(table_cell);
                String destination=pac.getDestination();
                table_cell=new PdfPCell(new Phrase(destination));
                table.addCell(table_cell);
            }
        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();

        }


    }

