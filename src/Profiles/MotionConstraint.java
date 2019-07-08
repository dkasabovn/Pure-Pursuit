package Profiles;

public class MotionConstraint {
    double maxV, maxA, maxJ;
    double maxWV, maxWA;

    public MotionConstraint(double maxV, double maxA, double maxWV, double maxWA) {
        this.maxV=maxV;
        this.maxA=maxA;
        this.maxWV=maxWV;
        this.maxWA=maxWA;
    }
}
