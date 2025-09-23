package entity;

public class Quality {
    private String quality;
    private double coefficient;

    public Quality(String quality, double coefficient) {
        this.quality = quality;
        this.coefficient = coefficient;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return quality  + ", coefficient=" + coefficient;
    }
}
