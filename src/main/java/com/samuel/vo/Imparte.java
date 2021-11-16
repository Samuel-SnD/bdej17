package com.samuel.vo;

public class Imparte {
    private String dniAl;
    private String dniProf;
    private int asig;
    private int curso;

    public String getDniAl() {
        return this.dniAl;
    }

    public void setDniAl(String dniAl) {
        this.dniAl = dniAl;
    }

    public String getDniProf() {
        return this.dniProf;
    }

    public void setDniProf(String dniProf) {
        this.dniProf = dniProf;
    }

    public int getAsig() {
        return this.asig;
    }

    public void setAsig(int asig) {
        this.asig = asig;
    }

    public int getCurso() {
        return this.curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public Imparte() {
    }

    public Imparte(String dniAl, String dniProf, int asig, int curso) {
        this.dniAl = dniAl;
        this.dniProf = dniProf;
        this.asig = asig;
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "dniAl='" + getDniAl() + "," +
            "dniProf='" + getDniProf() + "," +
            "asig='" + getAsig() + ",," +
            "curso='" + getCurso() + ",";
    }

}
