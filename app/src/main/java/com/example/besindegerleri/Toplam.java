package com.example.besindegerleri;

public class Toplam{

    int id1;
    String Besin1;
    Double Kalori1;
    Double Protein1;
    Double Yag1;
    Double Karbon1;

    public Toplam() {
        super();
    }


    public Toplam(int id1, String Besin1, double Kalori1, double Protein1, double Yag1, double Karbon1) {
        super();
        this.id1 = id1;
        this.Besin1 = Besin1;
        this.Kalori1=Kalori1;
        this.Protein1=Protein1;
        this.Yag1=Yag1;
        this.Karbon1=Karbon1;
    }

    // constructor
    public Toplam(String Besin1, Double Kalori1, Double Protein1, Double Yag1, Double Karbon1){
        this.Besin1 = Besin1;
        this.Kalori1=Kalori1;
        this.Protein1=Protein1;
        this.Yag1=Yag1;
        this.Karbon1=Karbon1;
    }
    public int getId1() { return id1; }
    public void setId1(int id1) { this.id1 = id1; }

    public String getBesin1() { return Besin1; }
    public void setBesin1(String Besin1) { this.Besin1 = Besin1; }


    public Double getKalori1() { return Kalori1; }
    public void setKalori1(Double Kalori1) { this.Kalori1 = Kalori1; }


    public Double getProtein1() { return Protein1;  }
    public void setProtein1(Double Protein1) { this.Protein1 = Protein1;}

    public Double getYag1() {  return Yag1; }
    public void setYag1(Double Yag1) { this.Yag1 = Yag1;}


    public Double getKarbon1() { return Karbon1;}
    public void setKarbon1(Double Karbon1) { this.Karbon1 = Karbon1; }



}