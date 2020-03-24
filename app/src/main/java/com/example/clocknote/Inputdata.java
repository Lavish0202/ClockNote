package com.example.clocknote;

public class Inputdata {
    private String evname,totime,fromtime,date,evid;

    public Inputdata(){

    }

    public Inputdata(String evname, String totime, String fromtime, String date, String evid) {
        this.evname = evname;
        this.totime = totime;
        this.fromtime = fromtime;
        this.date = date;
        this.evid = evid;
    }

    public String getEvname() {
        return evname;
    }

    public String getTotime() {
        return totime;
    }

    public String getFromtime() {
        return fromtime;
    }

    public String getDate() {
        return date;
    }

    public String getEvid() {
        return evid;
    }
}
