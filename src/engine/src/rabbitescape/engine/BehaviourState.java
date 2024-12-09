package rabbitescape.engine;

import java.util.Map;

public class BehaviourState
{
    public static void addToStateIfNotDefault(
        Map<String, String> saveState, String key, String value, String def
    )
    {
        if ( !def.equals( value  ) )
        {
            saveState.put( key, value );
        }
    }

    public static String restoreFromState(
        Map<String, String> saveState, String key, String defaultValue )
    {
        String val = saveState.get( key );
        if ( val != null )
        {
            return val;
        }
        else
        {
            return defaultValue;
        }
    }
    
    private static SaveRestoreStrategy<Integer> gtZeroStrategy = new SaveRestoreIfGtZero();
    private static SaveRestoreStrategy<Boolean> trueStrategy = new SaveRestoreIfGtTrue();

    public static void saveIfGtZero(Map<String, String> saveState, String key, int value)
    {
        gtZeroStrategy.saveState(saveState, key, value,0);
    }

    public int restoreIfGtZero(Map<String, String> saveState, String key, int defaultValue)
    {
        return gtZeroStrategy.restoreState(saveState, key, defaultValue);
    }

    public void saveIfTrue(Map<String, String> saveState, String key, boolean value)
    {
        trueStrategy.saveState(saveState, key, value,true);
    }

    public boolean restoreIfTrue(Map<String, String> saveState, String key, boolean defaultValue)
    {
        return trueStrategy.restoreState(saveState, key, defaultValue);
    }
}
