package rabbitescape.engine.token;

public interface NewTokenFactory {
    BashToken createBashToken();
    BlockToken createBlockToken();
    BridgeToken createBridgeToken();
    BrollyToken createBrollyToken();
    ClimbToken createClimbToken();
    DigToken createDigToken();
    ExplodeToken createExplodeToken();
}