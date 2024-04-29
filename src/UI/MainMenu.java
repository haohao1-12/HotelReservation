package UI;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    /*
    1. Find and reserve a room
    2. See my reservations
    3. Create an account
    4. Admin (open the admin menu described below)
    5. Exit (exit the application)
     */
    public static void displayMainMenu() {
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {

            while (keepRunning) {
                try{
                    System.out.println("Welcome to the Hotel Reservation Application");
                    System.out.println("\n----------------------------------------------------");
                    System.out.println("1. Find and reserve a room");
                    System.out.println("2. See my reservations");
                    System.out.println("3. Create an account");
                    System.out.println("4. Admin");
                    System.out.println("5. Exit");
                    System.out.println("----------------------------------------------------");
                    System.out.println("Please select a number for the menu option");
                    int selection = Integer.parseInt(scanner.nextLine());

                    switch (selection) {
                        case 1:
                            System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
                            String dateString = scanner.nextLine();
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            Date checkInDate = format.parse(dateString);

                            System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2020");
                            dateString = scanner.nextLine();
                            Date checkOutDate = format.parse(dateString);

                            HotelResource hotelResource = HotelResource.getInstance();
                            try{
                                System.out.println("Available rooms are shown below: ");
                                for (IRoom room : hotelResource.findARoom(checkInDate, checkOutDate)) {
                                    System.out.println(room.toString());
                                }
                            } catch (Exception ex) {
                                System.out.println("No room is available within the given range, we suggest putting off the dates");
                                System.out.println("We suggest a seven postpone of the original dates");
                                System.out.println("How many days would you like to put off?");
                                int days = Integer.parseInt(scanner.nextLine());
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(checkInDate);
                                // Add 7 days
                                calendar.add(Calendar.DAY_OF_MONTH, days);
                                checkInDate = calendar.getTime();
                                calendar.setTime(checkOutDate);
                                calendar.add(Calendar.DAY_OF_MONTH,days);
                                checkOutDate = calendar.getTime();
                                for (IRoom room : hotelResource.findARoom(checkInDate, checkOutDate)) {
                                    System.out.println(room.toString());
                                }
                            }
                            System.out.println("Would you like to reserve a room? y/n");
                            String choice1 = scanner.nextLine();
                            if (choice1.equals("y")) {
                                System.out.println("Do you have an account with us? y/n");
                                String choice2 = scanner.nextLine();
                                if (choice2.equals("y")) {
                                    System.out.println("Enter Email format: name@domain.com");
                                    String email = scanner.nextLine();
                                    System.out.println("What room number would you like to reserve?");
                                    String roomNumber = scanner.nextLine();

                                    hotelResource.bookARoom(email, hotelResource.getRoom(roomNumber), checkInDate, checkOutDate).toString();
                                }
                            }

                            break;

                        case 2:
                            System.out.println("Please enter the Email: name@domain.com");
                            String email = scanner.nextLine();
                            hotelResource = HotelResource.getInstance();
                            for (Reservation reservation : hotelResource.getCustomersReservations(email)) {
                                System.out.println(reservation.toString() + "\n");
                            }
                            break;

                        case 3:
                            System.out.println("Enter Email format: name@domain.com");
                            email = scanner.nextLine();
                            System.out.println("First Name");
                            String firstName = scanner.nextLine();
                            System.out.println("Last Name");
                            String lastName = scanner.nextLine();
                            hotelResource = HotelResource.getInstance();
                            hotelResource.createACustomer(email, firstName, lastName);
                            break;

                        case 4:
                            AdminMenu.displayAdminMenu(scanner);
                            break;

                        case 5:
                            keepRunning = false;
                            break;

                    }

                } catch (Exception ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
            }
        }
    }

}
