package ca.collegelacite.starwars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PersonnageAdapter extends BaseAdapter {

    private Context contexte;
    private ArrayList<Personnage> souceDonnees;
    private LayoutInflater inflater;

    public PersonnageAdapter(Context ctx, ArrayList<Personnage> donnees)
    {
        this.contexte = ctx;
        this.souceDonnees = donnees;

        this.inflater = (LayoutInflater) this.contexte.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return souceDonnees.size();
    }

    @Override
    public Object getItem(int i) {
        return souceDonnees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.personnage_list_item, viewGroup, false);

        Personnage p = souceDonnees.get(i);


        TextView nomTextView = rowView.findViewById(R.id.nomtextView);
        nomTextView.setText(p.getNom());

        ImageView iv = rowView.findViewById(R.id.imageView);
        String url = p.getImageUrl();
        Picasso.get().load(url).placeholder(R.mipmap.ic_launcher_round).into(iv);

        TextView descTV = rowView.findViewById(R.id.descriptionTextView);
        descTV.setText(p.getDescriptif());

        return rowView;
    }
}
