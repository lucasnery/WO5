package com.example.wo5;

public class CellIdentity {

    private int cellPci;
    private int ci;
    private double enobebId;
    private int tac;
    private int earfcn;
    private String operatorLong;
    private String operatorAlphaShort;
    private String mcc;
    private String mnc;
    private String detail;

    public CellIdentity(int cellPci, int ci, double enobebId, int tac, int earfcn,
                        String operatorLong, String operatorAlphaShort, String mcc, String mnc, String detail) {
        this.cellPci = cellPci;
        this.ci = ci;
        this.enobebId = enobebId;
        this.tac = tac;
        this.earfcn = earfcn;
        this.operatorLong = operatorLong;
        this.operatorAlphaShort = operatorAlphaShort;
        this.mcc = mcc;
        this.mnc = mnc;
        this.detail = detail;
    }

    public int getCellPci() {
        return cellPci;
    }

    public void setCellPci(int cellPci) {
        this.cellPci = cellPci;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public double getEnobebId() {
        return enobebId;
    }

    public void setEnobebId(double enobebId) {
        this.enobebId = enobebId;
    }

    public int getTac() {
        return tac;
    }

    public void setTac(int tac) {
        this.tac = tac;
    }

    public int getEarfcn() {
        return earfcn;
    }

    public void setEarfcn(int earfcn) {
        this.earfcn = earfcn;
    }

    public String getOperatorLong() {
        return operatorLong;
    }

    public void setOperatorLong(String operatorLong) {
        this.operatorLong = operatorLong;
    }

    public String getOperatorAlphaShort() {
        return operatorAlphaShort;
    }

    public void setOperatorAlphaShort(String operatorAlphaShort) {
        this.operatorAlphaShort = operatorAlphaShort;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


}
