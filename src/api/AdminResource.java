package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AdminResource {
    private static AdminResource adminResource;
    private AdminResource() {}
    public static AdminResource getInstance() {
        if (adminResource == null) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public void addRoom (IRoom room) {
        ReservationService reservationService = ReservationService.getInstance();

        reservationService.addRoom(room);

    }

    public Collection<IRoom> getAllRooms() {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        CustomerService customerService = CustomerService.getInstance();
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        ReservationService reservationService = ReservationService.getInstance();
        reservationService.printAllReservation();
    }

}
