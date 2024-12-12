package rabbitescape.engine;

import java.util.Map;

import rabbitescape.engine.ChangeDescription.State;

public abstract class Behaviour
{
    protected SaveRestoreStrategy<Integer> saveRestoreInt = new SaveRestoreIfGtZero();
    protected SaveRestoreStrategy<Boolean> saveRestoreBool = new SaveRestoreIfGtTrue();
    protected SaveRestoreStrategy<String> saveRestoreString = new SaveRestoreIfNotDefault();
    
    public void setIntState(SaveRestoreIfGtZero stateInt) {
        saveRestoreInt = stateInt;
    }
    
    public void setBoolState(SaveRestoreIfGtTrue stateBool) {
        saveRestoreBool = stateBool;
    }
    
    public void setStringState(SaveRestoreIfNotDefault stateString) {
        saveRestoreString = stateString;
    }
    
    public boolean triggered;
    /**
     * Subclasses examine the rabbit's situation using BehaviourTools and
     * return the state (see ChangeDescription) for the next time step.
     * This method may return null indicating that a different Behaviour
     * must take over.
     *
     * Note that the state determines the animation used.
     */
    public abstract State newState( BehaviourTools t, boolean triggered );

    /**
     * Move the rabbit in the world. Kill it, or record its safe exit.
     *
     * @return true if this behaviour has done all the work needed for
     *         this time step
     */
    public abstract boolean behave( World world, AbstractRabbit rabbit, State state );

    /**
     * Examine the rabbit's situation and return true if this Behaviour must
     * take control.
     */
    public abstract boolean checkTriggered( AbstractRabbit rabbit, World world );

    public abstract void cancel();
    
    protected void saveState(Map<String, String> saveState) {};

    protected void restoreFromState( Map<String, String> saveState ) {};
}
