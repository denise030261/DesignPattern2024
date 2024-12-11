package rabbitescape.engine.token;

import rabbitescape.engine.*;
import rabbitescape.engine.err.RabbitEscapeException;
import rabbitescape.engine.ChangeDescription.State;

import java.util.HashMap;
import java.util.Map;

public abstract class NewToken extends Thing {
    // Exceptions
    public static class UnknownType extends RabbitEscapeException {
        public final NewToken token;

        public UnknownType( NewToken token ) {
            this.token = token;
        }

        private static final long serialVersionUID = 1L;
    }

    // Constructors
    public NewToken(int x, int y) {
        super(x, y, State.NOTHING);
        this.state = switchType(false, false, true);
    }

    public NewToken(int x, int y, World world) {
        this(x, y);
        boolean onSlope = BehaviourTools.isSlope(world.getBlockAt(x, y));
        this.state = switchType(false, false, onSlope);
    }

    // Class-level methods
    protected static State chooseState(
            boolean moving,
            boolean slopeBelow,
            boolean onSlope,
            State falling,
            State onFlat,
            State fallingToSlope,
            State onSlopeState
    ) {
        if (onSlope) return onSlopeState;
        if (!moving) return onFlat;
        if (slopeBelow) return fallingToSlope;
        return falling;
    }

    // Instance-level methods
    protected abstract State switchType(
            boolean moving,
            boolean slopeBelow,
            boolean onSlope
    );

    @Override
    public void calcNewState(World world)
    {
        Block onBlock = world.getBlockAt(x, y);
        Block belowBlock = world.getBlockAt(x, y + 1);
        boolean still = (
                BehaviourTools.s_isFlat(belowBlock)
                        || (onBlock != null)
                        || BridgeTools.someoneIsBridgingAt(world, x, y)
        );

        this.state = switchType(
                !still,
                BehaviourTools.isSlope(belowBlock),
                BehaviourTools.isSlope(onBlock)
        );
    }

    @Override
    public void step(World world) {
        switch (this.state) {
            case TOKEN_BASH_FALLING:
            case TOKEN_BASH_FALL_TO_SLOPE:
            case TOKEN_DIG_FALLING:
            case TOKEN_DIG_FALL_TO_SLOPE:
            case TOKEN_BRIDGE_FALLING:
            case TOKEN_BRIDGE_FALL_TO_SLOPE:
            case TOKEN_BLOCK_FALLING:
            case TOKEN_BLOCK_FALL_TO_SLOPE:
            case TOKEN_CLIMB_FALLING:
            case TOKEN_CLIMB_FALL_TO_SLOPE:
            case TOKEN_EXPLODE_FALL_TO_SLOPE:
            case TOKEN_EXPLODE_FALLING:
            case TOKEN_BROLLY_FALLING:
            case TOKEN_BROLLY_FALL_TO_SLOPE:
            {
                ++y;
                if (y >= world.size.height) {
                    // TODO: Have to change related codes
                    // world.changes.removeToken(this);
                }

                return;
            }

            default: //Nothing to do
        }
    }

    @Override
    public Map<String, String> saveState(boolean runtimeMeta) {
        return new HashMap<>();
    }

    @Override
    public void restoreFromState(Map<String, String> state) {}

    public abstract String name();

    @Override
    public abstract String overlayText();
}