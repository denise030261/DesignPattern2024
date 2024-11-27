package rabbitescape.engine;

import static rabbitescape.engine.ChangeDescription.State.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.behaviours.*;

public abstract class AbstractRabbit extends Thing implements Comparable<AbstractRabbit>
{
    public final static int NOT_INDEXED = 0;
    protected final List<Behaviour> behaviours;
    protected final List<Behaviour> behavioursTriggerOrder;

    public int index;

    protected Falling falling;

    public Direction dir;
    public boolean onSlope;

    public boolean slopeBaseHop = false;

    //Type determines whether or not to count a rabbit kill
    //Therefore a new way to tell if to decrement the count of live rabbits is needed
    public abstract boolean countKill(); //Some rabbits may not count towards the kill count
    
    public AbstractRabbit(int x, int y, Direction dir)
    {
	super(x, y, RABBIT_WALKING_LEFT);
	this.dir = dir;
	this.onSlope = false;
	behaviours = new ArrayList<>();
	behavioursTriggerOrder = new ArrayList<>();
	createBehaviours(); //Must be overrided
	index = NOT_INDEXED;
    }

    private abstract void createBehaviours(); //Actor may have different behaviours

    public boolean isFallingToDeath()
    {
	return falling.isFallingToDeath();
    }

    public abstract void calcNewState(World world);

    public void cancelAllBehavioursExcept(Behaviour exception)
    {
	for (Behaviour behaviour : behaviours)
	{
	    if (behaviour != exception)
	    {
		behaviour.cancel();
	    }
	}
    }

    public abstract void possibleUndoSlopeBashHop(World world); //Only actors that can bash need this

    @Override
    public void step(World world)
    {
	for (Behaviour behaviour : behaviours)
	{
	    boolean handled = behaviour.behave(world, this, state);
	    if (handled)
	    {
		break;
	    }
	}
    }

    public abstract Map<String, String> saveState(boolean runtimeMeta); //States structure may be different
									
    public abstract restoreFromState(Map<String, String> state); //Naturally restore will be different

    public abstract String overlayText(); //Overlay text should be different for different types of rabbit

    @Override
    public int compareTo(Rabbit r)
    {
	return this.index - r.index;
    }

    @Override
    public boolean equals(Object o)
    {
	if (null == o || !(o instanceof AbstractRabbit))
	{
	    return false;
	}
	return ((AbstractRabbit)o).index == this.index;
    }

    @Override
    public int hashCode()
    {
	return index;
    }

    public abstract String stateName(); //Actors may have different states

    protected abstract int getFatalHeight(); //Actors may have diffent fatal heights
