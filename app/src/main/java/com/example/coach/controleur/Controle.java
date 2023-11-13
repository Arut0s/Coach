package com.example.coach.controleur;

import com.example.coach.modele.Profil;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {

    private static Controle instance = null;
    private static Profil profil;

    private Controle(){
        super();
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(){
        if(instance==null){
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     *
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(int poids, int taille, int age, int sexe){
        profil = new Profil(poids,taille,age,sexe);
    }

    /**
     * getter sur le résultat du calcul de l'img du profil
     * @return l'img du profil
     */
    public float getImg(){
        if(profil != null){
            return profil.getImg();
        }
        else
        {
            return 0;
        }
    }

    /**
     * getter sur le message correspondant a l'img du profil
     * @return message du profil
     */
    public String getMessage(){
        if(profil != null){
            return profil.getMessage();
        }
        else
        {
            return "";
        }
    }
}
