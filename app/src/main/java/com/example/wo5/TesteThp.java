package com.example.wo5;

public class TesteThp {
    private CellIdentity cellIdentity;
    private CellSignal cellSignal;
    private DateTime dateTime;
    private Location location;
    private Double thpDL;
    private Double thpUL;
    private Measurement measurement;
    //Adicionar usuário


    public TesteThp(CellIdentity cellIdentity, CellSignal cellSignal, DateTime dateTime, Location location, Double thpDL, Double thpUL) {
        this.cellIdentity = cellIdentity;
        this.cellSignal = cellSignal;
        this.dateTime = dateTime;
        this.location = location;
        this.thpDL = thpDL;
        this.thpUL = thpUL;
    }

    public TesteThp(Double thpDL, Double thpUL, Measurement measurement) {
        this.thpDL = thpDL;
        this.thpUL = thpUL;
        this.measurement = measurement;
    }

    public CellIdentity getCellIdentity() {
        return cellIdentity;
    }

    public void setCellIdentity(CellIdentity cellIdentity) {
        this.cellIdentity = cellIdentity;
    }

    public CellSignal getCellSignal() {
        return cellSignal;
    }

    public void setCellSignal(CellSignal cellSignal) {
        this.cellSignal = cellSignal;
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

    public Double getThpDL() {
        return thpDL;
    }

    public void setThpDL(Double thpDL) {
        this.thpDL = thpDL;
    }

    public Double getThpUL() {
        return thpUL;
    }

    public void setThpUL(Double thpUL) {
        this.thpUL = thpUL;
    }
}
