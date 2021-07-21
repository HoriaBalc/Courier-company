package entity;



import javax.persistence.*;

import java.io.Serializable;

import java.util.UUID;

@Entity
@Table(name = "user")
public class User  implements Serializable {

    @Id
    private String id;

    @Column
    private String username;

    @Column
    private String name;


    @Column
    private int type;

    @Column
    private String password;

    @Column
    private int money;


    @ManyToOne
    @JoinColumn(name="subscription")
    private Subscription subscription;



    public User( String username, String name, int type, String password, Subscription s) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.password = password;
        this.username = username;
        this.subscription=s;
        this.money=0;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
