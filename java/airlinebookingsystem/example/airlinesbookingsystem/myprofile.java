package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class myprofile extends AppCompatActivity {
private TextView name;
private TextView mail;
private TextView phno,male;
FirebaseAuth myauth;
FirebaseFirestore fstore;
private Button done;


Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("My  Profile");

        name=findViewById(R.id.names);
        mail=findViewById(R.id.mailid);
        phno=findViewById(R.id.phno);
        male=findViewById(R.id.male);


        myauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        done=findViewById(R.id.dones);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SecondActivity.class));
                Toast.makeText(getApplicationContext(), "Succesful", Toast.LENGTH_SHORT).show();

            }
        });

        DocumentReference docref=fstore.collection("users").document(myauth.getCurrentUser().getUid());
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String Fullname=documentSnapshot.getString("first")+documentSnapshot.getString("last");
                    name.setText(Fullname);
                    mail.setText(documentSnapshot.getString("email"));
                    phno.setText(myauth.getCurrentUser().getPhoneNumber());
                    male.setText(documentSnapshot.getString("male"));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),phoneactivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
