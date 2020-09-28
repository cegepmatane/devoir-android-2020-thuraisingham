package com.example.devoir_android_1.donnee;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.devoir_android_1.modele.Match;
import com.example.devoir_android_1.vue.VueAjouterMatch;

import java.util.ArrayList;
import java.util.List;

public class MatchDAO
{
    private static MatchDAO instance = null;
    //private static List<HashMap<String, String>> listeMatch;
    public List<Match> listeMatch;

    private BaseDeDonees baseDeDonees;

    public MatchDAO(){

        this.baseDeDonees = BaseDeDonees.getInstance();

        //listeMatch = new ArrayList<HashMap<String, String>>();
        listeMatch = new ArrayList<Match>();
        //preparerListeMatch();
    }

    private void preparerListeMatch() {
        /*
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
        match.put("rencontre"," Barcelone(FC) contre Real Madrid (CF)");
        match.put("date","10/09/2020");
        listeMatch.add(match);*/

        listeMatch.add(new Match("Lyon (OL) contre Paris (PSG)", "01/09/2020", 0));
        listeMatch.add(new Match("Marseille (OM) contre Lille (LOSC)", "05/09/2020", 1));
        listeMatch.add(new Match("Barcelone(FC) contre Real Madrid (CF)", "10/09/2020", 2));

    }

    public static MatchDAO getInstance(){

        if(null == instance){
            instance = new MatchDAO();
        }
        return instance;
    }

    public List<Match> listerMatch()
    {
        String LISTER_MATCH= "SELECT * FROM matchSoccer";
        Cursor curseur = baseDeDonees.getReadableDatabase().rawQuery(LISTER_MATCH, null);
        this.listeMatch.clear();
        Match match;

        int indexId = curseur.getColumnIndex("id");
        int indexDate = curseur.getColumnIndex("date");
        int indexRencontre = curseur.getColumnIndex("rencontre");

        for(curseur.moveToFirst(); !curseur.isAfterLast();curseur.moveToNext())
        {
            int id = curseur.getInt(indexId);
            String date = curseur.getString(indexDate);
            String rencontre = curseur.getString(indexRencontre);
            match = new Match(rencontre, date, id);
            this.listeMatch.add(match);
        }

        return listeMatch;
    }

    /*public void ajouterMatch(HashMap<String, String> match){
        //listeMatch.add(match);

    }*/

    public void ajouterMatch(Match match)
    {
        SQLiteDatabase baseDeDonneeEcriture = baseDeDonees.getWritableDatabase();

        baseDeDonneeEcriture.beginTransaction();

        /*Toast message = Toast.makeText(
            new VueAjouterMatch().getApplicationContext(),
            "TEST",
            Toast.LENGTH_LONG);

        message.show();*/
        try
        {


            ContentValues matchEnCleValeur = new ContentValues();
            matchEnCleValeur.put("rencontre", match.getRencontre());
            matchEnCleValeur.put("date", match.getDate());

            baseDeDonneeEcriture.insertOrThrow("matchSoccer", null, matchEnCleValeur);
            baseDeDonneeEcriture.setTransactionSuccessful();
        }
        catch (Exception e)
        {

            Log.d("MatchDAO", "erreur en tentant d'ajouter un match dans la base de donnees");
        }

        finally
        {
            baseDeDonneeEcriture.endTransaction();
        }
    }

    public Match chercherMatchParId(int id)
    {
        listerMatch();
        for(Match matchRecherche : this.listeMatch)
        {
            if(matchRecherche.getId() == id) return matchRecherche;
        }

        return null;
    }

    public void modifierMatch(Match match){

        SQLiteDatabase baseDeDonneeEcriture = baseDeDonees.getWritableDatabase();

        baseDeDonneeEcriture.beginTransaction();
        try
        {
            ContentValues matchEnCleValeur = new ContentValues();
            matchEnCleValeur.put("rencontre", match.getRencontre());
            matchEnCleValeur.put("date", match.getDate());

            baseDeDonneeEcriture.update("matchSoccer", matchEnCleValeur,  "id = " + match.getId(), null);
            baseDeDonneeEcriture.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            Log.d("MatchDAO", "erreur en tentant de modifier un match dans la base de donnees");
        }

        finally
        {
            baseDeDonneeEcriture.endTransaction();
        }

    }

}
