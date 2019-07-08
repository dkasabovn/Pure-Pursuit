package Coords;

public class State {
    public double v,a;

    public State(double vel, double acc) {
        v=vel;
        a=acc;
    }
    public String toString() {
        return "(" + v + "," + a + ")";
    }
}
