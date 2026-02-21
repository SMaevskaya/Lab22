package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name="userr")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column (name="name")
    private String name;

    @Column (name="email")
    private String email;

    @Column (name="age")
    private int age;

    @Column (name="created_at")
    private Date created_at;

    public User(){

    }

    public User (String name, String email, int age, Date created_at){
        this.name=name;
        this.email=email;
        this.age=age;
        this.created_at=created_at;
    }
     public  String getName(){
        return name;
     }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }


  public String toString(){
        return "Id="+id+ " Name="+name+" Email="+email+" Age="+age+" created_at="+created_at;
  }
}

