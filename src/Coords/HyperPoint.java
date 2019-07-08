package Coords;

public class HyperPoint extends CurvePoint {
    double vel, acc;
    Point vectorVel, vectorAcc;
    Point pos;

    public HyperPoint() {
        super(0,0,new Waypoint(0,0,0,0));
    }

}
