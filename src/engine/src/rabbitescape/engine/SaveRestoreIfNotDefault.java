package rabbitescape.engine;

import java.util.Map;

public class SaveRestoreIfNotDefault implements SaveRestoreStrategy<String>
{
    @Override
    public
        void
        saveState( Map<String, String> saveState, String key, String value,String defaultValue )
    {
        if ( defaultValue!=value )
        {
            saveState.put( key, value );
        }
    }

    @Override
    public String restoreState(
        Map<String, String> saveState,
        String key,
        String defaultValue )
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
}
