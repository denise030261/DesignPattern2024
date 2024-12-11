package rabbitescape.engine.token;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Token;
public class UnknownToken extends Token {
    // Constructors
    public UnknownToken() {
        super(0, 0);
    }

    // Instance-level methods
    @Override
    public Type getType() {
        throw new UnknownType(this);
    }

    @Override
    protected ChangeDescription.State switchType(boolean moving, boolean slopeBelow, boolean onSlope) {
        throw new UnknownType(this);
    }
}
