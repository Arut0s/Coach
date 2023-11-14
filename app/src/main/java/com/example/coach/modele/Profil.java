package com.example.coach.modele;


import java.io.Serializable;
import java.util.Date;

/**
 * Classe métier Profil : contient les informations du profil
 */
public class Profil implements Serializable {

    //constantes
    private static final Integer minFemme = 15; //maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus
    private int poids;
    private int taille;
    private int age;
    private int sexe;
    private float img = 0;
    private String message = "";
    private Date dateMesure;

    /**
     * Constructeur qui valorise directement les propriétés poids, taille, age, sexe
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour un homme, 0 pour une femme
     */
    public Profil(Date dateMesure, int poids, int taille, int age, int sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getSexe() {
        return sexe;
    }

    public Date getDateMesure(){
        return dateMesure;
    }

    /**
     * Retourne l'img après l'avoir calculé si il est vide
     * @return img
     */
    public float getImg() {

        if(img == 0){
            float tailleCm= ((float)taille)/100; // taille en mètres mais que l'on saisira en cm pour éviter la saisie de la virgule
            img = (float)((1.2*poids/(tailleCm*tailleCm))+(0.23*age)-(10.83*sexe)-5.4); // sexe = 0 pour une femme et sexe = 1 pour un homme
        }
        return img;
    }

    /**
     * Retourne le message correspondant à l'img
     * @return message "normal", "trop faible", "trop élevé"
     */
    public String getMessage() {
        if(message.equals("")) {
            message = "normal";
            Integer min = minFemme, max = maxFemme;
            if (sexe == 1) {
                min = minHomme;
                max = maxHomme;
            }
            img = getImg();
            if (img < min) {
                message = "trop faible";
            } else {
                if (img > max) {
                    message = "trop élevé";
                }
            }
        }
        return message;
    }

}
