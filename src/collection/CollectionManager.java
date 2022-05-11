package collection;

import collection.element.Ticket;
import collection.exceptions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface CollectionManager<T extends CollectionItem> {
    int size();
    void clear();
    void reverse();
    void sort();

    void add(T element) throws UniqueParamException;
    List<T> asList();
    T generateNew();
    T getById(Long id) throws NoSuchElementException;
    T removeById(Long id) throws NoSuchElementException;
    boolean matchId(Long id);
    long countValue(String valueName, String value);
    StringTable getCollectionInfoTable();
    StringTable getCollectionContentTable();
    void parse(ArrayList<? extends CollectionItem> collection);
    void removeHead();
    T getHead();
}
