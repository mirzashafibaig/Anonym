package com.anonym.getout.anonym.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.anonym.getout.anonym.Constant;
import com.anonym.getout.anonym.MainActivity;
import com.anonym.getout.anonym.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class signup extends ActionBarActivity {
    protected EditText mUserName;
    protected EditText mPassword;
    protected EditText mEmail;
    protected EditText mFullname;;
    protected Button mSignUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mPassword = (EditText) findViewById(R.id.passwordSignUp);
        mEmail = (EditText) findViewById(R.id.emailSignUp);
        mSignUpButton = (Button) findViewById(R.id.signUpButton);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = mPassword.getText().toString();
                String Email = mEmail.getText().toString();

                password = password.trim();
                Email = Email.trim();

                if ( password.isEmpty() || Email.isEmpty()
                       ) {
                    // error from the user side.. has not enter all the fields
                }else{
                    final Firebase ref = new Firebase(Constant.CONSTANT_URL);
                    ref.createUser(Email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            // sucess creating user
                            Intent intent = new Intent(signup.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            result.put("users", ref.getAuth().getUid()); // store their uid


                        }
                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // there was an error
                        }
                    });
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
