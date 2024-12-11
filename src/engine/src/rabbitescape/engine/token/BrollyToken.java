package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.World;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.ChangeDescription.State.TOKEN_BROLLY_ON_SLOPE;

public class BrollyToken extends NewToken {
    // Constructors
    public BrollyToken(int x, int y) {
        super(x, y);
    }

    public BrollyToken(int x, int y, World world) {
        super(x, y, world);
    }

    // Instance-level methods
    @Override
    protected ChangeDescription.State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
        return chooseState(
                moving,
                slopeBelow,
                onSlope,
                TOKEN_BROLLY_FALLING,
                TOKEN_BROLLY_STILL,
                TOKEN_BROLLY_FALL_TO_SLOPE,
                TOKEN_BROLLY_ON_SLOPE
        );
    }

    @Override
    public String name() {
        return "Brolly";
    }

    @Override
    public String overlayText() {
        return "brolly";
    }
}