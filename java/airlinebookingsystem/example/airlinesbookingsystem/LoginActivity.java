package airlinebookingsystem.example.airlinesbookingsystem;

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

public class LoginActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private TextView signup;
    private int counter = 5;

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),SecondActivity.class));
        }

        mDialog=new ProgressDialog(this);



        Email = (EditText) findViewById(R.id.editTextEmail);
        Password = (EditText) findViewById(R.id.editTextPassword);
        Info = (TextView) findViewById(R.id.textViewinfo);
        Login = (Button) findViewById(R.id.buttonlogin);
        signup = (TextView) findViewById(R.id.tvsignup);


        Info.setText("No. of attempts remaining:5");


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=Email.getText().toString().trim();
                String password=Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Required field.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Password.setError("Reqired field.");
                    return;
                }
                mDialog.setMessage("Processing...");
                mDialog.show();

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),SecondActivity.class));
                            Toast.makeText(getApplicationContext(),"Succesful",Toast.LENGTH_SHORT).show();

                            mDialog.dismiss();
                        }
                        else{
                            counter--;
                            Info.setText("No of attempts remaining"+String.valueOf(counter));
                            if(counter==0){
                                Login.setEnabled(false);
                                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                            }

                            mDialog.dismiss();

                        }
                    }
                });


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });


    }
}
