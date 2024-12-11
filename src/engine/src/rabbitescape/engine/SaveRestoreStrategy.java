package rabbitescape.engine;

import java.util.Map;

public interface SaveRestoreStrategy<T>
{
    void saveState(Map<String, String> saveState, String key, T value, T defaultValue);
    T restoreState(Map<String, String> saveState, String key, T defaultValue);

    //SaveRestoreStrategy<Integer> saveRestoreInt = new SaveRestoreIfGtZero();
    //SaveRestoreStrategy<Boolean> saveRestoreBool = new SaveRestoreIfGtTrue();
    //SaveRestoreStrategy<String> saveRestoreString = new SaveRestoreIfNotDefault();
}