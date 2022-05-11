package collection.element;

import collection.exceptions.DataException;
import collection.exceptions.InvalidDataFormatException;
import collection.exceptions.NullDataException;


public class Coordinates {
    private double x; //Значение поля должно быть больше -659
    private double y;
    public Coordinates() {
    }

    public Coordinates(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(Double x) throws DataException {
        if(x==null) throw new NullDataException();
        if(x < -659) throw new InvalidDataFormatException();
        this.x = x;
    }

    public void setY(Double y) throws DataException {
        if(y==null) throw new NullDataException();
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}