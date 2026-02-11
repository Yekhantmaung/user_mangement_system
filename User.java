public class User {
    int id;
    String name;
    int age;
    String email;
    String address;

    public User (int id, String name , int age , String email , String address){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }
    @Override
    public String toString () {
        return "ID "+id+". Name : "+ name + " , Age : "+ age + " , Email : "+ email + " , Address : "+ address;
    }
}
