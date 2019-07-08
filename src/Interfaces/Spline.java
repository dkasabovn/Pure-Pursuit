package Interfaces;

import Coords.Point;

public interface Spline {
    double getDerivative(double t);
    double getDDerivative(double t);
    double getDDDerivative(double t);
    Point getPoint(double t);
}
