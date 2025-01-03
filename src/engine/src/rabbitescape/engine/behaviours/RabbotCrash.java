package rabbitescape.engine.behaviours;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.BehaviourTools;
import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.AbstractRabbit;
import rabbitescape.engine.World;

public class RabbotCrash extends Behaviour
{
    @Override
    public void cancel()
    {
    }

    @Override
    public boolean checkTriggered( AbstractRabbit rabbit, World world )
    {
        if ( !rabbit.countKill() )
        {
            for ( AbstractRabbit otherRabbit : world.rabbits )
            {
                if ( otherRabbit.countKill() &&
                    otherRabbit.x == rabbit.x &&
                    otherRabbit.y == rabbit.y
                )
                {
                    world.changes.killRabbit( otherRabbit );
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            return State.RABBIT_CRASHING;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean behave( World world, AbstractRabbit rabbit, State state )
    {
        if ( state == State.RABBIT_CRASHING )
        {
            world.changes.killRabbit( rabbit );
            return true;
        }

        return false;
    }
}
