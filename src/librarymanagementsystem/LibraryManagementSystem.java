/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package librarymanagementsystem;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the library system
        System.out.print("Enter the maximum number of books the library can store: ");
        int maxBooks = scanner.nextInt();

        String[] bookTitles = new String[maxBooks];
        String[] bookDescriptions = new String[maxBooks];
        boolean[] bookStatuses = new boolean[maxBooks]; // false = available, true = issued
        int bookCount = 0;

        boolean exit = false; // Initialize the exit variable to avoid potential uninitialized error

        // Main menu loop using do-while structure
        do {
            System.out.println("\n Library Management System - Main Menu:");
            System.out.println("1. Add a Book");
            System.out.println("2. Search for a Book");
            System.out.println("3. Issue a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Delete a Book");
            System.out.println("6. Edit Book Details");
            System.out.println("7. View All Books");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add a Book
                    if (bookCount == maxBooks) {
                        System.out.println("Library is full! Cannot add more books.");
                        break;
                    }
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine().trim();
                    if (title.isEmpty()) {
                        System.out.println("Title cannot be empty!");
                        break;
                    }
                    System.out.print("Enter book description: ");
                    String description = scanner.nextLine().trim();

                    bookTitles[bookCount] = title;
                    bookDescriptions[bookCount] = description;
                    bookStatuses[bookCount] = false; // Book is available
                    bookCount++;

                    System.out.println("Book added successfully!");
                    break;

                case 2: // Search for a Book
                    System.out.println("Search by: 1. Title  2. ID");
                    int searchOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (searchOption == 1) {
                        System.out.print("Enter book title: ");
                        String searchTitle = scanner.nextLine().trim();
                        boolean found = false;
                        for (int i = 0; i < bookCount; i++) {
                            if (bookTitles[i].equalsIgnoreCase(searchTitle)) {
                                System.out.printf("ID: %d, Title: %s, Description: %s, Status: %s%n",
                                        i + 1, bookTitles[i], bookDescriptions[i],
                                        bookStatuses[i] ? "Issued" : "Available");
                                found = true;
                            }
                        }
                        if (!found) System.out.println("Book not found!");
                    } else if (searchOption == 2) {
                        System.out.print("Enter book ID: ");
                        int id = scanner.nextInt();
                        if (id > 0 && id <= bookCount) {
                            System.out.printf("ID: %d, Title: %s, Description: %s, Status: %s%n",
                                    id, bookTitles[id - 1], bookDescriptions[id - 1],
                                    bookStatuses[id - 1] ? "Issued" : "Available");
                        } else {
                            System.out.println("Invalid book ID!");
                        }
                    } else {
                        System.out.println("Invalid option!");
                    }
                    break;

                case 3: // Issue a Book
                    System.out.print("Enter book ID to issue: ");
                    int issueId = scanner.nextInt();
                    if (issueId > 0 && issueId <= bookCount) {
                        if (!bookStatuses[issueId - 1]) {
                            bookStatuses[issueId - 1] = true;
                            System.out.println("Book issued successfully!");
                        } else {
                            System.out.println("Book is already issued!");
                        }
                    } else {
                        System.out.println("Invalid book ID!");
                    }
                    break;

                case 4: // Return a Book
                    System.out.print("Enter book ID to return: ");
                    int returnId = scanner.nextInt();
                    if (returnId > 0 && returnId <= bookCount) {
                        if (bookStatuses[returnId - 1]) {
                            bookStatuses[returnId - 1] = false;
                            System.out.println("Book returned successfully!");
                        } else {
                            System.out.println("Book is not issued!");
                        }
                    } else {
                        System.out.println("Invalid book ID!");
                    }
                    break;

                case 5: // Delete a Book
                    System.out.print("Enter book ID to delete: ");
                    int deleteId = scanner.nextInt();
                    if (deleteId > 0 && deleteId <= bookCount) {
                        for (int i = deleteId - 1; i < bookCount - 1; i++) {
                            bookTitles[i] = bookTitles[i + 1];
                            bookDescriptions[i] = bookDescriptions[i + 1];
                            bookStatuses[i] = bookStatuses[i + 1];
                        }
                        bookTitles[bookCount - 1] = null;
                        bookDescriptions[bookCount - 1] = null;
                        bookStatuses[bookCount - 1] = false;
                        bookCount--;
                        System.out.println("Book deleted successfully!");
                    } else {
                        System.out.println("Invalid book ID!");
                    }
                    break;

                case 6: // Edit Book Details
                    System.out.print("Enter book ID to edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (editId > 0 && editId <= bookCount) {
                        System.out.println("Edit: 1. Title  2. Description");
                        int editOption = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (editOption == 1) {
                            System.out.print("Enter new title: ");
                            bookTitles[editId - 1] = scanner.nextLine().trim();
                        } else if (editOption == 2) {
                            System.out.print("Enter new description: ");
                            bookDescriptions[editId - 1] = scanner.nextLine().trim();
                        } else {
                            System.out.println("Invalid option!");
                        }
                        System.out.println("Book details updated successfully!");
                    } else {
                        System.out.println("Invalid book ID!");
                    }
                    break;

                case 7: // View All Books
                    if (bookCount == 0) {
                        System.out.println("No books in the library!");
                        break;
                    }
                    for (int i = 0; i < bookCount; i++) {
                        System.out.printf("ID: %d, Title: %s, Description: %s, Status: %s%n",
                                i + 1, bookTitles[i], bookDescriptions[i],
                                bookStatuses[i] ? "Issued" : "Available");
                    }
                    break;

                case 8: // Exit
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (!exit); // Loop until the user chooses to exit

        System.out.println("Exiting the system. Goodbye!");
        
    }
}
