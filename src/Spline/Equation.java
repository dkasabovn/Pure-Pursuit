package Spline;

import Coords.Point;
import Interfaces.*;

public class Equation implements Spline {
    double a,b,c,d,e,f;
    EType type;

    public Equation(EType type,double a, double b, double c, double d, double e, double f) {
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.e=e;
        this.f=f;
        this.type=type;
    }

    public Equation(EType type, double a, double b, double c, double d) {
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.type=type;
    }

    public String toString() {
        if (type == EType.CUBIC_HERMITE) return a + "t^3+" + b + "t^2+" + c + "t+" + d;
        return a + "t^5+" + b + "t^4+" + c + "t^3+" + d + "t^2+" + e + "t+" + d;
    }
    public double getDerivative(double t){
        if (type == EType.CUBIC_HERMITE) {
            return 3 * t * t * a + 2 * t * b + c;
        }
        return 5 * t * t * t * t * a + 4 * t * t * t * b + 3 * t * t * c + 2 * t * d + e;
    }
    public double getDDerivative(double t){
        if (type == EType.CUBIC_HERMITE) {
            return 6 * t * a + 2 * b;
        }
        return 20 * t * t * t * a + 12 * t * t * b + 6 * t * c + 2 * d;
    }
    public double getDDDerivative(double t){
        //TODO do this
        if (type == EType.CUBIC_HERMITE) {
            return 3 * t * t * a + 2 * t * b + c;
        }
        return 5 * t * t * t * t * a + 4 * t * t * t * b + 3 * t * t * c + 2 * t * d + e;
    }

    public Point getPoint(double t) {
        double y = (type == EType.CUBIC_HERMITE) ? a * t * t * t + b * t * t + c * t + d : a * t * t * t * t * t + b * t * t * t * t + c * t * t * t + d * t * t + e * t + f;
        return new Point(t, y);
    }
}
