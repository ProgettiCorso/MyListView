package com.ziviello.MyListView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Globals g = Globals.getInstance();
    ListView mylist;
    Context myContext;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        g.setK(-1);
        // Setto questa variabile globale a -1 in modo da poter effettuare un controllo nel momento in cui
        // vado nella pagina "activity_scelta.xml"("SceltaActivity.java").

        Button buttonScelta = findViewById(R.id.buttonAggiungi);
        buttonScelta.setOnClickListener(goToSceltaLayout);

        Button buttonSvuota = findViewById(R.id.buttonSvuota);
        buttonSvuota.setOnClickListener(Svuota);

        myContext = this;

        final ListView listView = findViewById(R.id.listView);
        final List<ElementoLista> list = new LinkedList<>();

        for(int i=0; i<g.list.size(); i++)
        {
            list.add(new ElementoLista(g.list.get(i)[0]));
        }
        adapter = new CustomAdapter(this, R.layout.lista_spesa, list);
        listView.setAdapter(adapter);


    }

//********************* FUNZIONI ************************************************************************************************************************

        final View.OnClickListener Svuota = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(g.list.size()>0) {
                    new AlertDialog.Builder(myContext)
                            .setMessage("SEI SICURO DI VOLER SVUOTARE LA TUA LISTA? ( Una volta svuotata non sarà possibile recuperare nulla )")
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    g.list = new ArrayList<>();
                                    mylist = findViewById(R.id.listView);
                                    mylist.setAdapter(null);
                                    Toast.makeText(myContext, "LA LISTA È STATA SVUOTATA.", Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("NO", null)
                            .show();
                }
                else
                {
                    Toast.makeText(myContext, "LA TUA LISTA È GIÀ VUOTA.",Toast.LENGTH_LONG).show();
                }
            }
        };

        final View.OnClickListener goToSceltaLayout = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent openScelta = new Intent(HomeActivity.this,SceltaActivity.class);
                startActivity(openScelta);

            }
        };

        public void OnClickRow(View view){

                Intent openVisualizza = new Intent(HomeActivity.this, VisualizzaActivity.class);
                startActivity(openVisualizza);

        }
//*****************************************************************************************************************************************************
}
