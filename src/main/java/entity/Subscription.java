package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "subscription")
public class Subscription implements Serializable {

    @Id
    private String id;
    @Column
    private String type;
    @Column
    private int nrPosts;
    @Column
    private int pret;

    @OneToMany(mappedBy="subscription")
    private List<User> users=new ArrayList<>();


    public Subscription( String type, int nrPosts, int pret) {
        this.id= UUID.randomUUID().toString();
        this.type = type;
        this.nrPosts = nrPosts;
        this.pret = pret;
    }

    public Subscription() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNrPosts() {
        return nrPosts;
    }

    public void setNrPosts(int nrPosts) {
        this.nrPosts = nrPosts;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
