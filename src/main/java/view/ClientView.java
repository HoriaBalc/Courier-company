package view;

import controller.ClientController;
import entity.PackageRequest;
import entity.Subscription;
import entity.User;
import start.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientView extends JFrame  implements IClientUserView{
    public JFrame frame;
    private JTextField packageNameTxt;
    private JTextField packageDestinationTxt;
    private JTextField packageSourceTxt;
    private JTextField packageUrgencyTxt;
    private JTextField packageSizeTxt;
    private JTextField moneyTxt;


    private String subs;

    private ClientController clientController;
    public JButton btnRequest = new JButton("requestPackage");
    public JButton btnChangeSubs = new JButton("Change Subscription");
    public JButton btnPayExtra = new JButton("Pay 5$ for one package");
    public JButton btnAddMoney = new JButton("Add money");
    public JButton btnLogout= new JButton("Log out");

    public JComboBox<String> subscriptionList = new JComboBox<String>();

    public Object[] row = new Object[4];
    JTable table = new JTable();
    public Object[] row1 = new Object[1];
    JTable table1 = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
    public DefaultTableModel model1 = new DefaultTableModel();
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private CardLayout cardLayout = new CardLayout();
    private Client.Connection con;
    private User user;
    public ClientView(Client.Connection con, User u){
        this.user=u;
        this.con=con;
        frame=new JFrame("Client-View");
        try {
            this.initialize();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientController=new ClientController(this,con,user);
        frame.setVisible(true);
        btnRequest.addActionListener(e->{

            try {
                clientController.request();
                this.updateTable();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        btnAddMoney.addActionListener(e -> {
            try {
                clientController.addMoney();
                this.updateTable();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

        });

        btnChangeSubs.addActionListener(e -> {
            try {
                clientController.changeSubs();
                this.updateTable();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

        });

        btnPayExtra.addActionListener(e -> {
            try {
                clientController.payExtra();
                this.updateTable();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

        });

        btnLogout.addActionListener(e -> {
            try {
                clientController.logout();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            frame.dispose();

        });
        subscriptionList.addItemListener(arg0 -> subs = subscriptionList.getSelectedItem().toString());



    }

    public void showNotification(String notificationMessage) {
        JOptionPane notification = new JOptionPane(notificationMessage, JOptionPane.WARNING_MESSAGE);
        JDialog dialog = notification.createDialog(notificationMessage);
        dialog.setAlwaysOnTop(true);
        SwingUtilities.invokeLater(
                () -> dialog.setVisible(true)
        );
    }

    private void initialize() throws IOException, ClassNotFoundException {

        JLabel lblNamePackage = new JLabel("NamePackage");
        JLabel lblDestinationPackage = new JLabel("DestinationPackage");
        JLabel lblSourcePackage = new JLabel("SourcePackage");
        JLabel lblSizePackage = new JLabel("SizePackage");
        JLabel lblUrgencyPackage = new JLabel("UrgencyPackage");
        JLabel lblSubscription = new JLabel("Subscription");
        JLabel lblMoney = new JLabel("Amount of money");
        Object[] columns = {"Package Name", "Id Client", "Id Package", "Data"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 22);
        table.setFont(font);
        table.setRowHeight(25);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 600, 100);
        clientController=new ClientController(this,con,user);

        Object[] columns1 = {"Money"};
        model1.setColumnIdentifiers(columns1);
        table1.setModel(model1);
        table1.setBackground(Color.LIGHT_GRAY);
        table1.setForeground(Color.black);
        Font font1 = new Font("", 1, 22);
        table1.setFont(font1);
        table1.setRowHeight(25);
        JScrollPane pane1 = new JScrollPane(table1);
        row1[0]=this.user.getMoney()+"";
        model1.addRow(row1);
        if(clientController.getPacks()!=null) {

            List<PackageRequest> packs = clientController.getPacks();
            for (PackageRequest p: packs) {

                if(p.getClient().getId().equals(user.getId())){
                    row[0] =clientController.getPack(p.getId()).getName();
                    row[1] =p.getClient().getUsername();
                    row[2] =p.getId();
                    row[3] =p.getData();
                    model.addRow(row);
                }
            }
        }

        ArrayList<Subscription> subs = (ArrayList<Subscription>) clientController.getSubs();
        subscriptionList.addItem("");
        for (Subscription subscription : subs) {
            if(((DefaultComboBoxModel)subscriptionList.getModel()).getIndexOf(subscription.getType()) < 0) {
                subscriptionList.addItem(subscription.getType());
                //updatedWarehousesList.addItem(subscription.getType());
            }
        }

        packageNameTxt=new JTextField(10);
        packageDestinationTxt=new JTextField(10);
        packageSourceTxt=new JTextField(10);
        packageSizeTxt=new JTextField(10);
        packageUrgencyTxt=new JTextField(10);
        moneyTxt=new JTextField(10);
        JLabel lblNewLabel = new JLabel("Information about the package to request");
        JPanel panelButoane = new JPanel(new GridBagLayout());
        JPanel panou = new JPanel(new GridLayout(1, 2));
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panel0 = new JPanel(new FlowLayout());
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

        panel1.add(lblNewLabel);
        panel1.add(btnLogout);
        panel0.add(pane1);
        panel2.add(lblNamePackage);
        panel2.add(packageNameTxt);
        panel3.add(lblDestinationPackage);
        panel3.add(packageDestinationTxt);
        panel4.add(lblSourcePackage);
        panel4.add(packageSourceTxt);
        panel5.add(lblSizePackage);
        panel5.add(packageSizeTxt);
        panel6.add(lblUrgencyPackage);
        panel6.add(packageUrgencyTxt);
        panel7.add(lblSubscription);
        panel7.add(subscriptionList);
        panel8.add(btnRequest);
        panel9.add(btnChangeSubs);
        panel10.add(btnPayExtra);
        panel11.add(lblMoney);
        panel11.add(moneyTxt);
        panel11.add(btnAddMoney);
        JPanel panelAdd = new JPanel(new GridLayout(12, 1));
        panelAdd.add(panel0);
        panelAdd.add(panel1);
        panelAdd.add(panel2);
        panelAdd.add(panel3);
        panelAdd.add(panel4);
        panelAdd.add(panel5);
        panelAdd.add(panel6);
        panelAdd.add(panel7);
        panelAdd.add(panel8);
        panelAdd.add(panel9);
        panelAdd.add(panel10);
        panelAdd.add(panel11);
        panel.add(panelAdd, BorderLayout.CENTER);
        panou.add(pane);
        panou.add(panel);
        frame.add(panou);
        frame.setSize(1500,900);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void updateTable() throws IOException, ClassNotFoundException {
        model.setRowCount(0);
        model1.setRowCount(0);
        List<PackageRequest> packs = null;
        row1[0]=clientController.getUser().getMoney()+"";
        model1.addRow(row1);
        try {
            try {
                packs =  clientController.getPacks();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (PackageRequest p : packs) {
            if(p.getClient().getId().equals(user.getId())) {

                row[0] =clientController.getPack(p.getId()).getName();
                row[1] =p.getClient().getUsername();
                row[2] =p.getId();
                row[3] =p.getData();
                model.addRow(row);
            }
        }

    }




    @Override
    public JTextField getPackName() { return packageNameTxt;
    }

    @Override
    public JTextField getPackDestination() { return packageDestinationTxt;
    }

    @Override
    public JTextField getPackSource() { return packageSourceTxt;
    }

    @Override
    public JTextField getPackSize() { return packageSizeTxt;
    }

    @Override
    public JTextField getPackUrgency() { return packageUrgencyTxt;
    }

    @Override
    public JTextField getMoneyTxt() {
        return moneyTxt;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public String getSubscriptionList() {
        return  (String) subscriptionList.getSelectedItem();
    }
}
