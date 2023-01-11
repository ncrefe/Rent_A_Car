package Enums;

public enum ModelYearRatio {

    // enum constants calling the enum constructors
    YEAR_2022(1.0),
    YEAR_2020_2021(0.95),
    YEAR_2017_2019(0.9);

    private final double modelYearRatio;

    // private enum constructor
    ModelYearRatio(double yearRatio) {
        this.modelYearRatio = yearRatio;
    }

    public double getModelYearRatio() {
        return modelYearRatio;
    }
}
