package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource;
    private HotelResource() {}
    public static HotelResource getInstance() {
        if (hotelResource == null) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String email) { // Should use CustomerService but how?
        CustomerService customerService = CustomerService.getInstance();
        return customerService.getCustomer(email);
    }

    public void createACustomer (String email, String firstName, String lastName) {
        CustomerService customerService = CustomerService.getInstance();
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom (String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) throws Exception {
        ReservationService reservationService = ReservationService.getInstance();
        Reservation reservation = reservationService.reserveARoom(this.getCustomer(customerEmail), room, checkInDate, checkOutDate);
        if (reservation == null) {
            throw new Exception("Invalid Reservation");
        }
        return reservation;
    }

    public Collection<Reservation> getCustomersReservations (String customerEmail) {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getCustomerReservation(this.getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom (Date checkIn, Date checkOut) throws Exception {
        ReservationService reservationService = ReservationService.getInstance();
        if (reservationService.findRooms(checkIn, checkOut).isEmpty()) {
            throw new Exception("There's no room available");
        }
        return reservationService.findRooms(checkIn, checkOut);
    }

}
