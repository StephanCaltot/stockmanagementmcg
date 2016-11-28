package fr.univtln.mcg;

/**
 * Created by screetts on 24/11/16.
 */
public class Car {
    private String id ;
    private int annee;
    private int price ;
    private String couleur;
    private String marque ;


    public Car() {
    }

    public Car(String id, int annee, int price, String couleur, String marque) {
        this.id = id;
        this.annee = annee;
        this.price = price;
        this.couleur = couleur;
        this.marque = marque;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", annee=" + annee +
                ", price=" + price +
                ", couleur='" + couleur + '\'' +
                ", marque='" + marque + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (annee != car.annee) return false;
        if (price != car.price) return false;
        if (couleur != null ? !couleur.equals(car.couleur) : car.couleur != null) return false;
        return marque != null ? marque.equals(car.marque) : car.marque == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + annee;
        result = 31 * result + price;
        result = 31 * result + (couleur != null ? couleur.hashCode() : 0);
        result = 31 * result + (marque != null ? marque.hashCode() : 0);
        return result;
    }
}
