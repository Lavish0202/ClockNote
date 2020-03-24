package com.example.clocknote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private SignInButton login;
    private GoogleSignInClient googleSignInClient;
    private Button logout;
    private FirebaseAuth auth;
    private int RC_signin=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login);
        logout=findViewById(R.id.logout);
        auth=FirebaseAuth.getInstance();
        GoogleSignInOptions signInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient=GoogleSignIn.getClient(this,signInOptions);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignInClient.signOut();
                Toast.makeText(LoginActivity.this,"You've Logged out!",Toast.LENGTH_SHORT).show();
                logout.setVisibility(View.INVISIBLE);
            }
        });

    }
    private void signIn(){
        Intent intent=googleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_signin);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_signin){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleresult(task);
        }
    }
    private void handleresult(Task<GoogleSignInAccount> complete){
        try {
            GoogleSignInAccount acc=complete.getResult(ApiException.class);
            Intent intent1=new Intent(this,inputschedule.class);
            startActivity(intent1);

            Toast.makeText(LoginActivity.this,"Sign-In Successful",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }
        catch (ApiException e) {
            e.printStackTrace();
            Toast.makeText(LoginActivity.this,"Sign-In Failed!",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct){
        AuthCredential authCredential= GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Welcome!",Toast.LENGTH_SHORT).show();
                    FirebaseUser user=auth.getCurrentUser();
                    updatebtn(user);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Sign-In Failed!",Toast.LENGTH_SHORT).show();
                    updatebtn(null);
                }
            }
        });
    }
    private void updatebtn(FirebaseUser user){
        logout.setVisibility(View.VISIBLE);
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account!=null){
            String name=account.getDisplayName();
            String email=account.getEmail();

            //String id=account.getId();
            //Uri photo=account.getPhotoUrl();
            Toast.makeText(LoginActivity.this,"Welcome" + name + email,Toast.LENGTH_SHORT).show();

        }
    }

}
