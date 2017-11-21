package com.ziviello.MyListView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by aesys on 13/11/17.
 */

public class SceltaActivity extends AppCompatActivity {

    Globals g = Globals.getInstance();
    EditText nome;
    EditText desc;
    TextView titolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta);

        Button buttonConferma = findViewById(R.id.buttonConferma);
        buttonConferma.setOnClickListener(Conferma);

        nome = findViewById(R.id.editTextNome);
        desc = findViewById(R.id.editTextDesc);
        titolo = findViewById(R.id.textViewTitolo);

        if(g.getK()==-1)
        // Se la variabile è a -1 vuol dire che stiamo creando una nuova riga
        // Le caselle di testo saranno quindi vuote
        {
            nome.setText("");
            desc.setText("");
            titolo.setText("CREA NUOVO ELEMENTO");
            buttonConferma.setText("CONFERMA");
        }
        else
        // Nel caso in cui la variabile sia diversa da -1 vuol dire che gli stiamo passando una riga già esistente
        // che noi vogliamo modificare; quindi nei nostri editText troveremo già scritti i dati della riga
            {
                nome.setText(String.valueOf(g.list.get(g.getK())[0]));
                desc.setText(String.valueOf(g.list.get(g.getK())[1]));
                titolo.setText("MODIFICA ELEMENTO");
                buttonConferma.setText("SALVA MODIFICHE");
            }


    }

    final View.OnClickListener Conferma = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(g.getK()==-1)
            {
                if (!nome.getText().toString().equals("")) {
                    Intent openHome = new Intent(SceltaActivity.this, HomeActivity.class);
                    startActivity(openHome);
                    g.STRING = new String[]{nome.getText().toString(), desc.getText().toString().trim()};
                    g.list.add(g.STRING);
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(100);
                } else {
                    Toast.makeText(getApplicationContext(), "NON HAI INSERITO NIENTE.", Toast.LENGTH_LONG).show();
                }
            }
            else
                {
                    if (!nome.getText().toString().equals("")) {
                        Intent openHome = new Intent(SceltaActivity.this, HomeActivity.class);
                        startActivity(openHome);
                        g.STRING = new String[]{nome.getText().toString(), desc.getText().toString().trim()};
                        g.list.set(g.getK(), g.STRING);
                        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vib.vibrate(100);
                    } else {
                        Toast.makeText(getApplicationContext(), "NON HAI INSERITO NIENTE.", Toast.LENGTH_LONG).show();
                    }
                }
        }
    };
}