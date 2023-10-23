package K22UG;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RentalCar {
    private int carId;
    private String carName;
    private String carType;
    private boolean available;

    public RentalCar(int carId, String carName, String carType) {
        this.carId = carId;
        this.carName = carName;
        this.carType = carType;
        this.available = true;`
    }

    public int getCarId() {
        return carId;
    }

    public String getCarName() {	
        return carName;
    }

    public String getCarType() {
        return carType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Booking {
    private int bookingId;
    private int carId;
    private String startDate;
    private String endDate;

    public Booking(int bookingId, int carId, String startDate, String endDate) {
        this.bookingId = bookingId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getCarId() {
        return carId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}

public class RentalCarManagementSystem {
    private Map<Integer, RentalCar> cars;
    private List<Booking> bookings;
    private int nextBookingId;

    public RentalCarManagementSystem() {
        cars = new HashMap<>();
        bookings = new ArrayList<>();
        nextBookingId = 1;
    }

    public void addCar(int carId, String carName, String carType) {
        RentalCar car = new RentalCar(carId, carName, carType);
        cars.put(carId, car);
    }

    public int reserveCar(int carId, String startDate, String endDate) {
        if (cars.containsKey(carId) && cars.get(carId).isAvailable()) {
            Booking booking = new Booking(nextBookingId, carId, startDate, endDate);
            bookings.add(booking);
            cars.get(carId).setAvailable(false);
            nextBookingId++;
            return booking.getBookingId();
        } else {
            return -1; // Indicates that the car is not available or does not exist
        }
    }

    public Booking getBookingDetails(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null; // Indicates that the booking was not found
    }

    public RentalCar getCarDetails(int carId) {
        if (cars.containsKey(carId)) {
            return cars.get(carId);
        } else {
            return null; // Indicates that the car was not found
        }
    }

    public static void main(String[] args) {
        RentalCarManagementSystem rentalSystem = new RentalCarManagementSystem();

        rentalSystem.addCar(1, "Car A", "Compact");
        rentalSystem.addCar(2, "Car B", "SUV");
        rentalSystem.addCar(3, "Car C", "Luxury");

        int bookingId = rentalSystem.reserveCar(1, "2023-10-15", "2023-10-20");
        if (bookingId != -1) {
            System.out.println("Booking successful! Booking ID: " + bookingId);
        } else {
            System.out.println("Car is not available for the specified dates.");
        }

        Booking booking = rentalSystem.getBookingDetails(1);
        if (booking != null) {
            System.out.println("Booking ID: " + booking.getBookingId() + ", Car ID: " + booking.getCarId() +
                    ", Start Date: " + booking.getStartDate() + ", End Date: " + booking.getEndDate());
        } else {
            System.out.println("Booking not found.");
        }

        RentalCar car = rentalSystem.getCarDetails(1);
        if (car != null) {
            System.out.println("Car ID: " + car.getCarId() + ", Car Name: " + car.getCarName() +
                    ", Car Type: " + car.getCarType() + ", Availability: " + car.isAvailable());
        } else {
            System.out.println("Car not found.");
        }
    }
}
