package com.example.coach.modele;

import android.content.Context;
import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;
import com.example.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class AccesDistant implements AsyncResponse {

    private static final String SERVERADDR = "http://192.168.88.26/coach/serveurcoach.php";
    private static AccesDistant instance;
    private Controle controle;

    private AccesDistant(){
        controle = Controle.getInstance();
    }

    public static AccesDistant getInstance(){
        if(instance == null){
            instance = new AccesDistant();
        }
        return instance;
    }

    @Override
    public void processFinish(String output) {
        Log.d("serveur", ""+output);
        try {
            ArrayList<Profil> lesProfils = new ArrayList<Profil>();
            JSONObject retour = new JSONObject(output);
            String code = retour.getString("code");
            String message = retour.getString("message");
            String result = retour.getString("result");
            if(!code.equals("200")){
                Log.d("erreur", "retour serveur code="+code+" result="+result);
            }else{
                if(message.equals("tous")){
                    JSONArray resultJson = new JSONArray(result);
                    for(int k=0; k<resultJson.length();k++){
                        JSONObject info = new JSONObject(resultJson.get(k).toString());
                        Date dateMesure = MesOutils.convertStringToDate(info.getString("datemesure"),"yyyy-MM-dd hh:mm:ss");
                        Integer poids = info.getInt("poids");
                        Integer taille = info.getInt("taille");
                        Integer age = info.getInt("age");
                        Integer sexe = info.getInt("sexe");
                        Profil profil = new Profil(dateMesure, poids, taille, age, sexe);
                        lesProfils.add(profil);
                    }
                    controle.setLesProfils(lesProfils);
                }
            }
        } catch (JSONException e) {
            Log.d("erreur", "output n'est pas au format JSON");
        }
    }

    public void envoi(String operation, JSONObject lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADDR);
    }
}
