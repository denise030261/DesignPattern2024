package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.Token;
import rabbitescape.engine.World;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.ChangeDescription.State.TOKEN_BRIDGE_ON_SLOPE;

public class Bridge extends Token {
    // Constructors
    public Bridge(int x, int y) {
        super(x, y);
    }

    public Bridge(int x, int y, World world) {
        super(x, y, world);
    }

    // Instance-level methods
    @Override
    protected State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
        return chooseState(
                moving,
                slopeBelow,
                onSlope,
                TOKEN_BRIDGE_FALLING,
                TOKEN_BRIDGE_STILL,
                TOKEN_BRIDGE_FALL_TO_SLOPE,
                TOKEN_BRIDGE_ON_SLOPE
        );
    }
}