package view;

import controller.AdministratorController;
import controller.RegularUserController;
import entity.Package;
import entity.User;
import start.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class RegularView extends JFrame implements IRegularUserView{
    private RegularUserController regularUserController;

    public JFrame frame;
    private JTextField sourceTxt;
    private JTextField destinationTxt;
    private JTextField sizeTxt;
    private JTextField urgencyTxt;
    private JTextField statusTxt;
    private JTextField nameTxt;
    private JTextField nameCarTxt;
    private JTextField search;
    private JTextField packageTxt;

    public JButton btnAddPackage = new JButton("Add package");
    public JButton btnUpdatePackage = new JButton("Update package");
    public JButton btnDeletePackage = new JButton("Delete package");
    public JButton btnSearchByDestination = new JButton("SearchByDestination");
    public JButton btnSearchBySource = new JButton("SearchBySource");
    public JButton btnSearchBySize = new JButton("SearchBySize");
    public JButton btnSearchByUrgency = new JButton("SearchByUrgency");
    public JButton btnReset= new JButton("ResetTable");
    public JButton btnLogout= new JButton("Log out");

    public JButton btnAddToList = new JButton("AddToMyList");
    public JButton btnChangeStatus = new JButton("ChangeStatus");

    public Object[] row = new Object[8];
    JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel();

    public Object[] row1 = new Object[8];
    JTable table1 = new JTable();
    public DefaultTableModel model1 = new DefaultTableModel();

    public Object[] row2 = new Object[2];
    JTable table2 = new JTable();
    public DefaultTableModel model2 = new DefaultTableModel();

    public int tip=0;
    private Client.Connection con;
    private User user;

public RegularView(Client.Connection con, User user) throws IOException, ClassNotFoundException {
    this.con=con;
    this.user=user;
    frame =new JFrame("Regular-User-View");
    this.initialize();
    regularUserController=new RegularUserController(this, con, user);

    frame.setVisible(true);
    btnAddPackage.addActionListener(e->{
        try {
            regularUserController.addPackage();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        try {
            this.updateTable();
            this.updateTable1();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    });

    btnLogout.addActionListener(e -> {




    });

    btnUpdatePackage.addActionListener(e->{
        try {
            regularUserController.updatePackage();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        try {
            this.updateTable();
            this.updateTable1();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    });
    btnDeletePackage.addActionListener(e->{
        try {
            regularUserController.deletePackage();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        try {
            this.updateTable();
            this.updateTable1();
            this.updateTable2();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    });

    btnReset.addActionListener(e -> {

        try {
            this.updateTable();
            this.updateTable1();
            this.updateTable2();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

    });

    btnLogout.addActionListener(e -> {
        regularUserController.logout();
        frame.dispose();
    });

    btnAddToList.addActionListener(e->{
        try {
            regularUserController.addToList();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        try {
            this.updateTable();
            this.updateTable1();
            this.updateTable2();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    });

    btnChangeStatus.addActionListener(e->{
        try {
            regularUserController.changeStatus();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        try {
            this.updateTable();
            this.updateTable1();
            this.updateTable2();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    });


    btnSearchByDestination.addActionListener(e->{
    this.setTip(0);
        try {
            this.updateTable1();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    });

    btnSearchBySize.addActionListener(e->{
        this.setTip(2);
        try {
            this.updateTable1();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    });

    btnSearchBySource.addActionListener(e->{
        this.setTip(1);
        try {
            this.updateTable1();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    });

    btnSearchByUrgency.addActionListener(e->{
        this.setTip(3);
        try {
            this.updateTable1();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    });

}

private void initialize() throws IOException, ClassNotFoundException {
    JLabel lblDestination= new JLabel("Destination");
    JLabel lblSource= new JLabel("Source");
    JLabel lblName= new JLabel("Name");
    JLabel lblStatus= new JLabel("Status");
    JLabel lblSize= new JLabel("Size");
    JLabel lblUrgency= new JLabel("Urgency");
    JLabel lblSearch= new JLabel("Search");
    JLabel lblNameCar= new JLabel("Name Car");
    JLabel lblPackageName= new JLabel("Package Name");
    Object[] columns = {"id", "Name", "Destination", "Source", "Status", "Size", "Urgency", "CarId"};
    model.setColumnIdentifiers(columns);
    table.setModel(model);
    table.setBackground(Color.LIGHT_GRAY);
    table.setForeground(Color.black);
    Font font = new Font("", 1, 22);
    table.setFont(font);
    table.setRowHeight(25);
    JScrollPane pane = new JScrollPane(table);
    pane.setBounds(0, 0, 600, 100);
    regularUserController=new RegularUserController(this, con, user);

    Object[] columns1 = {"id", "Name", "Destination", "Source", "Status", "Size", "Urgency", "CarId"};
    model1.setColumnIdentifiers(columns1);
    table1.setModel(model1);
    table1.setBackground(Color.LIGHT_GRAY);
    table1.setForeground(Color.black);
    Font font1 = new Font("", 1, 22);
    table1.setFont(font1);
    table1.setRowHeight(25);
    JScrollPane pane1 = new JScrollPane(table1);
    pane1.setBounds(0, 0, 600, 100);

    Object[] columns2 = { "PackageName", "Status"};
    model2.setColumnIdentifiers(columns2);
    table2.setModel(model2);
    table2.setBackground(Color.LIGHT_GRAY);
    table2.setForeground(Color.black);
    Font font2 = new Font("", 1, 22);
    table2.setFont(font1);
    table2.setRowHeight(25);
    JScrollPane pane2 = new JScrollPane(table2);
    pane1.setBounds(0, 0, 600, 100);

    if(regularUserController.getPackages()!=null) {
        ArrayList<Package> packs =(ArrayList<Package>) regularUserController.getPackagesByUser(user.getId());
        for (Package p : packs) {
            row2[0]= p.getName();
            row2[1]= p.getStatus();
            model2.addRow(row2);
        }
    }


    if(regularUserController.getPackages()!=null) {
        ArrayList<Package> packs =(ArrayList<Package>) regularUserController.getPackages();
        for (Package p : packs) {
            row1[0]= p.getId();
            row1[1]= p.getName();
            row1[2] = p.getDestination();
            row1[3] = p.getSource();
            row1[4] = p.getStatus();
            row1[5]= p.getSize();
            row1[6]= p.isUrgency();
            if(p.getTranspCar()==null)
                row1[7]="";
            else
            row1[7]= p.getTranspCar().getNume();

            model1.addRow(row1);
        }
    }

    if(regularUserController.getPackages()!=null) {
        ArrayList<Package> packs =(ArrayList<Package>) regularUserController.getPackages();
        for (Package p : packs) {
            row[0]= p.getId();
            row[1]= p.getName();
            row[2] = p.getDestination();
            row[3] = p.getSource();
            row[4] = p.getStatus();
            row[5]= p.getSize();
            row[6]= p.isUrgency();
            if(p.getTranspCar()==null)
                row[7]="";
            else
            row[7]= p.getTranspCar().getNume();

            model.addRow(row);
        }
    }

    destinationTxt= new JTextField(10);
    sourceTxt= new JTextField(10);
    sizeTxt= new JTextField(10);
    statusTxt= new JTextField(10);
    urgencyTxt= new JTextField(10);
    search= new JTextField(10);
    nameTxt= new JTextField(10);
    nameCarTxt= new JTextField(10);
    packageTxt=new JTextField(10);
    JLabel lblNewLabel = new JLabel("Information about package");

    JPanel panou = new JPanel(new BorderLayout());
    JPanel panel = new JPanel(new BorderLayout());
    JPanel panelButoane = new JPanel(new GridBagLayout());
    JPanel panelButoaneCar = new JPanel(new GridBagLayout());
    JPanel panelButoaneList = new JPanel(new GridBagLayout());
    JPanel panel1 = new JPanel(new FlowLayout());
    JPanel panel2 = new JPanel(new FlowLayout());
    JPanel panel3 = new JPanel(new FlowLayout());
    JPanel panel4 = new JPanel(new FlowLayout());
    JPanel panel5 = new JPanel(new FlowLayout());
    JPanel panel6 = new JPanel(new FlowLayout());

    panel1.add(lblNewLabel);

    panel2.add(lblName);
    panel2.add(nameTxt);
    panel2.add(lblSize);
    panel2.add(sizeTxt);

    panel3.add(lblSource);
    panel3.add(sourceTxt);
    panel3.add(lblDestination);
    panel3.add(destinationTxt);

    panel6.add(lblStatus);
    panel6.add(statusTxt);
    panel6.add(lblUrgency);
    panel6.add(urgencyTxt);
    panel6.add(lblNameCar);
    panel6.add(nameCarTxt);
    JPanel panelAdd = new JPanel(new GridLayout(4, 1));
    panelAdd.add(panel1);
    panelAdd.add(panel2);
    panelAdd.add(panel3);
    panelAdd.add(panel6);
    panelButoane.add(btnAddPackage);
    panelButoane.add(btnUpdatePackage);
    panelButoane.add(btnDeletePackage);
    panelButoaneCar.add(btnSearchByDestination);
    panelButoaneCar.add(btnSearchBySource);
    panelButoaneCar.add(btnSearchBySize);
    panelButoaneCar.add(btnSearchByUrgency);
    panelButoaneList.add(btnAddToList);
    panelButoaneList.add(btnChangeStatus);
    panelButoaneList.add(btnReset);
    panelButoaneList.add(btnLogout);

    JPanel panelAddCar = new JPanel(new GridLayout(4, 1));
    panel4.add(lblSearch);
    panel4.add(search);
    panelAddCar.add(panel4);

    JPanel panelAddList = new JPanel(new GridLayout(4, 1));
    panel5.add(lblPackageName);
    panel5.add(packageTxt);
    panelAddList.add(panel5);

    panel.add(panelButoane, BorderLayout.NORTH);
    panel.add(panelAdd, BorderLayout.CENTER);
    panou.add(pane, BorderLayout.CENTER);
    panou.add(panel, BorderLayout.NORTH);
    JPanel panou1 = new JPanel(new BorderLayout());
    JPanel panell = new JPanel(new BorderLayout());

    panell.add(panelButoaneCar, BorderLayout.NORTH);
    panell.add(panelAddCar, BorderLayout.CENTER);
    panou1.add(pane1, BorderLayout.CENTER);
    panou1.add(panell, BorderLayout.NORTH);

    JPanel panou2 = new JPanel(new BorderLayout());
    JPanel panelll = new JPanel(new BorderLayout());

    panelll.add(panelButoaneList, BorderLayout.NORTH);
    panelll.add(panelAddList, BorderLayout.CENTER);
    panou2.add(pane2, BorderLayout.CENTER);
    panou2.add(panelll, BorderLayout.NORTH);


    JPanel panelFinal=new JPanel(new GridLayout(1,3));
    panelFinal.add(panou);
    panelFinal.add(panou1);
    panelFinal.add(panou2);
    //frame.add(panou1);
    frame.add(panelFinal);
    frame.setSize(1700,1000);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

}


    public void updateTable() throws IOException, ClassNotFoundException {
        model.setRowCount(0);
        ArrayList<Package> packs =(ArrayList<Package>) regularUserController.getPackages();
        for (Package p : packs) {
            row[0]= p.getId();
            row[1]= p.getName();
            row[2] = p.getDestination();
            row[3] = p.getSource();
            row[4] = p.getStatus();
            row[5]= p.getSize();
            row[6]= p.isUrgency();
            if(p.getTranspCar()==null)
                row[7]="";
            else
            row[7]= p.getTranspCar().getNume();
            model.addRow(row);
        }



    }
    public void updateTable2() throws IOException, ClassNotFoundException {
        model2.setRowCount(0);
        ArrayList<Package> packs =(ArrayList<Package>) regularUserController.getPackagesByUser(user.getId());
        for (Package p : packs) {
        row2[0]= p.getName();
        row2[1]= p.getStatus();

        model2.addRow(row2);
    }



}

    public void updateTable1() throws IOException, ClassNotFoundException {
        model1.setRowCount(0);
        ArrayList<Package> packs=new ArrayList<Package>();
        if(this.getTip()==0)
         packs =(ArrayList<Package>) regularUserController.searchByDestinationPackage();
        if(this.getTip()==1)
            packs =(ArrayList<Package>) regularUserController.searchBySourcePackage();
        if(this.getTip()==2)
            packs =(ArrayList<Package>) regularUserController.searchBySizePackage();
        if(this.getTip()==3)
            packs =(ArrayList<Package>) regularUserController.searchByUgencyPackage();

        if(packs!=null) {
            for (Package p : packs) {
                row1[0] = p.getId();
                row1[1] = p.getName();
                row1[2] = p.getDestination();
                row1[3] = p.getSource();
                row1[4] = p.getStatus();
                row1[5] = p.getSize();
                row1[6] = p.isUrgency();
                if (p.getTranspCar() == null)
                    row1[7] = "";
                else
                    row1[7] = p.getTranspCar().getNume();

                model1.addRow(row1);
            }
        }


    }

@Override
public String getDestination(){
    return destinationTxt.getText();
}

    @Override
    public String getUrgency() {
        return urgencyTxt.getText();
    }

    @Override
    public String getSource(){
        return sourceTxt.getText();
    }

    @Override
    public String getStatus(){
        return statusTxt.getText();
    }

    @Override
    public String getSizes(){
        return sizeTxt.getText();
    }
    @Override
    public String getName(){
        return nameTxt.getText();
    }
    @Override
    public String getNameCar(){
        return nameCarTxt.getText();
    }
    @Override
    public String getSearch(){
        return search.getText();
    }

    @Override
    public int getTip() {
        return tip;
    }
    @Override
    public void setTip(int tip) {
        this.tip = tip;
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    @Override
    public String getPackageTxt() {
        return packageTxt.getText();
    }
}
