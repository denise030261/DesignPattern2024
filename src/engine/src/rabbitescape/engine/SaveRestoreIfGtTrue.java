package rabbitescape.engine;

import java.util.Map;

public class SaveRestoreIfGtTrue implements SaveRestoreStrategy<Boolean>
{
    @Override
    public void saveState(Map<String, String> saveState, String key, Boolean value, Boolean defaultValue)
    {
        if (value == defaultValue)
        {
            saveState.put(key, Boolean.toString(value));
        }
    }

    @Override
    public Boolean restoreState(Map<String, String> saveState, String key, Boolean defaultValue)
    {
        String val = saveState.get(key);
        if (val != null)
        {
            return Boolean.valueOf(val);
        }
        return defaultValue;
    }
}
