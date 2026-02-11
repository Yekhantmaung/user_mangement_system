import java.util.ArrayList;
import java.util.Scanner;


public class UserMangement {
    static ArrayList<User> users = new ArrayList<>();
    static int userIdCounter = 1;
    public static void main(String []args){
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("1. Creater User.");
            System.out.println("2. Read User.");
            System.out.println("3. Update User By ID.");
            System.out.println("4. Delete User By ID.");
            System.out.println("0. Exit.");
            System.out.print(" Enter your option : ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    createUser(scanner);
                    break;
                case 2:
                    readUser();
                    break;
                case 3:
                    updateUser(scanner);
                    break;
                case 4:
                    deleteUser(scanner);
                    break;
                case 0:
                    System.out.println("Exit Program.");
                    break;
                default:
                    System.out.println("Invalid Option. Please Try Again.");
            }
        }while (option != 0);
        scanner.close();
    }

    private static void createUser(Scanner scanner){
        System.out.print("Enter User Name : ");
        String name = scanner.nextLine();
        System.out.print("Enter User Age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter User Email : ");
        String email = scanner.nextLine();
        System.out.print("Enter User Address : ");
        String address = scanner.nextLine();
        User user = new User(userIdCounter++ , name , age , email , address);
        users.add(user);
        System.out.println("User Created Successfully.");
    }

    private static void readUser(){
        if (users.isEmpty()){
            System.out.println("No Users Found.");
        }else{
            System.out.println("User List");
            for (User user : users){
                System.out.println(user);
            }
        }
    }

    private static void updateUser(Scanner scanner){
        System.out.println("Enter User ID to Update : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        User userToUpdate = null;
        for (User user : users){
            if (user.id == id){
                userToUpdate = user;
                break;
            }
        }
        if (userToUpdate == null){
            System.out.println("User id "+ id + " Not Found.");
        }else{
            System.out.println("Update User Successfully.");
            System.out.print("Enter New User Name  : ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()){
                userToUpdate.name = newName;
            }
            System.out.print("Enter New User Age : ");
            int newAge = scanner.nextInt();
            if (newAge > 0){
                userToUpdate.age = newAge;
            }
            System.out.print("Enter New User Email : ");
            String newEmail = scanner.nextLine();
            if (!newEmail.isEmpty()){
                userToUpdate.email = newEmail;
            }
            System.out.print("Enter New User Address : ");
            String newAddress = scanner.nextLine();
            if (!newAddress.isEmpty()){
                userToUpdate.address = newAddress;
            }
        }
    }

    private static void deleteUser (Scanner scanner){
        System.out.println("Enter User ID to Delete : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        User userToDelete = null;
        for (User user : users){
            if (user.id == id){
                userToDelete = user;
                break;
            }
        }
        if (userToDelete == null){
            System.out.println("User id "+ id + " Not Found.");
        }else{
            users.remove(userToDelete);
            System.out.println("User Deleted SuccessFully.");
        }
    }
}