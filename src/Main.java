import Coords.*;
import Interfaces.EAccuracy;
import Interfaces.EType;
import Profiles.MotionConstraint;
import Profiles.TrapezoidalMotionProfile;
import Spline.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MotionConstraint c = new MotionConstraint(20, 2, 0,0);
        TrapezoidalMotionProfile tm = new TrapezoidalMotionProfile(c, EAccuracy.SAMPLE_LOW, 15);
        for (State i : tm.profile) {
            System.out.println(i);
        }
        System.out.println(tm.profile.size());
    }
}
