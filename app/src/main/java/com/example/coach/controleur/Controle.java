package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {

    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";

    private Controle(Context context){
        recupSerialize(context);
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(Context context){
        if(instance==null){
            Controle.instance = new Controle(context);
        }
        return Controle.instance;
    }

    /**
     * Permet de créer un profil avec le poids, la taille, l'age et le sexe passé en paramètre
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(int poids, int taille, int age, int sexe, Context context){
        profil = new Profil(poids,taille,age,sexe);
        Serializer.serialize(nomFic,profil,context);
    }

    /**
     * Permet de récupérer un profil par sérialisation
     */
    private static void recupSerialize(Context context){
        profil = (Profil)Serializer.deSerialize(nomFic,context);
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

    /**
     * Retourne le poids si le profil existe
     * @return
     */
    public Integer getPoids() {
        if (profil == null) {
            return null;
        } else {
            return profil.getPoids();
        }
    }
        /**
         * Retourne la taille si le profil existe
         */
        public Integer getTaille(){
            if(profil == null){
                return null;
            }else{
                return profil.getTaille();
            }
        }
    /**
     * Retourne l'age si le profil existe
     */
    public Integer getAge(){
        if(profil == null){
            return null;
        }else{
            return profil.getAge();
        }
    }
    /**
     * Retourne le sexe si le profil existe
     */
    public Integer getSexe(){
        if(profil == null){
            return null;
        }else{
            return profil.getSexe();
        }
    }
}
