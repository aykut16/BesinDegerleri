package com.example.besindegerleri;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class Main2Activity extends AppCompatActivity {

    ArrayList<Toplam> toplams;
    SQLiteDatabaseHandler db;
    Button btnSubmit,btnNext2;
    PopupWindow pwindo=null;
    Activity activity2;
    ListView listView=null;
    CustomToplamList customToplamList;
    TextView textProtein2,textKalori2,textYag2,textKarbon2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        activity2=this;
        db= new SQLiteDatabaseHandler(this);
        listView = (ListView) findViewById(R.id.list2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        textProtein2=(TextView)findViewById(R.id.textProtein1);
        textKalori2=(TextView)findViewById(R.id.textKalori1);
        textYag2=(TextView)findViewById(R.id.textYag1);
        textKarbon2=(TextView)findViewById(R.id.textKarbon1);
        btnNext2=(Button)findViewById(R.id.btnNext2);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPopUp();
            }
        });

        Log.d("Main2Activity: ", "Before reading mainactivity");
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Degerler();
            }
        });



        Double sum1 =  db.toplamProtein();
        String sum2=Double.toString(sum1);
        textProtein2.setText(sum2);


        Double sum3 =  db.toplamKalori();
        String sum4=Double.toString(sum3);
        textKalori2.setText(sum4);


        Double sum5 =  db.toplamYag();
        String sum6=Double.toString(sum5);
        textYag2.setText(sum6);


        Double sum7 =  db.toplamKarbon();
        String sum8=Double.toString(sum7);
        textKarbon2.setText(sum8);

        toplams = (ArrayList) db.getAllToplam();



        for (Toplam toplam : toplams) {
            String log = "Id: " + toplam.getId1() + " ,Besin: " + toplam.getBesin1() + " ,Protein: " + toplam.getProtein1()
                    + " ,Kalori: " + toplam.getKalori1()+ " ,Yag: " + toplam.getYag1()+ " ,Karbon: " + toplam.getKarbon1();
            // Writing Countries to log
            Log.d("Name: ", log);
        }


        CustomToplamList customToplamList = new CustomToplamList(this, toplams, db) {
            @Override
            public int getCount() {
                return toplams.size();
            }
        };
        listView.setAdapter(customToplamList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), "You Selected " + toplams.get(position).getBesin1() + " as Toplam", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Degerler(){

        Double sum1 =  db.toplamProtein();
        String sum2=Double.toString(sum1);
        textProtein2.setText(sum2);


        Double sum3 =  db.toplamKalori();
        String sum4=Double.toString(sum3);
        textKalori2.setText(sum4);


        Double sum5 =  db.toplamYag();
        String sum6=Double.toString(sum5);
        textYag2.setText(sum6);


        Double sum7 =  db.toplamKarbon();
        String sum8=Double.toString(sum7);
        textKarbon2.setText(sum8);


        toplams = (ArrayList) db.getAllToplam();


    }

    public void addPopUp() {
        LayoutInflater inflater = activity2.getLayoutInflater();
        View popupWindowView=null;
        PopupWindow popupWindow=null;
        View layout = (View) inflater.inflate(R.layout.edit_popup2,
                (ViewGroup) activity2.findViewById(R.id.popup_element2));
        pwindo = new PopupWindow(layout, 1024, 1280, true);
        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);


        final EditText besinEdit = (EditText) layout.findViewById(R.id.editTextBesin1);
        final EditText proteinEdit = (EditText) layout.findViewById(R.id.editTextProtein1);
        final EditText kaloriEdit = (EditText) layout.findViewById(R.id.editTextKalori1);
        final EditText yagEdit = (EditText) layout.findViewById(R.id.editTextYag1);
        final EditText karbonEdit = (EditText) layout.findViewById(R.id.editTextKarbon1);

        Button save = (Button) layout.findViewById(R.id.save_popup1);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String besin = besinEdit.getText().toString();
                String protein = proteinEdit.getText().toString();
                String kalori = kaloriEdit.getText().toString();
                String yag = yagEdit.getText().toString();
                String karbon = karbonEdit.getText().toString();

                Toplam toplam1 = new Toplam(besin,Double.parseDouble(protein), Double.parseDouble(kalori), Double.parseDouble(yag), Double.parseDouble(karbon));
                db.addToplam(toplam1);
                if(customToplamList==null)
                {
                    customToplamList = new CustomToplamList(activity2, toplams, db) {
                        @Override
                        public int getCount() {
                            return toplams.size();
                        }
                    };
                    listView.setAdapter(customToplamList);
                }
                customToplamList.toplams = (ArrayList) db.getAllToplam();
                ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
                for (Toplam toplam2 : toplams) {
                    String log = "Id: " + toplam2.getId1() + " ,Besin: " + toplam2.getBesin1() + " ,Protein: " + toplam2.getProtein1()
                            + " ,Kalori: " + toplam2.getKalori1()+ " ,Yag: " + toplam2.getYag1()+ " ,Karbon: " + toplam2.getKarbon1();
                    // Writing Countries to log
                    Log.d("Name: ", log);


                }
                Degerler();
                pwindo.dismiss();

            }
        });

    }



}

