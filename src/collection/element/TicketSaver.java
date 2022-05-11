package collection.element;

import java.util.Date;

public class TicketSaver {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float price; //Поле может быть null, Значение поля должно быть больше 0
    private Double discount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100
    private Boolean refundable; //Поле не может быть null
    private TicketType type; //Поле не может быть null
    private Person person; //Поле не может быть null
    public TicketSaver(Ticket ticket){
        id = ticket.getId();
        name = ticket.getName();
        coordinates = ticket.getCoordinates();
        creationDate = ticket.getCreationDate();
        price = ticket.getPrice();
        discount = ticket.getDiscount();
        refundable = ticket.getRefundable();
        type = ticket.getType();
        person = ticket.getPerson();
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
