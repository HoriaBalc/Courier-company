package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "packageRequest")
public class PackageRequest implements Serializable {
    @Id
    private String id;




    @JoinColumn(  name = "packageRequest")
    @ManyToOne
    private User client;

    @Column
    private Date data;

    public PackageRequest(User c,String idPack){
        this.id = idPack;
        this.client=c;
        this.data=new Date(System.currentTimeMillis());

    }
    public PackageRequest(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setClient(User client) {
        this.client = client;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }


    public User getClient() {
        return client;
    }
}
