package com.example.robin.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robin on 13/7/18.
 */

public class Signup extends Fragment {

    String Server_url = "https://photogenic0001.000webhostapp.com/photogenic/photogenic1.0/createaccount.php";
    Button button;
    Spinner spinner;
    TextView textView;
    ImageView imageView,plock1,plock0,gone;
    EditText username,password,repassword,phno;
    ArrayAdapter<CharSequence> adapter;
    String gender,un,ps,repas,phn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signup,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        username = getActivity().findViewById(R.id.username1);
        password = getActivity().findViewById(R.id.password1);
        repassword = getActivity().findViewById(R.id.rpassword1);
        phno = getActivity().findViewById(R.id.phno1);
        button = getActivity().findViewById(R.id.signup1);
        imageView = getActivity().findViewById(R.id.visible);


        spinner = getActivity().findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.GENDER, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                gender = spinner.getSelectedItem().toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                un = username.getText().toString();
                ps = password.getText().toString();
                repas = repassword.getText().toString();
                phn = phno.getText().toString();
                if(TextUtils.isEmpty(un))
                {
                    username.setError("Cannot be Empty");
                }
                else if(TextUtils.isEmpty(phn))
                {
                    phno.setError("cannot be empty");
                }
                else if(phn.length()!=10){
                    phno.setError("Invalid number");
                }
                else if(TextUtils.isEmpty(ps) && TextUtils.isEmpty(repas)){
                    password.setError("password cannot be empty");
                    repassword.setError("password cannot be empty");
                }

                else if(!ps.equals(repas))
                {
                  Toast.makeText(getActivity(),"password dosent match", Toast.LENGTH_LONG).show();
                }
                else if(gender.equals("Gender")){
                    Toast.makeText(getActivity(),"Please select your Gender", Toast.LENGTH_LONG).show();
                }
                else {
                    open();
                }

            }
        });

    }

    public void open(){
        StringRequest request = new StringRequest(Request.Method.POST, Server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(),response, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String voll = error.toString();
                Toast.makeText(getActivity(),voll, Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                un = username.getText().toString();
                ps = password.getText().toString();
                repas = repassword.getText().toString();
                phn = phno.getText().toString();
                params.put("name",un);
                params.put("password",ps);
                params.put("phno",phn);
                params.put("gender",gender);
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQue(request);
    }
}
