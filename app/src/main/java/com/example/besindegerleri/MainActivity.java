package com.example.besindegerleri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.besindegerleri.CustomBesinList;
import java.util.ArrayList;



import static java.lang.Long.parseLong;



public class MainActivity extends AppCompatActivity {

        ArrayList<Besin> besins;
        SQLiteDatabaseHandler db;
        Button btnSubmit,btnNextt;
        PopupWindow pwindo=null;
        Activity activity;
        ListView listView=null;
        CustomBesinList customBesinList;





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            activity=this;
            db= new SQLiteDatabaseHandler(this);
            listView = (ListView) findViewById(R.id.list);
            btnSubmit = (Button) findViewById(R.id.btnSubmit);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addPopUp();
                }
            });
            btnNextt=(Button) findViewById(R.id.btnNext);
            btnNextt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextt();
                }
            });

            Log.d("MainActivity: ", "Before reading mainactivity");
            besins = (ArrayList) db.getAllBesin();

            for (Besin besin : besins) {
                String log = "Id: " + besin.getId() + " ,Besin: " + besin.getBesin() + " ,Protein: " + besin.getProtein()
                        + " ,Kalori: " + besin.getKalori()+ " ,Yag: " + besin.getYag()+ " ,Karbon: " + besin.getKarbon();
                // Writing Countries to log
                Log.d("Name: ", log);
            }


            CustomBesinList customBesinList = new CustomBesinList(this, besins, db) {
                @Override
                public int getCount() {
                    return besins.size();
                }
            };
            listView.setAdapter(customBesinList);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(getApplicationContext(), "You Selected " + besins.get(position).getBesin() + " as Besin", Toast.LENGTH_SHORT).show();
                }
            });




        }

    public void nextt() {
        Intent intent=new Intent(MainActivity.this , Main2Activity.class);
        startActivity(intent);
    }


    public void addPopUp() {
            LayoutInflater inflater = activity.getLayoutInflater();
            View popupWindowView=null;
            PopupWindow popupWindow=null;
            View layout = (View) inflater.inflate(R.layout.edit_popup,
            (ViewGroup) activity.findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, 1024, 1280, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);


            final EditText besinEdit = (EditText) layout.findViewById(R.id.editTextBesin);
            final EditText proteinEdit = (EditText) layout.findViewById(R.id.editTextProtein);
            final EditText kaloriEdit = (EditText) layout.findViewById(R.id.editTextKalori);
            final EditText yagEdit = (EditText) layout.findViewById(R.id.editTextYag);
            final EditText karbonEdit = (EditText) layout.findViewById(R.id.editTextKarbon);

            Button save = (Button) layout.findViewById(R.id.save_popup);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String besin = besinEdit.getText().toString();
                    String protein = proteinEdit.getText().toString();
                    String kalori = kaloriEdit.getText().toString();
                    String yag = yagEdit.getText().toString();
                    String karbon = karbonEdit.getText().toString();

                    Besin besinl = new Besin(besin,Double.parseDouble(protein), Double.parseDouble(kalori), Double.parseDouble(yag), Double.parseDouble(karbon));
                    db.addBesin(besinl);
                    if(customBesinList==null)
                    {
                        customBesinList = new CustomBesinList(activity, besins, db) {
                            @Override
                            public int getCount() {
                                return besins.size();
                            }
                        };
                        listView.setAdapter(customBesinList);
                    }
                    customBesinList.besins = (ArrayList) db.getAllBesin();
                    ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
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

