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

import com.example.devoir_android_1.R;
import com.example.devoir_android_1.donnee.MatchDAO;
import com.example.devoir_android_1.modele.Match;

import java.util.Calendar;

public class VueModifierMatch extends AppCompatActivity {

    private TextView laDate;
    private Button vueModifierMatchBouttonDate;
    private Calendar c;
    private DatePickerDialog dpd;

    protected EditText vueModifierMatchChampRencontre;
    protected EditText vueModifierMatchChampDate;

    private MatchDAO matchDAO;
    protected Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_match);

        laDate = (TextView) findViewById (R.id.vueModifierMatchChampDate);
        vueModifierMatchBouttonDate = findViewById (R.id.vueModifierMatchBouttonDate);

        vueModifierMatchBouttonDate.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view){


                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(
                        VueModifierMatch.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                                laDate.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                            }

                        }, day, month, year);

                dpd.show();
            }
        });


        Button vueModifierMatchActionAnnuler = (Button)findViewById(R.id.vueModifierMatchActionAnnuler);

        vueModifierMatchActionAnnuler.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0){
                        //TODO : coder!
                       /* Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Action annuler",
                                Toast.LENGTH_SHORT);

                        message.show();*/
                        naviguerRetourCalendrier();


                    }
                }
        );

        Bundle parametres = this.getIntent().getExtras();
        String idParametre = (String) parametres.get("id");
        int id = Integer.parseInt(idParametre);
        matchDAO = MatchDAO.getInstance();
        match = matchDAO.chercherMatchParId(id);

        vueModifierMatchChampRencontre = (EditText)findViewById(R.id.vueModifierMatchChampRencontre);
        vueModifierMatchChampDate = (EditText)findViewById(R.id.vueModifierMatchChampDate);
        vueModifierMatchChampRencontre.setText(match.getRencontre());
        vueModifierMatchChampDate.setText(match.getDate());

        Button vueModifierMatchActionModifier = (Button)findViewById(R.id.vueModifierMatchActionModifier);

        vueModifierMatchActionModifier.setOnClickListener(

                new View.OnClickListener() {
                    public void onClick(View view) {
                        //TODO : coder!

                       /*  Toast message = Toast.makeText(
                              getApplicationContext(),
                                "Champ Rencontre: " + vueAjouterMatchChampRencontre.getText().toString() +
                                        " Champ Date: " + vueAjouterMatchChampDate.getText().toString(),
                                Toast.LENGTH_LONG);

                        message.show();*/

                        enregistrementMatch();
                        naviguerRetourCalendrier();


                    }
                }
        );

    }
    private void enregistrementMatch()
    {
        match.setDate(vueModifierMatchChampDate.getText().toString());
        match.setRencontre(vueModifierMatchChampRencontre.getText().toString());


        match.setDate(vueModifierMatchChampDate.getText().toString());
        match.setRencontre(vueModifierMatchChampRencontre.getText().toString());
        //livre(vueModifierLivreChampTitre.getText().toString(), vueModifierLivreChampAuteur.getText().toString(), 0);
        matchDAO = matchDAO.getInstance();
        matchDAO.modifierMatch(match);

        System.out.println("le match modifier : " + match);
    }

    public void naviguerRetourCalendrier()
    {
        this.finish();
    }

}
