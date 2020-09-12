package com.example.wo5;

public class Measurement {

    private CellSignal cellSignal;
    private CellIdentity cellIdentity;
    private DateTime dateTime;
    private Location location;

    public Measurement(CellSignal cellSignal, CellIdentity cellIdentity, DateTime dateTime, Location location) {
        this.cellSignal = cellSignal;
        this.cellIdentity = cellIdentity;
        this.dateTime = dateTime;
        this.location = location;

    }
    public Measurement(){

    }


    public CellSignal getCellSignal() {
        return cellSignal;
    }

    public void setCellSignal(CellSignal cellSignal) {
        this.cellSignal = cellSignal;
    }

    public CellIdentity getCellIdentity() {
        return cellIdentity;
    }

    public void setCellIdentity(CellIdentity cellIdentity) {
        this.cellIdentity = cellIdentity;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
