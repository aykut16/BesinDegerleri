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
import com.example.besindegerleri.Main2Activity;

public abstract class CustomToplamList extends BaseAdapter {
    private Activity context2;
    ArrayList<Toplam>toplams;
    private PopupWindow pwindo;
    SQLiteDatabaseHandler db;
    BaseAdapter ba;

    public CustomToplamList(Activity context, ArrayList<Toplam> toplams,SQLiteDatabaseHandler db) {
        this.context2 = context;
        this.toplams =toplams;
        this.db=db;
    }

    public void CallDegerler(){
        Main2Activity object = new Main2Activity();
        object.Degerler();  // non static method
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
        LayoutInflater inflater = context2.getLayoutInflater();
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            row = inflater.inflate(R.layout.row_item2, null, true);

            vh.textViewId = (TextView) row.findViewById(R.id.textViewId1);
            vh.textViewBesin = (TextView) row.findViewById(R.id.textViewBesin1);
            vh.textViewProtein = (TextView) row.findViewById(R.id.textViewProtein1);
            vh.textViewKalori = (TextView) row.findViewById(R.id.textViewKalori1);
            vh.textViewYag = (TextView) row.findViewById(R.id.textViewYag1);
            vh.textViewKarbon = (TextView) row.findViewById(R.id.textViewKarbon1);
            vh.editButton = (Button) row.findViewById(R.id.edit2);
            vh.deleteButton = (Button) row.findViewById(R.id.delete2);
            // store the holder with the view.
            row.setTag(vh);
        } else {

            vh = (ViewHolder) convertView.getTag();

        }

        vh.textViewBesin.setText(toplams.get(position).getBesin1());
        vh.textViewId.setText("" + toplams.get(position).getId1());
        vh.textViewProtein.setText("Protein= " + toplams.get(position).getProtein1());
        vh.textViewKalori.setText("Kalori= " + toplams.get(position).getKalori1());
        vh.textViewYag.setText("Yag= " + toplams.get(position).getYag1());
        vh.textViewKarbon.setText("Karbon= " + toplams.get(position).getKarbon1());


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
                db.deleteToplam(toplams.get(positionPopup));

                //      countries.remove(index.intValue());
                toplams = (ArrayList) db.getAllToplam();
                Log.d("Besin size", "" + toplams.size());
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

    public int getToplam() {
        return toplams.size();
    }

    public void editPopup(final int positionPopup)
    {
        LayoutInflater inflater = context2.getLayoutInflater();
        View layout = inflater.inflate(R.layout.edit_popup2,
                (ViewGroup) context2.findViewById(R.id.popup_element2));

        pwindo = new PopupWindow(layout, 1024, 1280, true);
        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
        final EditText besinEdit = (EditText) layout.findViewById(R.id.editTextBesin1);
        final EditText proteinEdit = (EditText) layout.findViewById(R.id.editTextProtein1);
        final EditText kaloriEdit = (EditText) layout.findViewById(R.id.editTextKalori1);
        final EditText yagEdit = (EditText) layout.findViewById(R.id.editTextYag1);
        final EditText karbonEdit = (EditText) layout.findViewById(R.id.editTextKarbon1);
        besinEdit.setText(toplams.get(positionPopup).getBesin1());
        proteinEdit.setText("" + toplams.get(positionPopup).getProtein1());
        kaloriEdit.setText("" + toplams.get(positionPopup).getKalori1());
        kaloriEdit.setText("" + toplams.get(positionPopup).getKalori1());
        yagEdit.setText("" + toplams.get(positionPopup).getYag1());
        karbonEdit.setText("" + toplams.get(positionPopup).getKarbon1());

        Log.d("Name: ", "" + toplams.get(positionPopup).getProtein1());
        Button save = (Button) layout.findViewById(R.id.save_popup1);
        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String besinStr = besinEdit.getText().toString();
                String protein = proteinEdit.getText().toString();
                String kalori = kaloriEdit.getText().toString();
                String yag = yagEdit.getText().toString();
                String karbon = karbonEdit.getText().toString();

                Toplam toplam = toplams.get(positionPopup);
                toplam.setBesin1(besinStr);
                toplam.setProtein1((double) Double.parseDouble(protein));
                toplam.setKalori1((double) Double.parseDouble(kalori));
                toplam.setYag1((double) Double.parseDouble(yag));
                toplam.setKarbon1((double) Double.parseDouble(karbon));
                db.updateToplam(toplam);
                toplams = (ArrayList) db.getAllToplam();
                notifyDataSetChanged();
                for (Toplam toplam1 : toplams) {
                    String log = "Id: " + toplam1.getId1() + " ,Besin: " + toplam1.getBesin1() + " ,Protein: " + toplam1.getProtein1()
                            + " ,Kalori: " + toplam1.getKalori1()+ " ,Yag: " + toplam1.getYag1()+ " ,Karbon: " + toplam1.getKarbon1();
                    // Writing Countries to log
                    Log.d("Name: ", log);
                }
                pwindo.dismiss();

            }
        });


    }
}