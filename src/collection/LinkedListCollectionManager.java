package collection;

import collection.element.Ticket;
import collection.exceptions.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

    abstract public class LinkedListCollectionManager<T extends CollectionItem> extends AbstractCollectionManager<T> {

        private final LinkedList<T> collection;
        private final static Gson converter = new Gson();
        public LinkedListCollectionManager() {
            this.collection = new LinkedList<T>();
        }

        @Override
        public void add(T element) {
            if (collection.stream().anyMatch(iter -> iter.getId().equals(element.getId()))) element.setId(generateId());
            for (String iter : element.getUniqueValueNameList()) {
                if (checkIfUnique(iter, element.getValue(iter)))
                    throw new UniqueParamException(iter, element.getValue(iter));
            }
            collection.push(element);
        }
        @Override
        public void parse(ArrayList<? extends CollectionItem> collection){
            for (CollectionItem ticket : collection) {
                add((T) ticket);
            }
        }
        @Override
        protected Collection<T> getCollection() {
            return collection;
        }

        @Override
        public void reverse() {
            Collections.reverse(collection);
        }

        @Override
        public void sort() {
            Collections.sort(collection);
        }
        @Override
        public void removeHead(){
            collection.removeFirst();
        }
        @Override
        public T getHead(){
            return collection.peekFirst();
        }

        @Override
        public LinkedListCollectionInfoData getCollectionInfoTable() {
            return new LinkedListCollectionInfoData();
        }


        public class LinkedListCollectionInfoData extends CollectionInfoTable {

            @Override
            public List<String> getTitles() {
                List<String> titles = new ArrayList<>(super.getTitles());
                titles.add("top of the stack");
                return titles;
            }

            @Override
            public List<Map<String, String>> getTable() {
                List<Map<String, String>> table = super.getTable();
                table.get(0).put("top of the stack", (size() != 0) ? "id: " + collection.peek().getId().toString() : "null");
                return table;
            }


            @Override
            protected String getType() {
                return "LinkedList";
            }
        }

    }
