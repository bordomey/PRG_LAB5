package collection.element;

import collection.CollectionItem;
import collection.CollectionItemAdapter;
import collection.StringTable;
import collection.exceptions.InvalidDataFormatException;
import collection.exceptions.NullDataException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public class Ticket extends CollectionItemAdapter {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float price; //Поле может быть null, Значение поля должно быть больше 0
    private Double discount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100
    private Boolean refundable; //Поле не может быть null
    private TicketType type; //Поле не может быть null
    private Person person; //Поле не может быть null
    {
        coordinates = new Coordinates();
        person = new Person();
        creationDate = Date.from(Instant.now());
    }

    public Ticket(Long id) {
        this.id = id;
        bannedSettersNameList.add("creationDate");

        settersMap.put("name", this::setName);
        settersMap.put("x", (str) -> this.getCoordinates().setX(Double.valueOf(str)));
        settersMap.put("y", (str) -> this.getCoordinates().setY(Double.valueOf(str)));
        settersMap.put("creationDate", (str) -> this.setCreationDate(Date.from(Instant.parse(str))));
        settersMap.put("price", (str) -> this.setPrice(Float.valueOf(str)));
        settersMap.put("discount", (str) -> this.setDiscount(Double.valueOf(str)));
        settersMap.put("refundable", (str) -> this.setRefundable((str.equals(CollectionItem.nullInputString)) ? null : Boolean.valueOf(str)));
        settersMap.put("type", (str) -> this.setType((str.equals(CollectionItem.nullInputString)) ? null : TicketType.valueOf(str)));
        settersMap.put("passportID", (str) -> this.getPerson().setPassportID(str));
        settersMap.put("hairColor", (str) -> this.getPerson().setHairColor(Color.valueOf(str)));

        gettersMap.put("name", this::getName);
        gettersMap.put("x", () -> this.getCoordinates().getX().toString());
        gettersMap.put("y", () -> this.getCoordinates().getY().toString());
        gettersMap.put("creationDate", () -> this.getCreationDate().toString());
        gettersMap.put("price", () -> this.getPrice().toString());
        gettersMap.put("discount", () -> this.getDiscount().toString());
        gettersMap.put("refundable", () -> this.getRefundable().toString());
        gettersMap.put("type", () -> this.getType().toString());
        gettersMap.put("passportID", () -> this.getPerson().getPassportID());
        gettersMap.put("hairColor", () -> this.getPerson().getHairColor().toString());
    }
    public Ticket(TicketSaver ticketSaver){
        this(ticketSaver.getId());
        this.setName(ticketSaver.getName());
        this.setCoordinates(ticketSaver.getCoordinates());
        this.setCreationDate(ticketSaver.getCreationDate());
        this.setPrice(ticketSaver.getPrice());
        this.setDiscount(ticketSaver.getDiscount());
        this.setRefundable(ticketSaver.getRefundable());
        this.setType(ticketSaver.getType());
        this.setPerson(ticketSaver.getPerson());

    }

    @Override
    public Map<String, String> getFormatMap() {
        return StringTable.stringMatrixToMap(new String[][] {
                {"name", "not empty"},
                {"x", "not null, greater than -659"},
                {"y", "not null"},
                {"price", "greater than 0"},
                {"discount", "greater than 0, less than 100"},
                {"refundable", "TRUE or FALSE"},
                {"type", "VIP, USUAL, BUDGETARY, CHEAP"},
                {"passportID", "length less or equals 20"},
                {"hairColor", "GREEN, BLACK, BLUE"},
        });
    }

    public int compareTo(Ticket o){
        return getPrice().compareTo(o.getPrice());
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setName(String name) {
        if((name.length() != 0)&&(name != null))
            this.name = name;
        else throw new NullDataException();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person.setHairColor(person.getHairColor());
        this.person.setPassportID(person.getPassportID());
    }

    public void setDiscount(Double discount) {
        if((discount > 0)&&(discount < 100))
        this.discount = discount;
        else
            throw new InvalidDataFormatException();
    }

    public void setPrice(Float price) {
        if(price>0) {
            this.price = price;
        }
        else
            throw new InvalidDataFormatException();
    }

    public void setRefundable(Boolean refundable) {
        if(refundable != null)
        this.refundable = refundable;
        else throw new NullDataException();
    }

    public void setType(TicketType type) {
        if(type != null)
            this.type = type;
        else throw new NullDataException();
    }

    @Override
    public void setId(Long id) {

    }

    @Override
    public void setValue(String valueName, String value) {
        super.setValue(valueName, value);
    }

    public String getName() {
        return name;
    }

    public Boolean getRefundable() {
        return refundable;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Double getDiscount() {
        return discount;
    }

    public Float getPrice() {
        return price;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public TicketType getType() {
        return type;
    }
}


