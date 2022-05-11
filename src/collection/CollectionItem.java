package collection;

import collection.exceptions.NoSuchValueException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CollectionItem extends Comparable<CollectionItem>  {

    String nullInputString = "";

    Long getId();

    void setId(Long id);

    String getValue(String valueName) throws NoSuchValueException;

    void setValue(String valueName, String value) throws NoSuchValueException;

    Set<String> getSettersTopics();

    Set<String> getGettersTopics();

    List<String> getUniqueValueNameList();

    Map<String, String> getFormatMap();
}