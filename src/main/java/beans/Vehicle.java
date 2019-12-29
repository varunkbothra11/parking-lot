package beans;

import enums.Color;

public class Vehicle {
    private String licenseNumber;
    private Color color;

    public Vehicle(String licenseNumber, Color color) {
        this.licenseNumber = licenseNumber;
        this.color = color;
    }
}
