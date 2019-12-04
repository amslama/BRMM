package com.example.brmm;

public class RentableFactory {
    public static Rentable buildRentable(String type, String co, String st, String na, double price, int idnum) {
        Rentable rentable = null;
        switch (type) {
            case "Instrument":
                rentable = new Instrument(co, st, na, price, idnum);
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