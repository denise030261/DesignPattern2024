package rabbitescape.engine;

import java.util.HashMap;
import java.util.Map;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.behaviours.*;

public class Rabbot extends AbstractRabbit
{
    SaveRestoreStrategy<Integer> saveRestoreInt = new SaveRestoreIfGtZero();
    SaveRestoreStrategy<Boolean> saveRestoreBool = new SaveRestoreIfGtTrue();

    public Rabbot( int x, int y, Direction dir )
    {
        super( x, y, dir );
        createIndex = 1;
    }

    @Override
    public boolean countKill()
    {
	return false;
    }

    @Override
    protected void createBehaviours()
    {
        Climbing climbing = new Climbing();
        Digging digging = new Digging();
        Exploding exploding = new Exploding();
        Burning burning = new Burning();
        OutOfBounds outOfBounds = new OutOfBounds();
        Drowning drowning = new Drowning();
        Exiting exiting = new Exiting();
        Brollychuting brollychuting = new Brollychuting( climbing, digging );
        falling = new Falling( climbing, brollychuting, getFatalHeight() );
        Bashing bashing = new Bashing();
        Bridging bridging = new Bridging();
        Blocking blocking = new Blocking();
        Walking walking = new Walking();
        RabbotCrash rabbotCrash = new RabbotCrash();
        RabbotWait rabbotWait = new RabbotWait();

        behavioursTriggerOrder.add( exploding );
        behavioursTriggerOrder.add( outOfBounds );
        behavioursTriggerOrder.add( burning );
        behavioursTriggerOrder.add( drowning );
        behavioursTriggerOrder.add( rabbotCrash );
        behavioursTriggerOrder.add( falling );
        behavioursTriggerOrder.add( exiting );
        behavioursTriggerOrder.add( brollychuting );
        behavioursTriggerOrder.add( climbing );
        behavioursTriggerOrder.add( bashing );
        behavioursTriggerOrder.add( digging );
        behavioursTriggerOrder.add( bridging );
        behavioursTriggerOrder.add( blocking );
        behavioursTriggerOrder.add( rabbotWait );
        behavioursTriggerOrder.add( walking );

        behaviours.add( exploding );
        behaviours.add( outOfBounds );
        behaviours.add( burning );
        behaviours.add( drowning );
        behaviours.add( rabbotCrash );
        behaviours.add( falling );
        behaviours.add( exiting );
        behaviours.add( brollychuting );
        behaviours.add( bashing );
        behaviours.add( digging );
        behaviours.add( bridging );
        behaviours.add( blocking );
        behaviours.add( climbing );
        behaviours.add( rabbotWait );
        behaviours.add( walking );

        assert behavioursTriggerOrder.size() == behaviours.size();
    }

    @Override
    public void calcNewState( World world )
    {
        for ( Behaviour behaviour : behavioursTriggerOrder )
        {
            behaviour.triggered = false;
        }

        for ( Behaviour behaviour : behavioursTriggerOrder )
        {
            behaviour.triggered = behaviour.checkTriggered( this, world );
            if ( behaviour.triggered )
            {
                cancelAllBehavioursExcept( behaviour );
            }
        }

        boolean done = false;
        for ( Behaviour behaviour : behaviours )
        {

            State thisState = behaviour.newState(
                new BehaviourTools( this, world ), behaviour.triggered );

            if ( thisState != null && !done )
            {
                state = thisState;
                done = true;
            }
        }

    }

    @Override
    protected void cancelAllBehavioursExcept( Behaviour exception )
    {
        for ( Behaviour behaviour : behaviours )
        {
            if ( behaviour != exception )
            {
                behaviour.cancel();
            }
        }
    }

    @Override
    public void possiblyUndoSlopeBashHop( World world )
    {
        if ( !slopeBashHop )
        {
            return;
        }
        BehaviourTools t = new BehaviourTools( this, world );
        if ( t.blockHere() != null ||
            !BehaviourTools.isSlope( t.blockBelow() ) )
        {
            return;
        }
        ++y;
        slopeBashHop = false;
    }

    @Override
    public Map<String, String> saveState( boolean runtimeMeta )
    {
        Map<String, String> ret = new HashMap<String, String>();
        if ( !runtimeMeta )
        {
            return ret;
        }
        
        saveRestoreInt.saveState( ret, "index", index,0 );
        saveRestoreBool.saveState( ret, "onSlope", onSlope,true );

        for ( Behaviour behaviour : behaviours )
        {
            behaviour.saveState( ret );
        }

        return ret;
    }

    @Override
    public void restoreFromState( Map<String, String> state )
    {
        index = saveRestoreInt.restoreState( state, "index", -1 );
        onSlope = saveRestoreBool.restoreState( state, "onSlope", false );

        for ( Behaviour behaviour : behaviours )
        {
            behaviour.restoreFromState( state );
        }
    }

    @Override
    public String overlayText()
    {
        String fmt;
        switch ( dir )
        {
        case LEFT:
            fmt = "<[%d] ";
            break;
        case RIGHT:
            fmt = " [%d]>";
            break;
        default:
            throw new RuntimeException( "Rabbit should only be left or right");
        }
        return String.format( fmt, index ) ;
    }

    @Override
    public String stateName()
    {
        String normalName = super.stateName();
        if ( countKill() )
        {
            return normalName;
        }
        else
        {
            return normalName.replaceFirst(
                "^rabbit", "RABBOT".toLowerCase() );
        }
    }

    /** Rabbots can fall further than rabbits. */
    @Override
    protected int getFatalHeight()
    {
        return ( countKill() ? 4 : 5 );
    }

    @Override
    public char rabbitChar()
    {
	if ( dir == Direction.RIGHT )
	{
	    return 't';
	}
	else
	{
	    return 'y';
	}
    }
}
