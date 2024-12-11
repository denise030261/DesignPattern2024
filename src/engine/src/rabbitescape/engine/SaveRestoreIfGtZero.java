package rabbitescape.engine;

import java.util.Map;

public class SaveRestoreIfGtZero implements SaveRestoreStrategy<Integer>
{
    @Override
    public void saveState(Map<String, String> saveState, String key, Integer value, Integer defaultValue)
    {
        if (value > defaultValue)
        {
            saveState.put(key, Integer.toString(value));
        }
    }

    @Override
    public Integer restoreState(Map<String, String> saveState, String key, Integer defaultValue)
    {
        String val = saveState.get(key);
        if (val != null)
        {
            try
            {
                return Integer.valueOf(val);
            }
            catch (NumberFormatException e)
            {
                throw new BadSavedState(e, saveState);
            }
        }
        return defaultValue;
    }
}
