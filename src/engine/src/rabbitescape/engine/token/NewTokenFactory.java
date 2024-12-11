package rabbitescape.engine.token;

public interface NewTokenFactory {
    Bash createBashToken();
    Block createBlockToken();
    Bridge createBridgeToken();
    Brolly createBrollyToken();
    Climb createClimbToken();
    Dig createDigToken();
    Explode createExplodeToken();
}