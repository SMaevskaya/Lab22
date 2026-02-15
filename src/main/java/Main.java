import dao.UserDao;
import models.User;
import utils.HibernateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final UserDao userDAO = new UserDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            boolean exit = false;

            while (!exit) {
                System.out.println("\n=== CRUD USER ===");
                System.out.println("1. Create new user");
                System.out.println("2. Show all users");
                System.out.println("3. Find user by ID");
                System.out.println("4. Update user");
                System.out.println("5. Delete user");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // очистка буфера

                switch (choice) {
                    case 1:
                        createUser();
                        break;
                    case 2:
                        showAllUsers();
                        break;
                    case 3:
                        findUserById();
                        break;
                    case 4:
                        updateUser();
                        break;
                    case 5:
                        deleteUser();
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } finally {
            // Закрываем ресурсы
            scanner.close();
            HibernateUtil.shutdown();
        }
    }

    private static void createUser() {
        System.out.println("\n--- Create New User ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter age: ");
        int age =Integer.parseInt(scanner.nextLine());

        System.out.print("Enter created_at: ");
        String created_at_Str = scanner.nextLine();
        Date created_at;
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        try {
             created_at = formatter.parse(created_at_Str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        User user = new User(name, email, age, new java.sql.Date(created_at.getTime()) );
        Integer userId = userDAO.saveUser(user);

        if (userId != null) {
            System.out.println("User created successfully with ID: " + userId);
        } else {
            System.out.println("Failed to create user.");
        }
    }

    private static void showAllUsers() {
        System.out.println("\n--- All Users ---");
        List<User> users = userDAO.getAllUsers();

        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("No users found.");
        }
    }

    private static void findUserById() {
        System.out.println("\n--- Find User by ID ---");
        System.out.print("Enter user ID: ");
        Long userId = scanner.nextLong();
        scanner.nextLine(); // очистка буфера

        User user = userDAO.getUserById(userId);
        if (user != null) {
            System.out.println("User found: " + user);
        } else {
            System.out.println("User not found with ID: " + userId);
        }
    }

    private static void updateUser() {
        System.out.println("\n--- Update User ---");
        System.out.print("Enter user ID to update: ");
        Long userId = scanner.nextLong();
        scanner.nextLine(); // очистка буфера

        User user = userDAO.getUserById(userId);
        if (user == null) {
            System.out.println("User not found with ID: " + userId);
            return;
        }

        System.out.println("Current user details: " + user);

        System.out.print("Enter new username (or press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            user.setName(name);
        }

        System.out.print("Enter new email (or press Enter to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            user.setEmail(email);
        }

        System.out.print("Enter new age (or press Enter to keep current): ");
        int age = Integer.parseInt(scanner.nextLine());
        if (age>0) {
            user.setAge(age);
        }

        boolean success = userDAO.updateUser(user);
        if (success) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Failed to update user.");
        }
    }

    private static void deleteUser() {
        System.out.println("\n--- Delete User ---");
        System.out.print("Enter user ID to delete: ");
        Long userId = scanner.nextLong();
        scanner.nextLine(); // очистка буфера

        boolean success = userDAO.deleteUserById(userId);
        if (success) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }

}

