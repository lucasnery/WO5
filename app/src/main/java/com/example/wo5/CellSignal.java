package com.example.wo5;

public class CellSignal {

    private int rsrp;
    private int rsrq;
    private int snr;
    private int ta;
    private int asu;
    private int cqi;
    private int dbm;
    private int level;
    private int rssi;

    public CellSignal(int rsrp, int rsrq, int snr, int ta, int asu, int cqi, int dbm, int level, int rssi) {
        this.rsrp = rsrp;
        this.rsrq = rsrq;
        this.snr = snr;
        this.ta = ta;
        this.asu = asu;
        this.cqi = cqi;
        this.dbm = dbm;
        this.level = level;
        this.rssi = rssi;
    }



    public int getRsrp() {
        return rsrp;
    }

    public void setRsrp(int rsrp) {
        this.rsrp = rsrp;
    }

    public int getRsrq() {
        return rsrq;
    }

    public void setRsrq(int rsrq) {
        this.rsrq = rsrq;
    }

    public int getSnr() {
        return snr;
    }

    public void setSnr(int snr) {
        this.snr = snr;
    }

    public int getTa() {
        return ta;
    }

    public void setTa(int ta) {
        this.ta = ta;
    }

    public int getAsu() {
        return asu;
    }

    public void setAsu(int asu) {
        this.asu = asu;
    }

    public int getCqi() {
        return cqi;
    }

    public void setCqi(int cqi) {
        this.cqi = cqi;
    }

    public int getDbm() {
        return dbm;
    }

    public void setDbm(int dbm) {
        this.dbm = dbm;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }


}
