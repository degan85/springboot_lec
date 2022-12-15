package com.degan.degan2.dto;

public class SampleDTO {
    private int sno;
    private String first;
    private String last;


    public int getSno() {
        return this.sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return this.last;
    }

    public void setLast(String last) {
        this.last = last;
    }


    @Override
    public String toString() {
        return "{" +
            " sno='" + getSno() + "'" +
            ", first='" + getFirst() + "'" +
            ", last='" + getLast() + "'" +
            "}";
    }


}
