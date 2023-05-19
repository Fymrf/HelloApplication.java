package v.visual;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lesson {
    private StringProperty name;
    private StringProperty descr;

    public Lesson(String name, String descr)
    {
        this.name = new SimpleStringProperty(this, "name",name);
        this.descr = new SimpleStringProperty(this, "descr",descr);
    }

    public void setName(String value)
    {
        name.set(value);
    }
    public  String getName()
    {
        return  name.get();
    }
    public StringProperty nameProperty()
    {
        return  name;
    }

    public void setDescr(String value)
    {
        descr.set(value);
    }
    public  String getDescr()
    {
        return  descr.get();
    }
    public StringProperty descrProperty()
    {
        return  descr;
    }

    @Override
    public  String toString(){
        return "Lesson [name="+name.get()+", descr =" +descr.get()+"]";
    }
}
