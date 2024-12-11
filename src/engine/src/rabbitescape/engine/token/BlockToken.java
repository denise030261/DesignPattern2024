package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.World;

import static rabbitescape.engine.ChangeDescription.State.*;

public class BlockToken extends NewToken {
    // Constructors
    public BlockToken(int x, int y) {
        super(x, y);
    }

    public BlockToken(int x, int y, World world) {
        super(x, y, world);
    }

    // Instance-level methods
    @Override
    protected ChangeDescription.State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
        return chooseState(
                moving,
                slopeBelow,
                onSlope,
                TOKEN_BLOCK_FALLING,
                TOKEN_BLOCK_STILL,
                TOKEN_BLOCK_FALL_TO_SLOPE,
                TOKEN_BLOCK_ON_SLOPE
        );
    }

    @Override
    public String name() {
        return "Block";
    }

    @Override
    public String overlayText() {
        return "block";
    }
}