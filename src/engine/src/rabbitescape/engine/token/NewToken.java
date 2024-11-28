package rabbitescape.engine.token;

import rabbitescape.engine.*;
import rabbitescape.engine.err.RabbitEscapeException;
import rabbitescape.engine.ChangeDescription.State;

import java.util.HashMap;
import java.util.Map;

public abstract class NewToken extends Thing {
    private boolean isInitialized = false;
    private World world = null;

    public static class UnknownType extends RabbitEscapeException {
        public final NewToken token;

        public UnknownType( NewToken token ) {
            this.token = token;
        }

        private static final long serialVersionUID = 1L;
    }

    public NewToken(int x, int y) {
        super(x, y, State.NOTHING);
    }

    public NewToken(int x, int y, World world) {
        this(x, y);
        this.world = world;
    }

    protected State switchType(
        boolean moving,
        boolean slopeBelow,
        boolean onSlope
    ) {
        if (!isInitialized) this.init();

    }

    private void init() {
        if (!isInitialized) {
            if (world == null) {
                this.switchType(false, false, true);
            } else {
                boolean onSlope = BehaviourTools.isSlope(world.getBlockAt(x, y));
                this.state = switchType(false, false, onSlope);
            }

            this.isInitialized = true;
        }
    }

    private State chooseState(
        boolean moving,
        boolean slopeBelow,
        boolean onSlope,
        State falling,
        State onFlat,
        State fallingToSlope,
        State onSlopeState
    ) {
        if (onSlope) {
            return onSlopeState;
        }
        if (!moving) {
            return onFlat;
        }
        if (slopeBelow) {
            return fallingToSlope;
        }
        return falling;
    }

    @Override
    public void calcNewState(World world) {
        if (!isInitialized) {

        }

        Block onBlock = world.getBlockAt( x, y );
        Block belowBlock = world.getBlockAt( x, y + 1 );
        boolean still = (
                BehaviourTools.s_isFlat( belowBlock )
                        || ( onBlock != null )
                        || BridgeTools.someoneIsBridgingAt( world, x, y )
        );

        state = switchType( type, !still,
                BehaviourTools.isSlope( belowBlock ),
                BehaviourTools.isSlope( onBlock ) );
    }

    @Override
    public Map<String, String> saveState(boolean runtimeMeta) {
        return new HashMap<>();
    }

    @Override
    public void restoreFromState(Map<String, String> state) {
        // Do nothing
    }

    @Override
    public abstract String toString();

    public abstract String name();

    @Override
    public String overlayText() {
        return this.toString();
    }
}