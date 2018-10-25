package my.power.project;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Fragment  {
    String Server_url = "https://beholden-effects.000webhostapp.com/DomPowCom/login.php";
    public Button logg;
    EditText username,password;
    String idofcus,ps;
    TextView signup;
    AVLoadingIndicatorView avi;
    ImageView imageView;
    FragmentTransaction fragmentTransaction;
    public ProgressBar p;
    public LoginActivity()
    {

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_activity, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        avi = getActivity().findViewById(R.id.avi);
        username = getActivity().findViewById(R.id.username1);
        password = getActivity().findViewById(R.id.password1);
        signup = getActivity().findViewById(R.id.signup);
        logg =getActivity().findViewById(R.id.login);


        logg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                avi.show();
                post();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                Signup signin_form = new Signup();
                LoginActivity login_page = (LoginActivity) fragmentManager.findFragmentByTag("login666");
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(login_page);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.add(R.id.cre,signin_form,"signin");
                // getFragmentManager().popBackStackImmediate();
                fragmentTransaction.commit();
            }
        });


    }
    void post()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String res=response.toString().trim();
                        if(!res.equals("0"))
                        {
                            /*SharedPreferences sharedPreferences=getActivity().getSharedPreferences("userid", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("id",idofcus);
                            editor.apply();*/
                            Toast.makeText(getActivity(),res,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(),Homepage.class);
                            startActivity(intent);
//                            fragmentTransaction.remove(Login.this).commit();
                        }
                        else
                        {
                            avi.hide();
                            Toast.makeText(getActivity(), "invalid user name or password",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String voll = error.toString();
                Toast.makeText(getActivity(),voll,Toast.LENGTH_LONG).show();
                avi.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                idofcus = username.getText().toString();
                ps = password.getText().toString();
                params.put("cus",idofcus);
                params.put("ps",ps);
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQue(request);
    }
}

