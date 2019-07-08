package Spline;

import Coords.CurvePoint;
import Interfaces.EAccuracy;

import java.util.ArrayList;

public class ParametrizedSpline {
    ArrayList<CurvePoint> spline;
    double sample;

    public ParametrizedSpline(ParametricEquation eq, EAccuracy sample) {
        this.sample=sample.size;
        spline = parametrize(eq);
    }

    public ArrayList<CurvePoint> parametrize(ParametricEquation eq) {
        
        return null;
    }
}
