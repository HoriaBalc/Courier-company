package view;

import controller.LoginController;
import entity.User;
import start.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView extends JFrame implements ILoginView{

    private LoginController loginController;
    public JFrame frame;
    private JTextField username;
    private JPasswordField password;
    private JTextField usernameSignUp;
    private JTextField nameSignUp;
    private JPasswordField passwordSignUp;


    public JButton btnLogin = new JButton("Login");
    public JLabel lblUsername = new JLabel("Username");
    public JLabel lblPassword = new JLabel("Password");
    public JButton btnSignUp = new JButton("SignUp");
    public JLabel lblUsernameSignUp = new JLabel("Username");
    public JLabel lblNameSignUp = new JLabel("Name");
    public JLabel lblPasswordSignUp = new JLabel("Password");
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private Client.Connection con;
    private User u;
    public LoginView(Client.Connection con) {
        this.con=con;
        frame = new JFrame("Login-View");
        loginController=new LoginController(this,con);
        initialize();
        frame.setVisible(true);
        LoginController loginController = new LoginController(this, con);
        btnLogin.addActionListener(e -> {
            try {
                loginController.login();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        btnSignUp.addActionListener(e -> {
            try {
                loginController.signUp();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
    }


    private void initialize() {


        username = new JTextField(10);
        password = new JPasswordField(10);

        usernameSignUp = new JTextField(10);
        nameSignUp = new JTextField(10);
        passwordSignUp = new JPasswordField(10);

        JLabel lblNewLabel = new JLabel("Enter your login credentials");
        JLabel lblNewLabel1 = new JLabel("Create a new account");


        JPanel panel = new JPanel(new GridLayout(4,1));
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new BorderLayout());


        JPanel panell = new JPanel(new GridLayout(5,1));
        JPanel panell1 = new JPanel(new FlowLayout());
        JPanel panell2 = new JPanel(new FlowLayout());
        JPanel panell3 = new JPanel(new FlowLayout());
        JPanel panell4 = new JPanel(new FlowLayout());
        JPanel panell5 = new JPanel(new BorderLayout());




        panel1.add(lblNewLabel);
        panel2.add(lblUsername);
        panel2.add(username);
        panel3.add(lblPassword);
        panel3.add(password);

        panel4.add(btnLogin, BorderLayout.SOUTH);

        panell1.add(lblNewLabel1);
        panell2.add(lblUsernameSignUp);
        panell2.add(usernameSignUp);
        panell3.add(lblNameSignUp);
        panell3.add(nameSignUp);
        panell4.add(lblPasswordSignUp);
        panell4.add(passwordSignUp);
        panell5.add(btnSignUp, BorderLayout.SOUTH);


        JPanel panelFinal=new JPanel(new GridLayout(1,2));

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);


        panell.add(panell1);
        panell.add(panell2);
        panell.add(panell3);
        panell.add(panell4);
        panell.add(panell5);

        panelFinal.add(panell);
        panelFinal.add(panel);
        frame.add(panelFinal);



        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

    }

    @Override
    public JTextField getUsernameSignUp() {
        return usernameSignUp;
    }

    @Override
    public JTextField getNameSignUp() {
        return nameSignUp;
    }

    @Override
    public JPasswordField getPasswordSignUp() {
        return passwordSignUp;
    }

    @Override
    public JTextField getUsername() {
        return username;
    }

    @Override
    public JPasswordField getPassword() {
        return password;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public void addLoginListener(ActionListener login) {
        btnLogin.addActionListener(login);
    }
    public void addSignUpListener(ActionListener signUp) {
        btnLogin.addActionListener(signUp);
    }

    @Override
    public void showAdminView() {
        new AdministratorView(con);
    }

    @Override
    public void showRegularView(User user) throws IOException, ClassNotFoundException {
        new RegularView(con, user);
    }

    @Override
    public void showClientView(User u) throws IOException {
        ClientView c=new ClientView(con,u);
        con.sendMessageToServer("login");
        con.sendObjectToServer(c);
    }


    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }




}
