package v.visual;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NowProcedure {
    private StringProperty N;
    private StringProperty action;
    private StringProperty time;
    private StringProperty point;

    public NowProcedure(String N, String action,String time, String point)
    {
        this.N = new SimpleStringProperty(this, "№",N);
        this.action = new SimpleStringProperty(this, "action",action);
        this.time = new SimpleStringProperty(this, "time",time);
        this.point = new SimpleStringProperty(this, "point",point);
    }

    public void setN(String value)
    {
        N.set(value);
    }
    public  String getN()
    {
        return  N.get();
    }
    public StringProperty NProperty()
    {
        return  N;
    }

    public void setaction(String value)
    {
        action.set(value);
    }
    public  String getaction()
    {
        return  action.get();
    }
    public StringProperty actionProperty()
    {
        return  action;
    }

    public void settime(String value)
    {
        time.set(value);
    }
    public  String gettime()
    {
        return  time.get();
    }
    public StringProperty timeProperty()
    {
        return  time;
    }

    public void setpoint(String value)
    {
        point.set(value);
    }
    public  String getpoint()
    {
        return  point.get();
    }
    public StringProperty pointProperty()
    {
        return  point;
    }

    @Override
    public  String toString(){
        return "NowProcedure [N="+N.get()+", action =" +action.get()+", time =" +time.get()+", point =" +point.get()+"]";
    }
}
