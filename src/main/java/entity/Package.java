package entity;


import javax.persistence.*;
import java.io.Serializable;

import java.util.UUID;

@Entity
@Table(name = "package")
public class Package implements Serializable {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String source;
    @Column
    private String destination;
    @Column
    private int size;
    @Column
    private boolean urgency;
    @Column
    private int status;

    @Column
    private String idUser;

    @ManyToOne
    @JoinColumn(name ="trasportCarId")
    private TransportCar transpCar;





    public Package( int status,String s, String name, String d, int sz, boolean u, TransportCar tp, String idUser){
        this.id= UUID.randomUUID().toString();
        this.status=status;
        this.source=s;
        this.destination=d;
        this.transpCar=tp;
        this.size=sz;
        this.urgency=u;
        this.name=name;
        this.idUser=idUser;
    }

    public Package(){ }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setUrgency(boolean urgency) {
        this.urgency = urgency;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getSize() {
        return size;
    }

    public boolean isUrgency() {
        return urgency;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransportCar getTranspCar() {
        return transpCar;
    }

    public void setTranspCar(TransportCar transpCar) {
        this.transpCar = transpCar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
