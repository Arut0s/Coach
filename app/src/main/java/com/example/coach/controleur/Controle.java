package com.example.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;
import com.example.coach.vue.CalculActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {

    private static Controle instance = null;
   // private static Profil profil;
    private static String nomFic = "saveprofil";
    //private AccesLocal accesLocal;
    private static AccesDistant accesDistant;
    private static Context context;
    private ArrayList<Profil> lesProfils = new ArrayList<Profil>();

    private Controle(){
        super();
        }
        //recupSerialize(context);
        //accesLocal = AccesLocal.getInstance(context);
        //profil = accesLocal.recupDernier();


    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(){
        if(instance==null){
            Controle.instance = new Controle();
            accesDistant = AccesDistant.getInstance();
            accesDistant.envoi("tous", new JSONObject());
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
       Profil profil = new Profil(new Date(), poids,taille,age,sexe);
        lesProfils.add(profil);
        //accesLocal.ajout(profil);
        //Serializer.serialize(nomFic,profil,context);
        accesDistant.envoi("enreg", profil.convertToJSONObject());
    }

    public void delProfil(Profil profil){
        accesDistant.envoi("suppr", profil.convertToJSONObject());
        Log.d("profil", "************** profil json = "+profil.convertToJSONObject());
        lesProfils.remove(profil);
    }



    /**
     * getter sur le résultat du calcul de l'img du dernier profil
     * @return l'img du profil
     */
    public float getImg(){
        if(lesProfils.size()>0){
            return lesProfils.get(lesProfils.size()-1).getImg();
        }
        else
        {
            return 0;
        }
    }

    /**
     * getter sur le message correspondant a l'img du dernier profil
     * @return message du profil
     */
    public String getMessage(){
        if(lesProfils.size()>0){
            return lesProfils.get(lesProfils.size()-1).getMessage();
        }
        else
        {
            return "";
        }
    }
    public ArrayList<Profil> getLesProfils(){

        return this.lesProfils;
    }

    public void setLesProfils(ArrayList<Profil> lesProfils){

        this.lesProfils = lesProfils;
    }
}
