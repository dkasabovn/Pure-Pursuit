package Spline;

import Coords.*;
import Interfaces.*;

public class ParametricEquation implements Spline {
    Equation x,y;

    public ParametricEquation(Equation x, Equation y) {
        this.x=x;
        this.y=y;
    }

    public String toString() { return "((" + x.toString() + "),(" + y.toString() + "))"; }
    public double getDerivative(double t){
        return y.getDerivative(t)/x.getDerivative(t);
    }
    public double getDDerivative(double t){
        return y.getDDerivative(t)/x.getDDerivative(t);
    }
    public double getDDDerivative(double t) { return y.getDDDerivative(t)/x.getDDDerivative(t); }
    public Point getPoint(double t) {
        return new Point(x.getPoint(t).y, y.getPoint(t).y);
    }

    public double getCurvature(double t) {
        return ((x.getDerivative(t) * y.getDDerivative(t)) - (y.getDerivative(t) * x.getDDerivative(t)))
                /(Math.pow((x.getDerivative(t) * x.getDerivative(t) + (y.getDerivative(t) * y.getDerivative(t))), 1.5));
    }
}
