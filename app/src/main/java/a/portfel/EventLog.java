package a.portfel;

import java.util.Date;

/**
 * Created by Krzysztof on 2017-05-23.
 */

public class EventLog {
        private String tytulem;
        private String data;
        private double kwota;

    public EventLog(){

    }

    public EventLog(double kwota, String data, String tytulem) {
        this.kwota = kwota;
        this.data = data;
        this.tytulem = tytulem;
    }

    public double getKwota() {
        return kwota;
    }

    public String getData() {
        return data;
    }

    public String getTytulem() {
        return tytulem;
    }
}
