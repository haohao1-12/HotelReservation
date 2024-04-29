package UI;

import api.AdminResource;
import model.*;

import java.util.Scanner;

public class AdminMenu {

    public static void displayAdminMenu(Scanner scanner) {
        boolean keep = true;
        while(keep) {
            try {
                System.out.println("Admin Menu");
                System.out.println("\n----------------------------------------------------");
                System.out.println("1. See all Customers");
                System.out.println("2. See all Rooms");
                System.out.println("3. See all Reservations");
                System.out.println("4. Add a room");
                System.out.println("5. Back to Main Menu");
                System.out.println("----------------------------------------------------");
                System.out.println("Please select a number for the menu option");

                int selection = Integer.parseInt(scanner.nextLine());

                switch (selection) {
                    case 1:
                        AdminResource adminResource = AdminResource.getInstance();
                        for (Customer customer : adminResource.getAllCustomers()) {
                            System.out.println(customer.toString());
                        }
                        break;
                    case 2:
                        adminResource = AdminResource.getInstance();
                        for (IRoom room : adminResource.getAllRooms()) {
                            System.out.println(room.toString());
                        }
                        break;
                    case 3:
                        adminResource = AdminResource.getInstance();
                        adminResource.displayAllReservations();
                        break;
                    case 4:
                        System.out.println("Enter room number");
                        String roomNumber = scanner.nextLine();
                        System.out.println("Enter price per night");
                        Double price = Double.parseDouble(scanner.nextLine());
                        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                        int num = Integer.parseInt(scanner.nextLine());
                        RoomType roomType = RoomType.SINGLE;
                        if (num == 1) {
                            roomType = RoomType.SINGLE;
                        } else if (num == 2) {
                            roomType = RoomType.DOUBLE;
                        }

                        IRoom newRoom = new Room(roomNumber, price, roomType);
                        adminResource = AdminResource.getInstance();
                        adminResource.addRoom(newRoom);
                        break;
                    case 5:
                        keep = false;
                        break;
                }



            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }

}
