package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.Token;
import rabbitescape.engine.World;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.ChangeDescription.State.TOKEN_DIG_ON_SLOPE;

public class DigToken extends Token {
    // Constructors
    public DigToken(int x, int y) {
        super(x, y);
    }

    public DigToken(int x, int y, World world) {
        super(x, y, world);
    }

    // Instance-level methods
    @Override
    public Type getType() {
        return Type.dig;
    }

    @Override
    protected State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
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
}