package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;
import com.example.coach.outils.MesOutils;

/**
 * Classe d'affichage (activity) CalculActivity
 * Permet le calcul d'un IMG
 */
public class CalculActivity extends AppCompatActivity {

    private Controle controle;
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }

    /**
     * Initialisation à l'ouverture :
     * - récupération des objets graphiques
     * - création du controleur
     */
    private void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        controle = Controle.getInstance();
        ecouteCalcul();
       recupProfil();
    }

    /**
     * Permet de récupérer les informations sur le click du bouton calculer
     */
    private void ecouteCalcul(){

        btnCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btnCalc.getWindowToken(), 0);
                Integer poids = 0, taille = 0, age = 0, sexe = 0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch (Exception e) {}
                if(rdHomme.isChecked()){
                    sexe = 1;
                }
                if(poids == 0 || taille == 0 || age == 0){
                    Toast.makeText(CalculActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                }else{
                    affichResult(poids,taille,age,sexe);
                }
            }
        });
    }

    /**
     * Permet d'afficher l'img et l'image a partir des informations saisies
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    private void affichResult(int poids, int taille, int age, int sexe){
        controle.creerProfil(poids, taille, age, sexe);
        float img = controle.getImg();
        String message = controle.getMessage();
        switch(message){
            case "normal":
                imgSmiley.setImageResource(R.drawable.normal);
                lblIMG.setTextColor(Color.GREEN);
                break;
            case "trop faible":
                imgSmiley.setImageResource(R.drawable.maigre);
                lblIMG.setTextColor(Color.RED);
                break;
            case "trop élevé":
                imgSmiley.setImageResource(R.drawable.graisse);
                lblIMG.setTextColor(Color.RED);
                break;
        }
        lblIMG.setText(MesOutils.format2Decimal(img)+" : IMG "+message);
    }

    /**
     * Recupère les infos d'un profil et les affiche
     */
    public void recupProfil(){
       Intent intent = getIntent();
       Bundle bundle = intent.getExtras();
       if(bundle != null){
           Profil profil = (Profil) bundle.get("profil");

           txtPoids.setText(""+profil.getPoids());
           txtTaille.setText(""+profil.getTaille());
           txtAge.setText(""+profil.getAge());
           if(profil.getSexe() == 1){
               rdHomme.setChecked(true);
           }else{
               rdFemme.setChecked(true);
           }
       }
    }
}