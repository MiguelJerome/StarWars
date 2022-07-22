package ca.collegelacite.starwars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // ListView <---> adaptateur <---> listeDePersonnages

    private ListView listView;



    private PersonnageAdapter adaptateur;
    // Source de données pour l'adaptateur du ListView
    private ArrayList<Personnage> listeDePersonnages;

    private static final String CLE_POSITION = "position";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate");


        // Données source de l'adaptateur
        listeDePersonnages = Personnage.lireFichier("personnages.json", this);

        adaptateur = new PersonnageAdapter(this, listeDePersonnages);

        listView = findViewById(R.id.listView);

        adaptateur = new PersonnageAdapter(this,listeDePersonnages);

        listView.setAdapter(adaptateur);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Toucher prolonger pour acceder au WIKI", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Personnage pers = listeDePersonnages.get(pos);
                affichePageWeb(pos);
                return true;
            }
        });


    }

        private void afficherPersonnage(int position)
        {
            Personnage personnage = listeDePersonnages.get(position);

            String nom = personnage.getNom();

            TextView tvNom = findViewById(R.id.nomtextView);

            tvNom.setText(nom);

            String descriptif = personnage.getDescriptif();
            TextView tvDescriptif = findViewById(R.id.descriptionTextView);
            tvDescriptif.setText(descriptif);

            ImageView imageView = findViewById(R.id.imageView);
            String urlStr = personnage.getImageUrl();
            Picasso.get().load(urlStr).into(imageView);
           // positionCourante = position;

        }

        private void affichePageWeb(int position)
        {
            Personnage personnage = listeDePersonnages.get(position);
            String urlStr = personnage.getWikiUrl();

            Uri webpage = Uri.parse(urlStr);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

            startActivity(intent);
        }




    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }



    // afficher le personnage dont la position de liste est fourni
    private void setSelectionCourante(int position)
    {
       // this.selectionCourante = position;
        Personnage pers = (Personnage) adaptateur.getItem(position);



        TextView tvNom = findViewById(R.id.nomtextView);
        tvNom.setText(pers.getNom());

        ImageView iv= findViewById(R.id.imageView);
        String url = pers.getImageUrl();
        Picasso.get().load(url).placeholder(R.mipmap.ic_launcher_round).into(iv);

        TextView tvDescription = findViewById(R.id.descriptionTextView);
        tvDescription.setText(pers.getDescriptif());

    }



}



