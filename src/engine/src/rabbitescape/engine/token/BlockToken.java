package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.Token;
import rabbitescape.engine.World;

import static rabbitescape.engine.ChangeDescription.State.*;

public class BlockToken extends Token {
    // Constructors
    public BlockToken(int x, int y) {
        super(x, y);
    }

    public BlockToken(int x, int y, World world) {
        super(x, y, world);
    }

    // Instance-level methods
    @Override
    public Type getType() {
        return Type.block;
    }

    @Override
    protected State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
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
}