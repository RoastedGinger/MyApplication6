package com.example.robin.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    TextView textView;
    ImageView imageView,plock1,plock0,gone;
    EditText username,password,repassword,phno;
    String un,ps,repas,phn,email,add;
    FragmentTransaction fragmentTransaction;
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
                else {

                    //open();
                    SharedPreferences preferences = getActivity().getSharedPreferences("details", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name",un);
                    editor.putString("Phone", phn);
                    editor.putString("Email",email);
                    editor.putString("password",ps);
                    editor.putString("address",add);
                    editor.apply();
                    FragmentManager fragmentManager = getFragmentManager();
                    Secondsignup secondsignup = new Secondsignup();
                    Signup signin_form = (Signup) fragmentManager.findFragmentByTag("signin");
                  //  LoginActivity login_page = (LoginActivity) fragmentManager.findFragmentByTag("login666");
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(signin_form);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.add(R.id.cre,secondsignup,"secondsign");
                    // getFragmentManager().popBackStackImmediate();
                    fragmentTransaction.commit();
                }

            }
        });

    }

    public void open(){

    }
}
