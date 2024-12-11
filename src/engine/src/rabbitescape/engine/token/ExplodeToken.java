package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.World;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.ChangeDescription.State.TOKEN_EXPLODE_ON_SLOPE;

public class ExplodeToken extends NewToken {
    // Constructors
    public ExplodeToken(int x, int y) {
        super(x, y);
    }

    public ExplodeToken(int x, int y, World world) {
        super(x, y, world);
    }

    // Instance-level methods
    @Override
    protected ChangeDescription.State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
        return chooseState(
                moving,
                slopeBelow,
                onSlope,
                TOKEN_EXPLODE_FALLING,
                TOKEN_EXPLODE_STILL,
                TOKEN_EXPLODE_FALL_TO_SLOPE,
                TOKEN_EXPLODE_ON_SLOPE
        );
    }

    @Override
    public String name() {
        return "Explode";
    }

    @Override
    public String overlayText() {
        return "explode";
    }
}