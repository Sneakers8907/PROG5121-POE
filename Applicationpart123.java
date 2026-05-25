/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.applicationpart123;

/**
 *
 * @author mohoje
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

// this class is basically where we keep all the user details
class User {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String cellnumber;

    // constructor to create a new user object
    User(String name,
         String surname,
         String username,
         String password,
         String cellnumber) {

        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.cellnumber = cellnumber;
    }

    // getters because private variables cannot be accessed directly
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCellnumber() {
        return cellnumber;
    }
}

// this class handles the login process
class Login {

    private ArrayList<User> users;

    // constructor
    Login(ArrayList<User> usersList) {

        this.users = usersList;
    }

    // checks if user login details match
    public boolean authenticate(String name,
                                String surname,
                                String username,
                                String password,
                                String cellnumber) {

        for (User user : users) {

            if (user.getName().equals(name)
                    && user.getSurname().equals(surname)
                    && user.getUsername().equals(username)
                    && user.getPassword().equals(password)
                    && user.getCellnumber().equals(cellnumber)) {

                System.out.println("\n");
                System.out.println("WELCOME TO STAR WORKS "
                        + user.getName() + " "
                        + user.getSurname());
                System.out.println("LOGIN SUCCESSFUL");

                return true;
            }
        }

        System.out.println("INVALID LOGIN DETAILS.");
        return false;
    }
}

// this class handles everything related to messages
class Message {

    String messageID;
    int messageNumber;
    String recipient;
    String message;
    String messageHash;

    // arrays for storing messages while app is running
    static ArrayList<Message> sentMessages =
            new ArrayList<>();

    static ArrayList<Message> storedMessages =
            new ArrayList<>();

    // constructor
    Message(String messageID,
            int messageNumber,
            String recipient,
            String message) {

        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;

        // hash gets created automatically
        this.messageHash = createMessageHash();
    }

    // checks if id is exactly 10 characters
    public boolean checkMessageID() {

        return messageID.length() == 10;
    }

    // checks recipient number format
    public String checkRecipientCell() {

        if (recipient.matches("^\\+27\\d{9}$")) {

            return "CELL NUMBER CAPTURED SUCCESSFULLY.";
        }

        else {

            return "CELL NUMBER INCORRECTLY FORMATTED.";
        }
    }

    // creates message hash
    public String createMessageHash() {

        if (message.trim().isEmpty()) {

            return "EMPTY";
        }

        String[] words = message.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String hash =
                messageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + firstWord + lastWord;

        return hash.toUpperCase();
    }

    // method for sending, storing or disregarding messages
    public String sentMessage(int choice) {

        // save sent message
        if (choice == 1) {

            sentMessages.add(this);

            return "MESSAGE SUCCESSFULLY SENT.";
        }

        // ignore message
        else if (choice == 2) {

            return "MESSAGE DISREGARDED.";
        }

        // store message for later
        else if (choice == 3) {

            storedMessages.add(this);

            return "MESSAGE SUCCESSFULLY STORED.";
        }

        return "INVALID OPTION.";
    }

    // displays messages
    public static void printMessages() {

        if (sentMessages.isEmpty()) {

            System.out.println("COMING SOON......");
            return;
        }

        System.out.println("         MESSAGE LIST");

        for (Message msg : sentMessages) {

            System.out.println("MESSAGE NUMBER : "
                    + msg.messageNumber);

            System.out.println("MESSAGE ID     : "
                    + msg.messageID);

            System.out.println("MESSAGE HASH   : "
                    + msg.messageHash);

            System.out.println("RECIPIENT      : "
                    + msg.recipient);

            System.out.println("MESSAGE        : "
                    + msg.message);

        }
    }

    // returns total sent messages
    public static int returnTotalMessages() {

        return sentMessages.size();
    }
}

// main class
public class Applicationpart123 {

    // array list for users
    private static ArrayList<User> users =
            new ArrayList<>();

    // scanner object
    private static Scanner scanner =
            new Scanner(System.in);

    // login object
    private static Login loginObject =
            new Login(users);

    // main method
    public static void main(String[] args) {

        while (true) {

            System.out.println("        STAR-WORKS-CHAT");

            System.out.println("1. REGISTER");
            System.out.println("2. SIGN IN");
            System.out.println("3. EXIT");

            System.out.print("\nCHOOSE OPTION: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            // registration section
            if (choice == 1) {

                register();
            }

            // login section
            else if (choice == 2) {

                login();
            }

            // exit app
            else if (choice == 3) {

                System.out.println("CLOSING APPLICATION...");
                break;
            }

            else {

                System.out.println("INVALID OPTION.");
            }
        }
    }

    // registration method
    private static void register() {
        System.out.println("            REGISTER");

        System.out.print("ENTER NAME: ");
        String name = scanner.nextLine();

        System.out.print("ENTER SURNAME: ");
        String surname = scanner.nextLine();

        String username;

        while (true) {

            System.out.println("\nUSERNAME REQUIREMENTS:");
            System.out.println("- MUST CONTAIN UNDERSCORE");
            System.out.println("- MAXIMUM 5 CHARACTERS");

            System.out.print("ENTER USERNAME: ");

            username = scanner.nextLine();

            // username validation
            if (username.contains("_")
                    && username.length() <= 5) {

                break;
            }

            else {

                System.out.println("INVALID USERNAME.");
            }
        }

        String password;

        while (true) {

            System.out.println("\nPASSWORD REQUIREMENTS:");
            System.out.println("- AT LEAST 8 CHARACTERS");
            System.out.println("- MUST CONTAIN NUMBER");
            System.out.println("- MUST CONTAIN SPECIAL CHARACTER");

            System.out.print("ENTER PASSWORD: ");

            password = scanner.nextLine();

            // password validation
            if (isValidPassword(password)) {

                break;
            }

            else {

                System.out.println("INVALID PASSWORD.");
            }
        }

        String cellPhone;

        while (true) {

            System.out.println("\nCELL NUMBER REQUIREMENTS:");
            System.out.println("- MUST START WITH +27");

            System.out.print("ENTER CELL NUMBER: ");

            cellPhone = scanner.nextLine();

            // cell number validation
            if (cellPhone.matches("^\\+27\\d{9}$")) {

                break;
            }

            else {

                System.out.println("INVALID CELL NUMBER.");
            }
        }

        // add user to array
        users.add(new User(name,
                surname,
                username,
                password,
                cellPhone));

        System.out.println("\nREGISTRATION SUCCESSFUL.");
    }

    // login method
    private static void login() {

        System.out.println("\n");
        System.out.println("              LOGIN");

        System.out.print("ENTER NAME: ");
        String name = scanner.nextLine();

        System.out.print("ENTER SURNAME: ");
        String surname = scanner.nextLine();

        System.out.print("ENTER USERNAME: ");
        String username = scanner.nextLine();

        System.out.print("ENTER CELL NUMBER: ");
        String cellnumber = scanner.nextLine();

        System.out.print("ENTER PASSWORD: ");
        String password = scanner.nextLine();

        // authenticate login
        if (loginObject.authenticate(name,
                surname,
                username,
                password,
                cellnumber)) {

            System.out.print("\nHOW MANY MESSAGES DO YOU WANT TO SEND: ");

            int totalMessages = scanner.nextInt();
            scanner.nextLine();

            int messageCount = 1;

            while (messageCount <= totalMessages) {

                System.out.println("\n");
                System.out.println("          MESSAGE MENU");

                System.out.println("1. SEND MESSAGE");
                System.out.println("2. SHOW MESSAGES");
                System.out.println("3. STORE MESSAGE");
                System.out.println("4. DISREGARD MESSAGE");
                System.out.println("5. QUIT");

                System.out.print("\nCHOOSE OPTION: ");

                int option = scanner.nextInt();
                scanner.nextLine();

                // send message
                if (option == 1) {

                    Random random = new Random();

                    String messageID =
                            String.format("%010d",
                            Math.abs(random.nextLong())
                            % 10000000000L);

                    System.out.print("ENTER RECIPIENT NUMBER: ");
                    String recipient = scanner.nextLine();

                    System.out.print("ENTER MESSAGE: ");
                    String messageText = scanner.nextLine();

                    // make sure message is not too long
                    if (messageText.length() > 250) {

                        System.out.println("MESSAGE EXCEEDS 250 CHARACTERS.");
                        continue;
                    }

                    // create message object
                    Message msg = new Message(
                            messageID,
                            messageCount,
                            recipient,
                            messageText);

                    // validate message id
                    if (msg.checkMessageID()) {

                        System.out.println("MESSAGE ID CAPTURED SUCCESSFULLY.");
                    }

                    else {

                        System.out.println("INVALID MESSAGE ID.");
                    }

                    // validate recipient number
                    System.out.println(
                            msg.checkRecipientCell());

                    // display message details
                    System.out.println("\n");
                    System.out.println("         MESSAGE DETAILS");

                    System.out.println("MESSAGE ID   : "
                            + msg.messageID);

                    System.out.println("MESSAGE HASH : "
                            + msg.messageHash);

                    System.out.println("RECIPIENT    : "
                            + msg.recipient);

                    System.out.println("MESSAGE      : "
                            + msg.message);

                    // save message into array
                    Message.sentMessages.add(msg);

                    System.out.println("MESSAGE SUCCESSFULLY SENT.");

                    messageCount++;
                }

                // show messages
                else if (option == 2) {

                    System.out.println("COMING SOON......");
                }

                // store message
                else if (option == 3) {

                    System.out.println("MESSAGE STORED FOR LATER.");
                }

                // disregard message
                else if (option == 4) {

                    System.out.println("MESSAGE DISREGARDED.");
                }

                // quit
                else if (option == 5) {

                    System.out.println("GOODBYE.");
                    break;
                }

                else {

                    System.out.println("INVALID OPTION.");
                }
            }

            // display total messages sent
            System.out.println("\n");
            System.out.println("TOTAL MESSAGES SENT : "
                    + Message.returnTotalMessages());
        }
    }

    // password validation
    private static boolean isValidPassword(String password) {

        boolean hasNumber =
                password.matches(".*\\d.*");

        boolean hasSpecial =
                password.matches(".*[!@#$%^&*()].*");

        return password.length() >= 8
                && hasNumber
                && hasSpecial;
    }
}


