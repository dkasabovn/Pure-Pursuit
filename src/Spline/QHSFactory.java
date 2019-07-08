package Spline;

import Coords.Waypoint;

import java.util.ArrayList;

public class QHSFactory {
    public static ParametricEquation interpolate(Waypoint p0, Waypoint p1) {
        return new QHS(p0, p1).getParametric();
    }

    public static ArrayList<ParametricEquation> interpolate(ArrayList<Waypoint> points){
        //TODO knot generatedLines
        ArrayList<ParametricEquation> lines = new ArrayList<>();
        for (int i = 0; i < points.size()-1; i++) {
            lines.add(interpolate(points.get(i), points.get(i+1)));
        }
        return lines;
    }

    public static double ArcLength(ArrayList<ParametricEquation> lines, double stepSize) {
        double sum = 0;
        for (ParametricEquation line : lines) {
            sum+=ArcLength(line, stepSize);
        }
        return sum;
    }

    public static double ArcLength(ParametricEquation line, double stepSize) {
        double sum = 0;
        for (double i=0; i <= 1.0; i+=stepSize) {
            sum+= Math.sqrt(Math.pow(line.x.getDerivative(i),2) + Math.pow(line.y.getDerivative(i),2)) * stepSize;
        }
        return sum;
    }

    public static double getCurvature(ParametricEquation eq, double t) {
        return ((eq.x.getDerivative(t)*eq.y.getDDerivative(t))-(eq.y.getDerivative(t)*eq.x.getDDerivative(t)))/Math.pow((Math.pow(eq.x.getDerivative(t),2) + Math.pow(eq.y.getDerivative(t),2)),1.5);
    }
}
