package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;
import com.example.coach.vue.MainActivity;

import org.json.JSONObject;

import java.util.Date;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {

    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    //private AccesLocal accesLocal;
    private static AccesDistant accesDistant;
    private static Context context;

    private Controle(Context context){
        if(context != null){
            Controle.context = context;
        }
        //recupSerialize(context);
        //accesLocal = AccesLocal.getInstance(context);
        //profil = accesLocal.recupDernier();
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(Context context){
        if(instance==null){
            Controle.instance = new Controle(context);
            accesDistant = AccesDistant.getInstance();
            accesDistant.envoi("dernier", new JSONObject());
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
    public void creerProfil(int poids, int taille, int age, int sexe){
        profil = new Profil(new Date(), poids,taille,age,sexe);
        //accesLocal.ajout(profil);
        //Serializer.serialize(nomFic,profil,context);
        accesDistant.envoi("enreg", profil.convertToJSONObject());
    }

    /**
     * Permet de récupérer un profil par sérialisation
     */
    private static void recupSerialize(Context context){
        profil = (Profil)Serializer.deSerialize(nomFic,context);
    }

    public void setProfil(Profil profil){
        Controle.profil = profil;
        ((MainActivity)context).recupProfil();
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
