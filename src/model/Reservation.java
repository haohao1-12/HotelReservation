package model;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public final Date getCheckInDate() {
        return checkInDate;
    }

    public final Date getCheckOutDate() {
        return checkOutDate;
    }

    public final Customer getCustomer() {
        return customer;
    }

    public final IRoom getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reservation reservation = (Reservation) o;
        return Objects.equals(getCheckInDate(), reservation.getCheckInDate()) &&
                Objects.equals(getCheckOutDate(), reservation.getCheckOutDate()) &&
                Objects.equals(getCustomer(), reservation.getCustomer()) &&
                Objects.equals(getRoom(), reservation.getRoom());
    }

    @Override
    public String toString(){
        return customer.getName() + "\n" + "Room: " + room.getRoomNumber() + " - " + room.getRoomType()
                + "\n" + "Price: " + "$" + room.getRoomPrice() + " price per night" + "\n" +
                "Checkin Date: " + checkInDate + "\n" + "Checkout Date: " + checkOutDate;
    }
}
