package view;

import controller.AdministratorController;
import entity.Subscription;
import entity.TransportCar;
import entity.User;
import start.Client;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class AdministratorView extends JFrame implements IAdministratorView{
    private AdministratorController administratorController;

    public JFrame frame;
    private JTextField nameTxt;
    private JTextField usernameTxt;
    private JPasswordField passwordTxt;
    private JTextField typeTxt;

    private JTextField sizeTxt;
    private JTextField defectTxt;
    private JTextField numeTxt;

    private JTextField subscriptionTypeTxt;
    private JTextField userUsernameTxt;
    private JTextField subscriptionPostsTxt;
    private JTextField subscriptionPriceTxt;

    public JButton btnAddUser = new JButton("Add user");
    public JButton btnUpdateUser = new JButton("Update user");
    public JButton btnDeleteUser = new JButton("Delete user");
    public JButton btnGenerateReport = new JButton("Generate pdf report");
    public JButton btnGenerateReport1 = new JButton("Generate txt report");
    public JButton btnResetTables = new JButton("reset Tables");


    public JButton btnAddCar = new JButton("Add car");
    public JButton btnUpdateCar = new JButton("Update car");
    public JButton btnDeleteCar = new JButton("Delete car");
    public JButton btnLogout = new JButton("Log out");


    public JButton btnAddSubscription = new JButton("Add subscription");
    public JButton btnUpdateSubscription = new JButton("Update subscription");
    public JButton btnDeleteSubscription = new JButton("Delete subscription");
    public JButton btnChangeSubscription = new JButton("Change subscription");

    public Object[] row = new Object[6];
    public Object[] row1 = new Object[4];
    public Object[] row2 = new Object[4];
    JTable table1 = new JTable();
    JTable table = new JTable();
    JTable table2 = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
    public DefaultTableModel model1 = new DefaultTableModel();
    public DefaultTableModel model2 = new DefaultTableModel();

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private CardLayout cardLayout = new CardLayout();
    private Client.Connection con;

    public AdministratorView(Client.Connection con){
        this.con=con;
        frame =new JFrame("Administrator-View");
       // frame.setLayout(new BorderLayout());
        try {
            this.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        administratorController=new AdministratorController(this, con);

        frame.setVisible(true);

          btnAddUser.addActionListener(e->{
              try {
                  administratorController.addUser();
              } catch (IOException ioException) {
                  ioException.printStackTrace();
              } catch (ClassNotFoundException classNotFoundException) {
                  classNotFoundException.printStackTrace();
              }
              this.updateTable();});
          btnUpdateUser.addActionListener(e->{
              try {
                  administratorController.updateUser();
              } catch (IOException ioException) {
                  ioException.printStackTrace();
              } catch (ClassNotFoundException classNotFoundException) {
                  classNotFoundException.printStackTrace();
              }
              this.updateTable();});
          btnDeleteUser.addActionListener(e->{
              try {
                  administratorController.deleteUser();
              } catch (IOException | ClassNotFoundException ioException) {
                  ioException.printStackTrace();
              }
              this.updateTable();});


        btnGenerateReport.addActionListener(e -> {
            try {
                administratorController.generateReport("pdf");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        btnGenerateReport1.addActionListener(e -> {
            try {
                administratorController.generateReport("txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        btnAddCar.addActionListener(e->{
            try {
                administratorController.addCar();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            this.updateTable1();});
        btnUpdateCar.addActionListener(e->{
            try {
                administratorController.updateCar();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            this.updateTable1();});
        btnDeleteCar.addActionListener(e->{
            try {
                administratorController.deleteCar();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            this.updateTable1();});

        btnAddSubscription.addActionListener(e->{
            try {
                administratorController.addSubs();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            this.updateTable2();});


        btnUpdateSubscription.addActionListener(e->{
            try {
                administratorController.updateSubs();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            this.updateTable2();});

        btnDeleteSubscription.addActionListener(e->{
            try {
                administratorController.deleteSubs();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            this.updateTable2();});

        btnChangeSubscription.addActionListener(e->{
            try {
                administratorController.changeSubs();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            this.updateTable();
            this.updateTable2();});

        btnResetTables.addActionListener(e -> {
            this.updateTable();
            this.updateTable1();
            this.updateTable2();
        });

        btnLogout.addActionListener(e -> {
            administratorController.logout();
            frame.dispose();
        });

    }





    private void initialize() throws IOException, ClassNotFoundException {
        JLabel lblUsername = new JLabel("Username");
        JLabel lblName = new JLabel("Name");
        JLabel lblPassword = new JLabel("Password");
        JLabel lblType = new JLabel("Type");

        JLabel lblSize = new JLabel("Size");
        JLabel lblDefect = new JLabel("Broken");
        JLabel lblNume = new JLabel("Name Car");

        JLabel lblTypeSubs = new JLabel("Subscription type");
        JLabel lblUsernameUser = new JLabel("Username of user");
        JLabel lblPostsSubs = new JLabel("Number of posts");
        JLabel lblPriceUser = new JLabel("Price");

        Object[] columns = {"id", "Name", "Password", "Type", "Username", "Subscription"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        Object[] columns1 = {"id","NameCar", "Size", "Broken"};
        model1.setColumnIdentifiers(columns1);
        table1.setModel(model1);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 22);
        table.setFont(font);
        table.setRowHeight(25);
        table1.setBackground(Color.LIGHT_GRAY);
        table1.setForeground(Color.black);
        Font font1 = new Font("", 1, 22);
        table1.setFont(font);
        table1.setRowHeight(25);

        Object[] columns2 = {"id","Type", "Posts/Day", "Price"};
        model2.setColumnIdentifiers(columns2);
        table2.setModel(model2);
        Font font2 = new Font("", 1, 22);
        table2.setFont(font);
        table2.setRowHeight(25);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 600, 100);
        administratorController=new AdministratorController(this, con);

        if(administratorController.getUsers()!=null) {


            ArrayList<User> users =(ArrayList<User>) administratorController.getUsers();
            for (User u : users) {
                row[0]= u.getId();
                row[1] = u.getName();
                row[2] = u.getPassword();
                row[3] = u.getType();
                row[4]= u.getUsername();
                row[5]=null;
                if(u.getSubscription()!=null)
                    row[5]= u.getSubscription().getType();
                model.addRow(row);
            }
        }

        JScrollPane pane1 = new JScrollPane(table1);
        pane1.setBounds(0, 0, 800, 100);
        try {
            if(administratorController.getCars()!=null) {
                ArrayList<TransportCar> cars =(ArrayList<TransportCar>) administratorController.getCars();
                for (TransportCar c : cars) {
                    row1[0]= c.getId();
                    row1[1] = c.getNume();
                    row1[2] = c.getSize();
                    row1[3] = c.isDefect();


                    model1.addRow(row1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JScrollPane pane2 = new JScrollPane(table2);
        pane2.setBounds(0, 0, 800, 100);
        try {
            if(administratorController.getCars()!=null) {
                ArrayList<Subscription> subs =(ArrayList<Subscription>) administratorController.getSubs();
                for (Subscription s : subs) {
                    row2[0]= s.getId();
                    row2[1] = s.getType();
                    row2[2] = s.getNrPosts();
                    row2[3] = s.getPret();

                    model2.addRow(row2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        nameTxt= new JTextField(10);
        usernameTxt= new JTextField(10);
        passwordTxt= new JPasswordField(10);
        typeTxt= new JTextField(10);

        sizeTxt= new JTextField(10);
        numeTxt= new JTextField(10);
        defectTxt= new JTextField(10);

        userUsernameTxt= new JTextField(10);
        subscriptionTypeTxt= new JTextField(10);
        subscriptionPostsTxt= new JTextField(10);
        subscriptionPriceTxt= new JTextField(10);

        JLabel lblNewLabel = new JLabel("Information about user");
        JLabel lblNewLabel1 = new JLabel("Information about cars");

        JPanel panou = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());

        JPanel panou1 = new JPanel(new BorderLayout());
        JPanel panell = new JPanel(new BorderLayout());

        JPanel panou2 = new JPanel(new BorderLayout());
        JPanel panelll = new JPanel(new BorderLayout());

        JPanel panelButoane = new JPanel(new GridBagLayout());
        JPanel panelButoaneCar = new JPanel(new GridBagLayout());
        JPanel panelButoaneSubs = new JPanel(new GridLayout(2, 2));
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JPanel panel5 = new JPanel(new FlowLayout());
        JPanel panel6 = new JPanel(new FlowLayout());
        JPanel panel7 = new JPanel(new FlowLayout());
        JPanel panel8 = new JPanel(new FlowLayout());
        JPanel panel9 = new JPanel(new FlowLayout());
        JPanel panel10 = new JPanel(new FlowLayout());
        JPanel panel11 = new JPanel(new FlowLayout());
        JPanel panel12 = new JPanel(new FlowLayout());
        JPanel panel13 = new JPanel(new FlowLayout());

        panel1.add(lblNewLabel);
        panel2.add(lblUsername);
        panel2.add(usernameTxt);
        panel3.add(lblName);
        panel3.add(nameTxt);
        panel9.add(lblType);
        panel9.add(typeTxt);
        panel10.add(lblPassword);
        panel10.add(passwordTxt);
        JPanel panelAdd = new JPanel(new GridLayout(6, 1));

        JPanel panelAddCar = new JPanel(new GridLayout(6, 1));

        panel4.add(lblNewLabel1);
        panel5.add(lblSize);
        panel5.add(sizeTxt);
        panel6.add(lblNume);
        panel6.add(numeTxt);
        panel11.add(lblDefect);
        panel11.add(defectTxt);

        JPanel panelAddSubs = new JPanel(new GridLayout(5, 1));


        panel7.add(lblTypeSubs);
        panel7.add(subscriptionTypeTxt);
        panel8.add(lblUsernameUser);
        panel8.add(userUsernameTxt);
        panel12.add(lblPostsSubs);
        panel12.add(subscriptionPostsTxt);
        panel13.add(lblPriceUser);
        panel13.add(subscriptionPriceTxt);


        panelAdd.add(panel1);
        panelAdd.add(panel2);
        panelAdd.add(panel3);
        panelAdd.add(panel9);
        panelAdd.add(panel10);
        panelButoane.add(btnAddUser);
        panelButoane.add(btnUpdateUser);
        panelButoane.add(btnDeleteUser);
        panelButoane.add(btnGenerateReport);
        panelButoane.add(btnGenerateReport1);
        panelButoane.add(btnResetTables);

        panelAddCar.add(panel4);
        panelAddCar.add(panel5);
        panelAddCar.add(panel6);
        panelAddCar.add(panel11);
        panelButoaneCar.add(btnAddCar);
        panelButoaneCar.add(btnUpdateCar);
        panelButoaneCar.add(btnDeleteCar);
        panelButoaneCar.add(btnLogout);

        panelAddSubs.add(panel7);
        panelAddSubs.add(panel8);
        panelAddSubs.add(panel12);
        panelAddSubs.add(panel13);
        panelButoaneSubs.add(btnAddSubscription);
        panelButoaneSubs.add(btnUpdateSubscription);
        panelButoaneSubs.add(btnDeleteSubscription);
        panelButoaneSubs.add(btnChangeSubscription);

        panel.add(panelButoane, BorderLayout.NORTH);
        panel.add(panelAdd, BorderLayout.CENTER);
        panou.add(pane, BorderLayout.CENTER);
        panou.add(panel, BorderLayout.NORTH);

        panell.add(panelButoaneCar, BorderLayout.NORTH);
        panell.add(panelAddCar, BorderLayout.CENTER);
        panou1.add(pane1, BorderLayout.CENTER);
        panou1.add(panell, BorderLayout.NORTH);

        panelll.add(panelButoaneSubs, BorderLayout.NORTH);
        panelll.add(panelAddSubs, BorderLayout.CENTER);
        panou2.add(pane2,BorderLayout.CENTER);
        panou2.add(panelll, BorderLayout.NORTH);

        //frame.add(panou,BorderLayout.NORTH);
        JPanel panelInt=new JPanel(new GridLayout(1,2));
        panelInt.add(panou2);
        panelInt.add(panou1);

        JPanel panelFinal=new JPanel(new GridLayout(1,2));
        panelFinal.add(panou);
        panelFinal.add(panelInt);

        frame.add(panelFinal);
        frame.setSize(1700,1000);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

@Override
    public void updateTable(){
        model.setRowCount(0);
    ArrayList<User> users = null;
    try {
        users = (ArrayList<User>) administratorController.getUsers();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    for (User u : users) {
            row[0]= u.getId();
            row[1] = u.getName();
            row[2] = u.getPassword();
            row[3] = u.getType();
            row[4]= u.getUsername();
            row[5]=null;

            if(u.getSubscription()!=null)
                   row[5]= u.getSubscription().getType();

             model.addRow(row);

        }



    }



    public void updateTable1(){
        model1.setRowCount(0);
        ArrayList<TransportCar> tps = null;
        try {
            tps = (ArrayList<TransportCar>) administratorController.getCars();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (TransportCar c : tps) {
            row1[0]= c.getId();
            row1[1] = c.getNume();
            row1[2] = c.getSize();
            row1[3] = c.isDefect();

            model1.addRow(row1);
        }



    }

    public void updateTable2(){
        model2.setRowCount(0);
        ArrayList<Subscription> subs = null;


        try {
            subs = (ArrayList<Subscription>) administratorController.getSubs();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Subscription s : subs) {
            row2[0]= s.getId();
            row2[1] = s.getType();
            row2[2] = s.getNrPosts();
            row2[3] = s.getPret();

            model2.addRow(row2);
        }

    }







    @Override
    public JTextField getUsername() { return usernameTxt;
    }

    @Override
    public JPasswordField getPassword() { return passwordTxt;
    }

    @Override
    public String getName() { return nameTxt.getText();
    }

    @Override
    public String getTypes() { return typeTxt.getText();
    }

    @Override
    public String getNume() {
        return numeTxt.getText();
    }
    @Override
    public String getDefect() {
        return defectTxt.getText();
    }
    @Override
    public String getSizes() {
        return sizeTxt.getText();
    }

    @Override
    public String getTypeSub(){return subscriptionTypeTxt.getText();}
    @Override
    public String getPostSub(){return subscriptionPostsTxt.getText();}
    @Override
    public String getPriceSub(){return subscriptionPriceTxt.getText();}

    @Override
    public String getUserUsernameTxt() {
        return userUsernameTxt.getText();
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
