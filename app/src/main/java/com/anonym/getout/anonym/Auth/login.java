package com.anonym.getout.anonym.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anonym.getout.anonym.Constant;
import com.anonym.getout.anonym.MainActivity;
import com.anonym.getout.anonym.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class login extends ActionBarActivity {
    protected EditText mPassword;
    protected EditText mEmail;
    protected Button mLogInButton;
    protected TextView mSignUpTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

// when the user clicks on the signup
        mSignUpTextView = (TextView) findViewById(R.id.signUpTextView);
        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });
       // sign up action ends

        // OAuth user
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = mPassword.getText().toString();
                String Email = mEmail.getText().toString();
                password = password.trim();
                Email = Email.trim();

                if (password.isEmpty() || Email.isEmpty()) {
                    // empty space reenter users's creditanls
                }else{
                    Firebase ref = new Firebase(Constant.CONSTANT_URL);
                    // Create a handler to handle the result of the authentication
                   ref.authWithPassword(Email, password, new Firebase.AuthResultHandler() {
                       @Override
                       public void onAuthenticated(AuthData authData) {
                           // sucess ! logging in the background
                           Intent intent = new Intent(login.this, MainActivity.class);
                           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(intent);
                           // start the intent

                       }

                       @Override
                       public void onAuthenticationError(FirebaseError firebaseError) {
                            // something went wrong !!
                       }
                   });
                }
            }
       });
 }
         @Override
         public boolean onCreateOptionsMenu (Menu menu){
             // Inflate the menu; this adds items to the action bar if it is present.
             getMenuInflater().inflate(R.menu.menu_login, menu);
             return true;
         }

         @Override
         public boolean onOptionsItemSelected (MenuItem item){
             // Handle action bar item clicks here. The action bar will
             // automatically handle clicks on the Home/Up button, so long
             // as you specify a parent activity in AndroidManifest.xml.
             int id = item.getItemId();

             //noinspection SimplifiableIfStatement
             if (id == R.id.action_settings) {
                 return true;
             }

             return super.onOptionsItemSelected(item);
         }
     }
