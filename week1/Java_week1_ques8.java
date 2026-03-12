import java.util.*;

class ParkingSpot {
    String licensePlate;
    long entryTime;
    String status;

    ParkingSpot() {
        status = "EMPTY";
    }
}

public class Java_week1_ques8 {

    private ParkingSpot[] table;
    private int size = 500;
    private int totalProbes = 0;
    private int vehiclesParked = 0;

    public Java_week1_ques8() {
        table = new ParkingSpot[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ParkingSpot();
        }
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    public void parkVehicle(String plate) {

        int index = hash(plate);
        int probes = 0;

        while (!table[index].status.equals("EMPTY")) {
            index = (index + 1) % size;
            probes++;
        }

        table[index].licensePlate = plate;
        table[index].entryTime = System.currentTimeMillis();
        table[index].status = "OCCUPIED";

        totalProbes += probes;
        vehiclesParked++;

        System.out.println("Assigned spot #" + index + " (" + probes + " probes)");
    }

    public void exitVehicle(String plate) {

        int index = hash(plate);

        while (!table[index].status.equals("EMPTY")) {

            if (plate.equals(table[index].licensePlate)) {

                long duration = System.currentTimeMillis() - table[index].entryTime;
                double hours = duration / 3600000.0;
                double fee = hours * 5;

                table[index].status = "EMPTY";
                table[index].licensePlate = null;

                System.out.println("Spot #" + index + " freed");
                System.out.println("Duration: " + hours + " hours");
                System.out.println("Fee: $" + fee);
                return;
            }

            index = (index + 1) % size;
        }

        System.out.println("Vehicle not found");
    }

    public void getStatistics() {

        int occupied = 0;

        for (ParkingSpot s : table) {
            if (s.status.equals("OCCUPIED")) {
                occupied++;
            }
        }

        double occupancy = (occupied * 100.0) / size;
        double avgProbes = vehiclesParked == 0 ? 0 : (double) totalProbes / vehiclesParked;

        System.out.println("Occupancy: " + occupancy + "%");
        System.out.println("Avg Probes: " + avgProbes);
    }

    public static void main(String[] args) {

        Java_week1_ques8 parking = new Java_week1_ques8();

        parking.parkVehicle("ABC1234");
        parking.parkVehicle("ABC1235");
        parking.parkVehicle("XYZ9999");

        parking.exitVehicle("ABC1234");

        parking.getStatistics();
    }
}
