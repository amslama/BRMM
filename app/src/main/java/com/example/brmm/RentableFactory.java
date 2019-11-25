package com.example.brmm;

public class RentableFactory {
    public static Rentable buildRentable(String type) {
        Rentable rentable = null;
        switch (type) {
            case "Instrument":
                rentable = new Instrument();
                break;

            case "Part":
                rentable = new Part();
                break;

            default:
                // throw some exception
                break;
        }
        return rentable;
    }
}
