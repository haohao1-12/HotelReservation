package service;

import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
        ReservationService reservationService = ReservationService.getInstance();
        CustomerService customerService = CustomerService.getInstance();

        // Add a customer
        customerService.addCustomer("11@22.com", "Shenghao","Li");

        // Add rooms
        IRoom room1 = new Room("100", 30.0, RoomType.SINGLE);
        IRoom room2 = new Room("101", 50.0, RoomType.DOUBLE);
        IRoom room3 = new Room("102", 60.0, RoomType.DOUBLE);
        reservationService.addRoom(room1);
        reservationService.addRoom(room2);
        reservationService.addRoom(room3);

        // Reserve a room
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date checkInDate = format.parse("02/02/2020");
        Date checkOutDate = format.parse("02/04/2020");

        reservationService.reserveARoom(customerService.getCustomer("11@22.com"), room1, checkInDate, checkOutDate);

        Date checkIn = format.parse("02/01/2020");
        Date checkOut = format.parse("02/05/2020");

        for (IRoom room : reservationService.getAllRooms()) {
            System.out.println("All the rooms: " + room.toString());
        }

        for (IRoom room : reservationService.findRooms(checkIn, checkOut)) {
            System.out.println("Available rooms: " + room.toString());
        }

        for (Reservation reservation : reservationService.getCustomerReservation(customerService.getCustomer("11@22.com"))) {
            System.out.println(reservation.toString());
        }
    }
}
