package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    private ReservationService() {}

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }
    Set<IRoom> roomSet = new HashSet<>();
    Set<Reservation> reservationSet = new HashSet<>();
    Set<IRoom> recommendSet;
    Set<Reservation> checkReservationSet = new HashSet<>();

    public Set<IRoom> getAllRooms() {
        return roomSet;
    }
    public void addRoom(IRoom room) {
        for (IRoom item : roomSet) {
            if (item.equals(room)) {
                System.out.println("duplicated room");
                return;
            }
        }
        roomSet.add(room);

//        recommendSet.add(room);
//        System.out.println("Adding complete\n" + room.toString()) ;
    }

    public IRoom getARoom(String roomId) { // check more info for a specific room
        for (IRoom room : this.getAllRooms()) {
            if (Objects.equals(room.getRoomNumber(), roomId)) {
//                System.out.println("find***");
                return room;
            }
        }
//        return null;
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (!reservationSet.isEmpty()){
            for (Reservation reservation : reservationSet) {
                if (Objects.equals(room.getRoomNumber(), reservation.getRoom().getRoomNumber()) && (checkInDate.before(reservation.getCheckOutDate()) && reservation.getCheckInDate().before(checkOutDate) )) {
                    return null;
                }
            }
        }

        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationSet.add(reservation);
        return reservation;
    }

    // most difficult one I suppose
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        recommendSet = new HashSet<>(roomSet);
        for (IRoom room : roomSet) {
            for (Reservation reservation : reservationSet) {
                if (Objects.equals(room.getRoomNumber(), reservation.getRoom().getRoomNumber()) && (checkInDate.before(reservation.getCheckOutDate()) && reservation.getCheckInDate().before(checkOutDate) )) {
//                    System.out.println("remove***");
                    recommendSet.remove(room);
                }
            }
        }
        return recommendSet;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        for (Reservation reservation : reservationSet) {
            if (reservation.getCustomer() == customer) {
                checkReservationSet.add(reservation);
            }
        }
        return checkReservationSet;
    }

    public void printAllReservation() {
        for (Reservation reservation : reservationSet) {
            System.out.println(reservation.toString());
        }
    }

}
