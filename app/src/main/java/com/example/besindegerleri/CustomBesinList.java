package com.example.besindegerleri;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import com.example.besindegerleri.SQLiteDatabaseHandler;

public abstract class CustomBesinList extends BaseAdapter {
    private Activity context;
    ArrayList<Besin>besins;
    private PopupWindow pwindo;
    SQLiteDatabaseHandler db;
    BaseAdapter ba;

    public CustomBesinList(Activity context, ArrayList<Besin> besins,SQLiteDatabaseHandler db) {
        this.context = context;
        this.besins =besins;
        this.db=db;
    }

    public static class ViewHolder
    {
        TextView textViewId;
        TextView textViewBesin;
        TextView textViewProtein;
        TextView textViewKalori;
        TextView textViewYag;
        TextView textViewKarbon;
        Button editButton;
        Button deleteButton;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            row = inflater.inflate(R.layout.row_item, null, true);

            vh.textViewId = (TextView) row.findViewById(R.id.textViewId);
            vh.textViewBesin = (TextView) row.findViewById(R.id.textViewBesin);
            vh.textViewProtein = (TextView) row.findViewById(R.id.textViewProtein);
            vh.textViewKalori = (TextView) row.findViewById(R.id.textViewKalori);
            vh.textViewYag = (TextView) row.findViewById(R.id.textViewYag);
            vh.textViewKarbon = (TextView) row.findViewById(R.id.textViewKarbon);
            vh.editButton = (Button) row.findViewById(R.id.edit);
            vh.deleteButton = (Button) row.findViewById(R.id.delete);
            // store the holder with the view.
            row.setTag(vh);
        } else {

            vh = (ViewHolder) convertView.getTag();

        }

        vh.textViewBesin.setText(besins.get(position).getBesin());
        vh.textViewId.setText("" + besins.get(position).getId());
        vh.textViewProtein.setText("Protein= " + besins.get(position).getProtein());
        vh.textViewKalori.setText("Kalori= " + besins.get(position).getKalori());
        vh.textViewYag.setText("Yag= " + besins.get(position).getYag());
        vh.textViewKarbon.setText("Karbon= " + besins.get(position).getKarbon());


        final int positionPopup = position;
        vh.editButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Save: ", "" + positionPopup);
                editPopup(positionPopup);

            }
        });
        vh.deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Last Index", "" + positionPopup);
                //     Integer index = (Integer) view.getTag();
                db.deleteBesin(besins.get(positionPopup));

                //      countries.remove(index.intValue());
                besins = (ArrayList) db.getAllBesin();
                Log.d("Besin size", "" + besins.size());
                notifyDataSetChanged();
            }
        });


        return  row;
    }


    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getBesin() {
        return besins.size();
    }

    public void editPopup(final int positionPopup)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.edit_popup,
                (ViewGroup) context.findViewById(R.id.popup_element));

        pwindo = new PopupWindow(layout, 1024, 1280, true);
        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
        final EditText besinEdit = (EditText) layout.findViewById(R.id.editTextBesin);
        final EditText proteinEdit = (EditText) layout.findViewById(R.id.editTextProtein);
        final EditText kaloriEdit = (EditText) layout.findViewById(R.id.editTextKalori);
        final EditText yagEdit = (EditText) layout.findViewById(R.id.editTextYag);
        final EditText karbonEdit = (EditText) layout.findViewById(R.id.editTextKarbon);
        besinEdit.setText(besins.get(positionPopup).getBesin());
        proteinEdit.setText("" + besins.get(positionPopup).getProtein());
        kaloriEdit.setText("" + besins.get(positionPopup).getKalori());
        kaloriEdit.setText("" + besins.get(positionPopup).getKalori());
        yagEdit.setText("" + besins.get(positionPopup).getYag());
        karbonEdit.setText("" + besins.get(positionPopup).getKarbon());

        Log.d("Name: ", "" + besins.get(positionPopup).getProtein());
        Button save = (Button) layout.findViewById(R.id.save_popup);
        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String besinStr = besinEdit.getText().toString();
                String protein = proteinEdit.getText().toString();
                String kalori = kaloriEdit.getText().toString();
                String yag = yagEdit.getText().toString();
                String karbon = karbonEdit.getText().toString();

                Besin besin = besins.get(positionPopup);
                besin.setBesin(besinStr);
                besin.setProtein((double) Double.parseDouble(protein));
                besin.setKalori((double) Double.parseDouble(kalori));
                besin.setYag((double) Double.parseDouble(yag));
                besin.setKarbon((double) Double.parseDouble(karbon));
                db.updateBesin(besin);
                besins = (ArrayList) db.getAllBesin();
                notifyDataSetChanged();
                for (Besin besin1 : besins) {
                    String log = "Id: " + besin1.getId() + " ,Besin: " + besin1.getBesin() + " ,Protein: " + besin1.getProtein()
                            + " ,Kalori: " + besin1.getKalori()+ " ,Yag: " + besin1.getYag()+ " ,Karbon: " + besin1.getKarbon();
                    // Writing Countries to log
                    Log.d("Name: ", log);
                }
                pwindo.dismiss();

            }
        });


    }
}