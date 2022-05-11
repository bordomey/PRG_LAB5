package collection;
import collection.exceptions.DataException;
import collection.exceptions.InvalidDataFormatException;
import collection.exceptions.InvalidInputException;
import collection.exceptions.NoSuchValueException;
import com.sun.media.sound.InvalidDataException;

import java.io.File;
import java.security.DomainCombiner;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

abstract public class CollectionItemAdapter implements CollectionItem {

    protected Map<String, Supplier<String>> gettersMap;
    protected Map<String, Consumer<String>> settersMap;
    protected static List<String> uniqueValueNameList;
    protected static List<String> bannedSettersNameList;
    static {
        bannedSettersNameList = new ArrayList<>();
        uniqueValueNameList = new ArrayList<>();
        uniqueValueNameList.add("id");
    }

    {
        gettersMap = new LinkedHashMap<>();
        settersMap = new LinkedHashMap<>();

        gettersMap.put("id", () -> this.getId().toString());

        settersMap.put("id", (str) -> this.setId(Long.valueOf(str)));
        bannedSettersNameList.add("id");
    }



    @Override
    public Set<String> getSettersTopics() {
        Set<String> keySet = settersMap.keySet();
        keySet.removeAll(bannedSettersNameList);
        return keySet;
    }

    @Override
    public Set<String> getGettersTopics() {
        return gettersMap.keySet();
    }

    @Override
    public List<String> getUniqueValueNameList() {
        return uniqueValueNameList;
    }

    @Override
    public String getValue(String valueName) {
        if(gettersMap.keySet().stream().noneMatch(str -> str.equals(valueName))) throw new NoSuchValueException(valueName);
        try {
            return gettersMap.get(valueName).get()!=null ? gettersMap.get(valueName).get() : "null";
        } catch (NullPointerException e) {
            return "null";
        }
    }

    @Override
    public void setValue(String valueName, String value) {
        if(settersMap.keySet().stream().noneMatch(str -> str.equals(valueName))) throw new NoSuchValueException(valueName);
        try {
            settersMap.get(valueName).accept(value);
        } catch (DataException e) {
            throw new InvalidInputException(value, valueName,  e);
        } catch (IllegalArgumentException | DateTimeParseException e ){
            throw new InvalidInputException(value, valueName, new InvalidDataFormatException());
        }
    }

    @Override
    public int compareTo(CollectionItem o) {
        return getId().compareTo(o.getId());
    }
}
