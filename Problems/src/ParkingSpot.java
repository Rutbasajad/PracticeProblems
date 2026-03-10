class ParkingSpot {

    String plate;
    boolean occupied;

    ParkingSpot(String plate) {
        this.plate = plate;
        this.occupied = true;
    }
}

public class ParkingLot {

    ParkingSpot[] table = new ParkingSpot[500];

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % table.length;
    }

    public int parkVehicle(String plate) {

        int index = hash(plate);

        int probes = 0;

        while (table[index] != null && table[index].occupied) {

            index = (index + 1) % table.length;
            probes++;
        }

        table[index] = new ParkingSpot(plate);

        return index;
    }

    public void exitVehicle(String plate) {

        int index = hash(plate);

        while (table[index] != null) {

            if (table[index].plate.equals(plate)) {
                table[index].occupied = false;
                return;
            }

            index = (index + 1) % table.length;
        }
    }
}
