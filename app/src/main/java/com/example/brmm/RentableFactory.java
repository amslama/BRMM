package com.example.brmm;

public class RentableFactory {

    //returns a Rentable of specific type
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
                break;
        }
        return rentable;
    }
}