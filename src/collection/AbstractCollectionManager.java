package collection;

import collection.exceptions.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

abstract public class AbstractCollectionManager<T extends CollectionItem>
        implements CollectionManager<T> {
    abstract protected Collection<T> getCollection();

    private LocalDateTime initializedTime = LocalDateTime.now();

    protected boolean checkIfUnique(String valueName, String value) {
        return getCollection().stream().anyMatch(iter -> iter.getValue(valueName).equals(value));
    }

    @Override
    public int size() {
        return getCollection().size();
    }

    @Override
    public void clear() {
        getCollection().clear();
    }

    protected Long generateId() {
        if (size() == 0) return 1L;

        List<Long> idList = getCollection().stream().collect(
                ArrayList::new,
                (list, elem) -> list.add(elem.getId()),
                List::addAll
        );

        Collections.sort(idList);
        for (int i = 0; i < idList.size() - 1; i++) {
            if (idList.get(i + 1) - 1 > idList.get(i))
                return idList.get(i)+1;
        }

        return idList.get(idList.size() - 1) + 1;
    }


    @Override
    public List<T> asList() {
        return new ArrayList<>(getCollection());
    }

    @Override
    public T getById(Long id) throws ElementNotFoundException {
        if (!matchId(id)) throw new ElementNotFoundException();
        return getCollection().stream().filter(item -> item.getId().equals(id)).findFirst().get();
    }

    @Override
    public T removeById(Long id) throws ElementNotFoundException {
        T element = getById(id);
        getCollection().remove(element);
        return element;
    }


    @Override
    public long countValue(String valueName, String value) {
        return getCollection().stream().collect(
                ArrayList::new,
                (list, item) -> list.add(item.getValue(valueName)),
                ArrayList::addAll
        ).stream().filter(value::equals).count();
    }

    @Override
    public boolean matchId(Long id) {
        return getCollection().stream().anyMatch(item -> item.getId().equals(id));
    }

    @Override
    public CollectionContentTable<?> getCollectionContentTable() {
        return new CollectionContentTable(getCollection());
    }


    public static class CollectionContentTable<T extends CollectionItem> implements StringTable {
        public final Collection<T> collectionItems;

        public CollectionContentTable(Collection<T> collectionItems) {
            this.collectionItems = collectionItems;
        }

        @Override
        public List<String> getTitles() throws CollectionException {
            if(collectionItems.size()!=0)
                return new ArrayList<>(collectionItems.stream().findAny().get().getGettersTopics());
            else throw new EmptyCollectionException();
        }

        @Override
        public List<Map<String, String>> getTable() {
            if(collectionItems.size()!=0)
                return collectionItems.stream().collect(
                        ArrayList::new,
                        (list, item) -> list.add(item.getGettersTopics().stream().collect(Collectors.toMap(
                                Function.identity(),
                                (name) -> item.getValue(name).length()<=30 ? item.getValue(name) : item.getValue(name).substring(0,30)
                        ))),
                        ArrayList::addAll
                );
            else throw new EmptyCollectionException();
        }
    }

    abstract public class CollectionInfoTable implements StringTable {

        abstract protected String getType();

        @Override
        public List<String> getTitles() {
            return Arrays.asList(
                    "initialized time",
                    "count"
            );
        }

        @Override
        public List<Map<String, String>> getTable() {
            List<Map<String, String>> table = new ArrayList<>();
            table.add(StringTable.stringMatrixToMap(new String[][]{
                    {"type", getType()},
                    {"initialized time", initializedTime.toString()},
                    {"count", String.valueOf(size())}
            }));
            return table;
        }
    }





}