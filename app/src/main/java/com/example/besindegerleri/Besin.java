package com.example.besindegerleri;
import static android.R.attr.name;

public class Besin  {

    int id;
    String Besin;
    Double Kalori;
    Double Protein;
    Double Yag;
    Double Karbon;

    public Besin() {
        super();
    }


    public Besin(int i, String Besin, double Kalori, double Protein, double Yag, double Karbon) {
        super();
        this.id = i;
        this.Besin = Besin;
        this.Kalori=Kalori;
        this.Protein=Protein;
        this.Yag=Yag;
        this.Karbon=Karbon;
    }

    // constructor
    public Besin(String Besin, Double Kalori, Double Protein, Double Yag, Double Karbon){
        this.Besin = Besin;
        this.Kalori=Kalori;
        this.Protein=Protein;
        this.Yag=Yag;
        this.Karbon=Karbon;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBesin() { return Besin; }
    public void setBesin(String Besin) { this.Besin = Besin; }


    public Double getKalori() { return Kalori; }
    public void setKalori(Double Kalori) { this.Kalori = Kalori; }


    public Double getProtein() { return Protein;  }
    public void setProtein(Double Protein) { this.Protein = Protein;}

    public Double getYag() {  return Yag; }
    public void setYag(Double Yag) { this.Yag = Yag;}


    public Double getKarbon() { return Karbon;}
    public void setKarbon(Double Karbon) { this.Karbon = Karbon; }



}