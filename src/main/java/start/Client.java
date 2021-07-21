package start;

import view.LoginView;

import java.io.*;
import java.net.Socket;


public class Client
{
    public static class Connection extends Thread implements Serializable
    {
        private transient final Socket socket;
        private transient final ObjectOutputStream output;
        private transient final ObjectInputStream input;

        public Connection(Socket socket) throws IOException
        {
            this.socket = socket;
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        }

        public void sendMessageToServer(String message) throws IOException
        {
            output.writeObject(message);
        }

        public void sendObjectToServer(Object obj) throws IOException
        {
            output.writeObject(obj);
        }

        public Socket getSocket() {
            return socket;
        }

        public ObjectInputStream getInput() {
            return input;
        }
    }

    public static void main(String[] args) throws IOException
    {
        Connection connection = new Connection(new Socket("localhost", 3000));
        connection.start();
        new LoginView(connection);
    }
}
