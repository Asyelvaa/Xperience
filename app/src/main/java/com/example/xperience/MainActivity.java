package com.example.xperience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.xperience.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String email = sharedpreferences.getString(EMAIL_KEY, null);
        String password = sharedpreferences.getString(PASSWORD_KEY, null);

        binding.btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.etemail.getText().toString();
                String password = binding.etpassword.getText().toString();

                if (email.equalsIgnoreCase("admin") &&
                        password.equalsIgnoreCase("admin")){

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(EMAIL_KEY, binding.etemail.getText().toString());
                    editor.putString(PASSWORD_KEY, binding.etpassword.getText().toString());

                    // to save data with key and value.
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Gagal Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}