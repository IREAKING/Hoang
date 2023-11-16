package com.example.aphelios;

public class Student {
    private int id;
    private String hinhanh;
    private String ten;
    private  String quequan;
    private int tuoi;

    public Student(int id, String hinhanh, String ten, String quequan, int tuoi) {
        this.id = id;
        this.hinhanh = hinhanh;
        this.ten = ten;
        this.quequan = quequan;
        this.tuoi = tuoi;
    }

    public int getId() {
        return id;
    }
    public String getHinhanh() {
        return hinhanh;
    }
    public String getTen() {
        return ten;
    }
    public  String getQuequan(){
        return  quequan;
    }
    public int getTuoi() {
        return tuoi;
    }
}
