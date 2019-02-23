package com.example.interndcr;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner productGroup,literature,physican,gift;
    Button submit;
    String url="https://raw.githubusercontent.com/appinion-dev/intern-dcr-data/master/data.json";
    RequestQueue rq;


    final public static List<String>DataProductGroup=new ArrayList<>();
    final public static List<String>DataLiterautre=new ArrayList<>();
    final public static List<String>DataPhysician=new ArrayList<>();
    final public static List<String>DataGift=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rq= Volley.newRequestQueue(this);
        submit=(Button)findViewById(R.id.btn_Submit);


        productGroup=(Spinner)findViewById(R.id.spinnerPG);
        literature=(Spinner)findViewById(R.id.spinnerLT);
        physican=(Spinner)findViewById(R.id.spinnerPH);
        gift=(Spinner)findViewById(R.id.spinnerGift);

        JsonDataParse();
        ProductGroupSpinnerData();
        LiteratureSpinnerData();
        PhysicianSpinnerData();
        GiftSpinnerData();

         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(productGroup.getContext(),"",Toast.LENGTH_LONG).show();
                 Toast.makeText(getApplicationContext(),"done", Toast.LENGTH_LONG).show();


             }
         });
    }

    private void JsonDataParse() {
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray ProductArray=response.getJSONArray("product_group_list");
                    JSONArray LiteratureArray=response.getJSONArray("literature_list");
                    JSONArray PhysicianArray=response.getJSONArray("physician_sample_list");
                    JSONArray GiftArray=response.getJSONArray("gift_list");
                    for(int i=0;i<ProductArray.length();i++)
                    {
                        JSONObject productlist=ProductArray.getJSONObject(i);

                        String produuctname=productlist.getString("product_group");


                            DataProductGroup.add(produuctname);

                    }
                    productGroup.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, DataProductGroup));

                    for(int i=0;i< LiteratureArray.length();i++)
                    {
                        JSONObject literaturelist= LiteratureArray.getJSONObject(i);
                        String literaturename=literaturelist.getString("literature");


                            DataLiterautre.add(literaturename);

                    }
                    literature.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, DataLiterautre));

                    for(int i=0;i< PhysicianArray.length();i++)
                    {
                        JSONObject physicianslist= PhysicianArray.getJSONObject(i);

                        String physicianname=physicianslist.getString("sample");


                            DataPhysician.add(physicianname);

                    }
                    physican.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, DataPhysician));

                    for(int i=0;i< GiftArray.length();i++)
                    {
                        JSONObject giftlist= GiftArray.getJSONObject(i);
                        String giftname=giftlist.getString("gift");

                            DataGift.add(giftname);

                    }
                    gift.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, DataGift));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        rq.add(request);


    }

    void ProductGroupSpinnerData()
    {
        ArrayAdapter<String> productadapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,DataProductGroup)
        {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView itemtext = (TextView) view;
                if(position == 0){

                    itemtext.setTextColor(Color.parseColor("#853497"));
                }
                else {
                    itemtext.setTextColor(Color.BLACK);
                }

                return view;
            }
        };
        productadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productGroup.setAdapter(productadapter);

        productGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = productGroup.getItemAtPosition(productGroup.getSelectedItemPosition()).toString();

                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        "Choose : " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    void LiteratureSpinnerData()
    {
        ArrayAdapter<String> literaturedapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,DataLiterautre)
        {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView itemtext = (TextView) view;
                if(position == 0){

                    itemtext.setTextColor(Color.parseColor("#853497"));
                }
                else {
                    itemtext.setTextColor(Color.BLACK);
                }


                return view;
            }
        };
        literaturedapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        literature.setAdapter(literaturedapter);

        literature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        "Choose : " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    void PhysicianSpinnerData()
    {
        ArrayAdapter<String> physiciandapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,DataPhysician)
        {


            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView itemtext = (TextView) view;
                if(position == 0){

                    itemtext.setTextColor(Color.parseColor("#853497"));
                }
                else {
                    itemtext.setTextColor(Color.BLACK);
                }


                return view;
            }
        };
        physiciandapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        physican.setAdapter(physiciandapter);
        physican.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        "Choose : " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    void GiftSpinnerData()
    {
        ArrayAdapter<String> giftandapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,DataGift)
        {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView itemtext = (TextView) view;
                if(position == 0){

                    itemtext.setTextColor(Color.parseColor("#853497"));
                }
                else {
                    itemtext.setTextColor(Color.BLACK);
                }



                return view;
            }
        };
        giftandapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gift.setAdapter(giftandapter);
        gift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        "Choose : " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
