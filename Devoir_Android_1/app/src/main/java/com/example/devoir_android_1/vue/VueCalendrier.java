package com.example.devoir_android_1.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.devoir_android_1.R;
import com.example.devoir_android_1.donnee.BaseDeDonees;
import com.example.devoir_android_1.donnee.MatchDAO;
import com.example.devoir_android_1.modele.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VueCalendrier extends AppCompatActivity {

    protected ListView vueCalendrierListeMatch;
    //protected List<HashMap<String, String>> listeMatch;
    protected List<Match> listeMatch;

    protected MatchDAO matchDAO;

    protected Intent intentionNaviguerAjouterMatch;
    protected Intent intentionNaviguerModifierMatch;

    static final public int ACTIVITE_AJOUTER_MATCH = 1;
    static final public int ACTIVITE_MODIFIER_MATCH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_calendrier);

        vueCalendrierListeMatch =(ListView)findViewById(R.id.vueCalendrierListeMatch);

        BaseDeDonees.getInstance(getApplicationContext());

        matchDAO = MatchDAO.getInstance();
       /* listeMatch = matchDAO.listerMatch();

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                listeMatch,
                android.R.layout.two_line_list_item,
                new String[]{"rencontre", "date"},
                new int[]{android.R.id.text1, android.R.id.text2});

        vueCalendrierListeMatch.setAdapter(adapter);*/

        afficherListeMatch();

        Button vueCalendrierActionAjouterMatch = (Button)findViewById(R.id.vueCalendrierActionAjouterMatch);

        intentionNaviguerAjouterMatch = new Intent(this, VueAjouterMatch.class);

        vueCalendrierActionAjouterMatch.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0){
                        //TODO : coder!
                        /*Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Action ajouter match",
                                Toast.LENGTH_SHORT);

                        message.show();*/

                       // startActivity(intentionNaviguerAjouterMatch);
                        startActivityForResult(intentionNaviguerAjouterMatch,ACTIVITE_AJOUTER_MATCH);

                    }
                }
        );

        intentionNaviguerModifierMatch = new Intent(this, VueModifierMatch.class);

        vueCalendrierListeMatch.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View vue,
                                            int positionDansAdapteur, long positionItem) {

                        ListView vueListeMatch = (ListView)vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String,String> match=
                                (HashMap<String,String>)
                                        vueListeMatch.getItemAtPosition((int)positionItem);

                       /* Toast message = Toast.makeText(getApplicationContext(),
                                 "Position " + positionItem + " rencontre" + match.get("rencontre"),
                                Toast.LENGTH_SHORT);
                        message.show();*/

                        //startActivity(intentionNaviguerModifierMatch);
                        intentionNaviguerModifierMatch.putExtra("id", match.get("id"));
                        startActivityForResult(intentionNaviguerModifierMatch, ACTIVITE_MODIFIER_MATCH);

                    }
                }
        );

    }

    protected void onActivityResult(int activite, int resultat, Intent donnees)
    {
        super.onActivityResult(activite, resultat, donnees);
        switch(activite)
        {
            case ACTIVITE_AJOUTER_MATCH:
                afficherListeMatch();
                break;
            case ACTIVITE_MODIFIER_MATCH:
                afficherListeMatch();
                break;
        }
    }

    public void afficherListeMatch(){
        //listeMatch = new MatchDAO().listerMatch();
        listeMatch = matchDAO.listerMatch();

        List<HashMap<String, String>> listeMatchPourAfficher = new ArrayList<HashMap<String, String>>();

        for(Match match:listeMatch)
        {
            listeMatchPourAfficher.add(match.obtenirMatchPourAfficher());
        }

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeMatchPourAfficher,
                android.R.layout.two_line_list_item,
                new String[] {"rencontre","date"},
                new int[] {android.R.id.text1,android.R.id.text2}
        );
        vueCalendrierListeMatch.setAdapter(adapteur);
    }

   /* public List<HashMap<String, String>> preparerListeMatch()
    {
        List<HashMap<String, String>> listeMatch = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> match;

        match = new HashMap<String, String>();
        match.put("rencontre"," Lyon (OL) contre Paris (PSG)");
        match.put("date","01/09/2020");
        listeMatch.add(match);

        match = new HashMap<String, String>();
        match.put("rencontre"," Marseille (OM) contre Lille (LOSC)");
        match.put("date","05/09/2020");
        listeMatch.add(match);

        match = new HashMap<String, String>();
        match.put("rencontre","  Barcelone(FC) contre Real Madrid (CF)");
        match.put("date","10/09/2020");
        listeMatch.add(match);

        return listeMatch;
    }*/


}