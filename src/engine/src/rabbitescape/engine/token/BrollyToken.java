package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.Token;
import rabbitescape.engine.World;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.ChangeDescription.State.TOKEN_BROLLY_ON_SLOPE;

public class BrollyToken extends Token {
    // Constructors
    public BrollyToken(int x, int y) {
        super(x, y);
    }

    public BrollyToken(int x, int y, World world) {
        super(x, y, world);
    }

    // Instance-level methods
    @Override
    public Type getType() {
        return Type.brolly;
    }

    @Override
    protected State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
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
}