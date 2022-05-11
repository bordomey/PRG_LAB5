package collection.element;

import collection.exceptions.DataException;
import collection.exceptions.InvalidDataFormatException;
import collection.exceptions.NullDataException;

public class Person {
    private String passportID; //Длина строки не должна быть больше 20, Поле не может быть null
    private Color hairColor; //Поле может быть null

    public void setHairColor(Color hairColor) throws DataException {
        if(hairColor == null) throw new NullDataException();
        this.hairColor = hairColor;
    }
    public void setPassportID(String passportID) {
        if(passportID == null) throw new NullDataException();
        if(passportID.length() > 20) throw new InvalidDataFormatException("Length can't be longer than 20 symbols");
        this.passportID = passportID;
    }

    public long compareTo(Person o){
        return passportID.compareTo(o.passportID);
    }

    public Color getHairColor() {
        return hairColor;
    }

    public String getPassportID() {
        return passportID;
    }


}
