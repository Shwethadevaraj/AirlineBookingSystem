package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;


public class RegisterActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button signup;
    private TextView Login;
    private TextView regph;

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mAuth=FirebaseAuth.getInstance();

        mDialog=new ProgressDialog(this);



        Email = (EditText) findViewById(R.id.editTextEmail);
        Password = (EditText) findViewById(R.id.editTextPassword);

       signup = (Button) findViewById(R.id.buttonsignup);
        Login= (TextView) findViewById(R.id.tvsignin);
        regph= (TextView) findViewById(R.id.regphno);






        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Email.setError("Required field.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Reqired field.");
                    return;
                }
               
                mDialog.setMessage("Processing...");
                mDialog.show();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                            Toast.makeText(getApplicationContext(), "Succesful", Toast.LENGTH_SHORT).show();


                            mDialog.dismiss();
                        }

                        else {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    }
                });
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        regph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),phoneactivity.class));
            }
        });

    }
}
