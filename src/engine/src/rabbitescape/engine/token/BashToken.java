package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.World;

import static rabbitescape.engine.ChangeDescription.State.*;

public class BashToken extends NewToken {
    // Constructors
    public BashToken(int x, int y) {
        super(x, y);
    }

    public BashToken(int x, int y, World world) {
        super(x, y, world);
    }

    // Instance-level methods
    @Override
    protected State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
        return chooseState(
                moving,
                slopeBelow,
                onSlope,
                TOKEN_BASH_FALLING,
                TOKEN_BASH_STILL,
                TOKEN_BASH_FALL_TO_SLOPE,
                TOKEN_BASH_ON_SLOPE
        );
    }

    @Override
    public String name() {
        return "Bash";
    }

    @Override
    public String overlayText() {
        return "bash";
    }
}