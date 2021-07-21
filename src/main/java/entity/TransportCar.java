package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "transportCar")
public class TransportCar implements Serializable {
    @Id
    private String id;
    @Column
    private String nume;
    @Column
    private int size;

    @Column
    private boolean defect;

    @OneToMany(mappedBy="transpCar")
    private List<Package> pack=new ArrayList<Package>();



    public TransportCar( int size, String s, boolean def){
        this.defect=def;
        this.id= UUID.randomUUID().toString();
        this.size=size;
        this.nume=s;
    }

    public TransportCar(){}

    public String getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPack(List<Package> pack) {
        this.pack = pack;
    }

    public int getSize() {
        return size;
    }

    public boolean isDefect() {
        return defect;
    }

    public List<Package> getPack() {
        return pack;
    }


    public void setSize(int size) {
        this.size = size;
    }

    public void setDefect(boolean defect) {
        this.defect = defect;
    }



}
