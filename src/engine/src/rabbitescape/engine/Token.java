package rabbitescape.engine;

import static rabbitescape.engine.ChangeDescription.State.*;

import java.util.HashMap;
import java.util.Map;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.err.RabbitEscapeException;
import rabbitescape.engine.token.*;

public abstract class Token extends Thing {
    // Exceptions
    public static class UnknownType extends RabbitEscapeException {
        public final Token token;

        public UnknownType(Token token) {
            this.token = token;
        }

        private static final long serialVersionUID = 1L;
    }

    // Enums
    public enum Type {
        bash,
        dig,
        bridge,
        block,
        climb,
        explode,
        brolly
    }

    // Constructors
    public Token(int x, int y) {
        super(x, y, NOTHING);
        this.state = switchType(false, false, true);
    }

    public Token(int x, int y, World world) {
        this(x, y);
        boolean onSlope = BehaviourTools.isSlope( world.getBlockAt( x, y ) );
        // Can't use calcNewState here since we have just been created, so
        // can't be moving (until a time step passes).
        this.state = switchType( false, false, onSlope);
    }

    // Factory methods
    public static Token createToken(Type type, int x, int y) {
        return switch (type) {
            case bash -> new BashToken(x, y);
            case dig -> new DigToken(x, y);
            case bridge -> new BridgeToken(x, y);
            case block -> new BlockToken(x, y);
            case climb -> new ClimbToken(x, y);
            case explode -> new ExplodeToken(x, y);
            case brolly -> new BrollyToken(x, y);
        };
    }

    public static Token createToken(Type type, int x, int y, World world) {
        return switch (type) {
            case bash -> new BashToken(x, y, world);
            case dig -> new DigToken(x, y, world);
            case bridge -> new BridgeToken(x, y, world);
            case block -> new BlockToken(x, y, world);
            case climb -> new ClimbToken(x, y, world);
            case explode -> new ExplodeToken(x, y, world);
            case brolly -> new BrollyToken(x, y, world);
        };
    }

    // Class-level methods
    public static String name(Token token) {
        String n = token.toString();
        return n.substring(0, 1).toUpperCase() + n.substring(1);
    }

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

    public abstract Type getType();

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

        state = switchType(
                !still,
                BehaviourTools.isSlope(belowBlock),
                BehaviourTools.isSlope(onBlock)
        );
    }

    @Override
    public void step(World world) {
        switch (state) {
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
            if (y >= world.size.height) world.changes.removeToken(this);
            return;
        }

        default: // Nothing to do
        }
    }

    @Override
    public Map<String, String> saveState(boolean runtimeMeta) {
        return new HashMap<>();
    }

    @Override
    public void restoreFromState(Map<String, String> state) {}

    @Override
    public String overlayText() {
        return this.toString();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
