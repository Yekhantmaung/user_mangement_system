import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class UserMangement {
    static ArrayList<User> users = new ArrayList<>();
    static int userIdCounter = 1;
    static final String FILE_NAME = "user.json";
    static Gson gson = new Gson();
    public static void main(String []args){
        loadFromFile();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("1. Create User");
            System.out.println("2. View Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("0. Exit");
            System.out.print("Enter your choice : ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    createUser(scanner);
                    break;
                case 2:
                    viewUsers();
                    break;
                case 3:
                    updateUser(scanner);
                    break;
                case 4:
                    deleteUser(scanner);
                    break;
                case 0:
                    SaveToFile();
                    System.out.println("Exit Prgram.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (option != 0);
        scanner.close();
    }
    private static void loadFromFile () {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            users = gson.fromJson(reader, new TypeToken<ArrayList<User>>(){}.getType());
            if (users == null){
                users = new ArrayList<>();
            }else {
                for (User u : users){
                    if (u.id >= userIdCounter){
                        userIdCounter = u.id + 1;
                    }
                }
            }
        }catch (IOException e) {
            users = new ArrayList<>();
        }
    }
    private static void SaveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Error saving to file: ");
        }
    }
    private static void createUser(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter address  : ");
        String address = scanner.nextLine();
        User user = new User(userIdCounter++, name, email, address);
        users.add(user);
        SaveToFile();
        System.out.println("User added successfully.");
    }
    private static void viewUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
    private static void updateUser(Scanner scanner) {
        System.out.print("Enter user ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        User userToUpdate = null;
        for (User user : users) {
            if (user.id == id) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) user.name = newName;
                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();
                if (!newEmail.isEmpty()) user.email = newEmail;
                System.out.print("Enter new address: ");
                String newAddress = scanner.nextLine();
                if (!newAddress.isEmpty()) user.address = newAddress;
                SaveToFile();
                System.out.println("User updated successfully.");
                return;
            }else{
                System.out.println("User id "+ id + " not found.");
            }
        }
    }
    private static void deleteUser(Scanner scanner) {
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (userIdCounter == id ) {
            for (User user : users){
                users.remove(user);
                SaveToFile();
                System.out.println("User deleted successfully.");
                return;
            }
        }else{
            System.out.println("User id "+ id + " not found.");
        }
    }
}
