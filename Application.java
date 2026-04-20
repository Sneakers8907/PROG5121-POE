/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.application;

/**
 *
 * @author mohoje
 */

import java.util.Scanner;
import java.util.ArrayList;

// this class stores user detials (like a blueprit)
class User{
    
    String name;
    String surname;       
    String username;
    String password;
    String cellnumber;   

    // contructor to intialize user object
    User(String name ,String surname  ,String username ,String password ,String cellnumber){
       
      this.name = name; // assign name
      this.surname = surname; // assign surname
      this.username = username; // assign username
      this.cellnumber = cellnumber; // assign phone
      this.password = password; // assign password
   } 
}

// class for login functinality
class Login {
    private ArrayList<User> users; // list to store users.
    
    // contructor recieves user list
    Login(ArrayList<User> usersList){
       this.users = usersList;
    }

    // method to check if login detials are corect
    public boolean authenticate(String username , String password ){
        
        // loop through all users
        for(User user: users){
           // check if username and password match
           if(user.username.equals(username)&& user.password.equals(password)){
              System.out.println("Welcome " + user.name + " " + user.surname + " Your login was successful...");
              return true; // login succes
           }
        }  
        System.out.println("Invalid...Enter correct credentials"); // login fail
        return false;  
    }
} 

public class Application {
    
     
    private static ArrayList<User> users = new ArrayList<>();   // list to hold users
    private static Scanner scanner = new Scanner(System.in); // scanner for input
    private static Login login = new Login(users); // login object
          
    public static void main(String[] args) {
       
       while (true){ // loop forever untill exit
          
          System.out.println("1.Register"); 
          System.out.println("2.Signin"); 
          System.out.println("3.Leave");
          System.out.print("Select your path: ");
          
          int choice = scanner.nextInt(); // get user choice
          
          scanner.nextLine(); // clear buffer
          
          if(choice ==1){
              register(); // call register
          }
          else if(choice == 2){
              Login(); // call login method
          }
          else if (choice ==3){
              System.out.println("Exiting program.."); // exit program
              return;
          }
          else{
              System.out.println("Invalid choice...Try again"); // wrong option
          }
       }
    }

    // method to register new user
    private static void register(){
        System.out.println("REGISTER YOUR ACCOUNT");
        
        System.out.print("Enter your Name/names: ");
        String name = scanner.nextLine(); // read name
        
        System.out.print("Enter surname :");
        String surname = scanner.nextLine(); // read surname
        
        String username;
        while (true){
           System.out.println("Username must have an underscore");
           System.out.println("Username must have maximum of 5 charecters");      
           System.out.println("Enter username");
           username = scanner.nextLine();
           
            // check username rules
            if (username.contains("_")&& username.length()<=5) {
                break;
               
            }
            else{
                System.out.print("Enter correct Username: "); // invalid username
            }
            
        }
        
        String password;
        while(true){
            System.out.println("Password must include min of 8 Charecter");
            System.out.println("Password must include number");
            System.out.println("Password must include special charecter");
           
            System.out.print("Enter your Password: ");
            password = scanner.nextLine();
            
            // check password rules
            if(password.length()>=8&&password.matches(".*[0-9].*")&&password.matches(".*[!@#$%^&*].*")){
                break;
            }
            else{
                System.out.println("Password is invalid...try again."); // wrong password
            }
        }  
     
        String cellPhone; 
        while(true){
            System.out.println("Cell phone number should have a SA code,(+27)..");
            
            System.out.println("Enter cell number...:");
            cellPhone = scanner.nextLine();
            
            // check SA number format
            if(cellPhone.startsWith("+27")){
                break;
            }
            else {
                System.out.println("Invalid cell number...PLEASE ENTER A VALID NUMBER"); 
            }   
        }

        // add new user to list
        users.add(new User(name, surname, username, password, cellPhone));
    }

    // login method for user
    private static void Login(){ 
        System.out.println("-----L-O-G-I-N-----");
      
        System.out.print("Enter your Name :");
        String name = scanner.nextLine();
        
        System.out.print("Enter Surname :");
        String surname = scanner.nextLine();
        
        System.out.print("Enter Username :");
        String username = scanner.nextLine(); 
        
        System.out.print("Enter Cellnumber :");
        String cellPhone = scanner.nextLine(); 
         
        System.out.print("Enter your unique Password :");
        String password = scanner.nextLine(); // password input

        //only print welcome back if login is true
        if(login.authenticate(username, password)){
            System.out.println("-WELCOME BACK.....To STAR-WORKS-");
        }
    }   

    // method to validate password 
    private static boolean isValidPassword(String password){
        
        boolean hasNumber = password.matches(".*\\d.*"); // check number
        boolean hasSpecial = password.matches(".*[!@#$%^&*()].*"); // check special
        return password.length() >=8 && hasNumber && hasSpecial;
    }
}
