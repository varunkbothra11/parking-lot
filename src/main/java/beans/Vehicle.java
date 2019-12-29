package beans;

import enums.Color;

/**
 * @author varun.bothra
 */
public class Vehicle {
    private String licenseNumber;
    private Color color;

    public Vehicle(String licenseNumber, Color color) {
        this.licenseNumber = licenseNumber;
        this.color = color;
    }

    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    public Color getColor() {
        return this.color;
    }
}
