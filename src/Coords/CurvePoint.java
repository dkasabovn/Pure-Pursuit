package Coords;

public class CurvePoint extends Waypoint {
    double Kcurvature;
    double Krad;

    public CurvePoint (double Kcurvature, double Krad, Waypoint p) {
        super(p.x,p.y,p.dx,p.dy);
        this.Kcurvature=Kcurvature;
        this.Krad=Krad;
    }

    public CurvePoint interpose(CurvePoint one, CurvePoint two) {
        return null;
    }



}
