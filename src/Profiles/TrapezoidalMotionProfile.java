package Profiles;

import Coords.State;
import Interfaces.EAccuracy;
import Math.*;

import java.util.ArrayList;
import java.util.Collections;

public class TrapezoidalMotionProfile {
    public ArrayList<State> profile;
    double time;

    public TrapezoidalMotionProfile(MotionConstraint c, EAccuracy sample, double time) {
        profile = new ArrayList<>();
        this.time=time;
        profile = generateProfile(c, sample);
    }

    private ArrayList<State> generateLeg(MotionConstraint c, EAccuracy sample, boolean reverse) {
        ArrayList<State> leg = new ArrayList<>();
        for (double i = 0; i <= time; i+=(time/sample.size)) {
            double v = c.maxA*i;
            if (v > c.maxV || MathUtil.fuzzyEquals(i, time/2, .000001)) {
                break;
            } else {
                leg.add(new State(v, (reverse) ? -c.maxA : c.maxA));
            }
        }
        if (reverse) Collections.reverse(leg);
        return leg;
    }

    private ArrayList<State> generateProfile(MotionConstraint c, EAccuracy sample) {
        ArrayList<State> full = new ArrayList<>();
        ArrayList<State> fLeg = generateLeg(c, sample, false);
        full.addAll(fLeg);
        ArrayList<State> tba = new ArrayList<State>();
        if (sample.size-full.size()-fLeg.size() > 0) {
            for (int i : new int[(int)sample.size-full.size()-fLeg.size()]) {
                tba.add(new State(c.maxV, 0));
            }
            full.addAll(tba);
        }
        full.addAll(generateLeg(c, sample, true));
        return full;
    }
}
