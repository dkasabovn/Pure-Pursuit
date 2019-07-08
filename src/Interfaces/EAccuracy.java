package Interfaces;

public enum EAccuracy {
    SAMPLE_LOW(1000),
    SAMPLE_HIGH(10000),
    SAMPLE_OVERHEAT(100000),
    SAMPLE_WTF(1000000);

    public double size;

    EAccuracy(double s) {
        size=s;
    }
}
