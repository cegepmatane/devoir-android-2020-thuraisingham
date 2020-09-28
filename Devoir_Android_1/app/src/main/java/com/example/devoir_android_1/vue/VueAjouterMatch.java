package com.example.devoir_android_1.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devoir_android_1.R;
import com.example.devoir_android_1.donnee.MatchDAO;
import com.example.devoir_android_1.modele.Match;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class VueAjouterMatch extends AppCompatActivity {

    private TextView laDate;
    private Button vueAjouterMatchBouttonDate;
    private Calendar c;
    private DatePickerDialog dpd;


    protected EditText vueAjouterMatchChampRencontre;
    protected EditText vueAjouterMatchChampDate;
    private MatchDAO matchDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_match);

        laDate = (TextView) findViewById (R.id.vueAjouterMatchChampDate);
        vueAjouterMatchBouttonDate = findViewById (R.id.vueAjouterMatchBouttonDate);

        vueAjouterMatchBouttonDate.setOnClickListener(new View.OnClickListener()
        {
           public void onClick(View view){


               Calendar c = Calendar.getInstance();
               int day = c.get(Calendar.DAY_OF_MONTH);
               int month = c.get(Calendar.MONTH);
               int year = c.get(Calendar.YEAR);

               dpd = new DatePickerDialog(
                       VueAjouterMatch.this,
                       new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                       laDate.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                   }

               }, day, month, year);

               dpd.show();
           }
        });


        Button vueAjouterMatchActionAnnuler = (Button) findViewById(R.id.vueAjouterMatchActionAnnuler);

        vueAjouterMatchActionAnnuler.setOnClickListener(

                new View.OnClickListener() {
                    public void onClick(View arg0) {
                        //TODO : coder!
                        int alloo = 1;
                       /* Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Action annuler",
                                Toast.LENGTH_SHORT);

                        message.show();*/
                        naviguerRetourCalendrier();


                    }
                }
        );

        vueAjouterMatchChampRencontre = (EditText) findViewById(R.id.vueAjouterMatchChampRencontre);
        vueAjouterMatchChampDate = (EditText) findViewById(R.id.vueAjouterMatchChampDate);


        Button vueAjouterMatchActionAjouter = (Button) findViewById(R.id.vueAjouterMatchActionAjouter);

        vueAjouterMatchActionAjouter.setOnClickListener(

                new View.OnClickListener() {
                    public void onClick(View arg0) {
                        //TODO : coder!
                        //int allo = 0;

                       /*  Toast message = Toast.makeText(
                              getApplicationContext(),
                                "Champ Rencontre: " + vueAjouterMatchChampRencontre.getText().toString() +
                                        " Champ Date: " + vueAjouterMatchChampDate.getText().toString(),
                                Toast.LENGTH_LONG);

                        message.show();*/
                        //naviguerRetourCalendrier();

                        enregistrementMatch();
                        naviguerRetourCalendrier();


                    }
                }
        );

    }

   private void enregistrementMatch()
    {
        /*HashMap<String,String> match;

        match = new HashMap<String, String>();
        match.put("rencontre", vueAjouterMatchChampRencontre.getText().toString());
        match.put("date", vueAjouterMatchChampDate.getText().toString());

        matchDAO = MatchDAO.getInstance();
        matchDAO.ajouterMatch(match);*/



        Match match = new Match(vueAjouterMatchChampRencontre.getText().toString(), vueAjouterMatchChampDate.getText().toString(), 0);
        matchDAO = new MatchDAO().getInstance();

        matchDAO.ajouterMatch(match);
    }

    public void naviguerRetourCalendrier()
    {
        this.finish();
    }

}
