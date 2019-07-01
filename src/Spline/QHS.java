package Spline;

import Coords.*;

public class QHS {
    double ax,bx,cx,dx,ex,fx;
    double ay,by,cy,dy,ey,fy;

    public QHS(Waypoint p0, Waypoint p1) {
        ax = -6 * p0.x - 3 * p0.dx - 0.5 * p0.ddx + 0.5 * p1.ddx - 3 * p1.dx + 6 * p1.x;
        bx = 15 * p0.x + 8 * p0.dx + 1.5 * p0.ddx - p1.ddx + 7 * p1.dx - 15 * p1.x;
        cx = -10 * p0.x - 6 * p0.dx - 1.5 * p0.ddx + 0.5 * p1.ddx - 4 * p1.dx + 10 * p1.x;
        dx = 0.5 * p0.ddx;
        ex = p0.dx;
        fx = p0.x;

        ay = -6 * p0.y - 3 * p0.dy - 0.5 * p0.ddy + 0.5 * p1.ddy - 3 * p1.dy + 6 * p1.y;
        by = 15 * p0.y + 8 * p0.dy + 1.5 * p0.ddy - p1.ddy + 7 * p1.dy - 15 * p1.y;
        cy = -10 * p0.y - 6 * p0.dy - 1.5 * p0.ddy + 0.5 * p1.ddy - 4 * p1.dy + 10 * p1.y;
        dy = 0.5 * p0.ddy;
        ey = p0.dy;
        fy = p0.y;
    }
}
