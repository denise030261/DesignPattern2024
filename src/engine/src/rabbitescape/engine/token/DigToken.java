package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.World;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.ChangeDescription.State.TOKEN_DIG_ON_SLOPE;

public class DigToken extends NewToken {
    // Constructors
    public DigToken(int x, int y) {
        super(x, y);
    }

    public DigToken(int x, int y, World world) {
        super(x, y, world);
    }

    // Instance-level methods
    @Override
    protected ChangeDescription.State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
        return chooseState(
                moving,
                slopeBelow,
                onSlope,
                TOKEN_DIG_FALLING,
                TOKEN_DIG_STILL,
                TOKEN_DIG_FALL_TO_SLOPE,
                TOKEN_DIG_ON_SLOPE
        );
    }

    @Override
    public String name() {
        return "Dig";
    }

    @Override
    public String overlayText() {
        return "dig";
    }
}