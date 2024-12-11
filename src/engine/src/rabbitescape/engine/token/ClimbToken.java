package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.Token;
import rabbitescape.engine.World;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.ChangeDescription.State.TOKEN_CLIMB_ON_SLOPE;

public class ClimbToken extends Token {
    // Constructors
    public ClimbToken(int x, int y) {
        super(x, y);
    }

    public ClimbToken(int x, int y, World world) {
        super(x, y, world);
    }

    // Instance-level methods
    @Override
    protected State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
        return chooseState(
                moving,
                slopeBelow,
                onSlope,
                TOKEN_CLIMB_FALLING,
                TOKEN_CLIMB_STILL,
                TOKEN_CLIMB_FALL_TO_SLOPE,
                TOKEN_CLIMB_ON_SLOPE
        );
    }
}