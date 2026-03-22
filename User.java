public class User {
    int id;
    String name;
    String email;
    String address;

    public User (int id , String name , String email , String address){
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }
    @Override
    public String toString () {
        return "User ID : "+ id + " , Name : "+ name + " , Email : "+ email + " , Address : "+ address;
    }
}
