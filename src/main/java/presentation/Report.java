package presentation;

import start.Client;

import java.io.Serializable;

public abstract class Report implements Serializable {

    public abstract void generateReport(Client.Connection con);

}
