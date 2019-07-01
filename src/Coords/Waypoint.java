package Coords;

public class Waypoint {
    public double x,dx,ddx,dddx;
    public double y,dy,ddy,dddy;

    //for qhs
    public Waypoint(double x,double y,double dx,double dy,double ddx,double ddy) {
        this.x=x;
        this.y=y;
        this.dx=dx;
        this.dy=dy;
        this.ddx=ddx;
        this.ddy=ddy;
    }

    //for chs
    public Waypoint(double x, double y, double dx, double dy) {
        this.x=x;
        this.y=y;
        this.dx=dx;
        this.dy=dy;
    }
}
