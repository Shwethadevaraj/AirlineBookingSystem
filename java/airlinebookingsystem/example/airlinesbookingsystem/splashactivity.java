
package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class splashactivity extends AppCompatActivity  {
    EditText first,last,email,phno;
    Button save,ok;
    FirebaseAuth myauth;
    FirebaseFirestore fstore;
    String userId;
    RadioGroup radiogrp;
    TextView select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);
        first=findViewById(R.id.full);
        last=findViewById(R.id.last);
        email=findViewById(R.id.email);

        save=findViewById(R.id.savebtn);
        radiogrp=findViewById(R.id.Radiogroup);
        select=findViewById(R.id.radio3);

        ok=findViewById(R.id.radio4);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int R1=radiogrp.getCheckedRadioButtonId();

                ok=findViewById(R1);
                select.setText("Selected Gender: "+ok.getText());
            }
        });

        myauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        userId=myauth.getCurrentUser().getUid();
        final DocumentReference docref=fstore.collection("users").document(userId);



        save.setOnClickListener(new View.OnClickListener() {
            //required field
            @Override
            public void onClick(View v) {
                if(!first.getText().toString().isEmpty() && !last.getText().toString().isEmpty()  && !email.getText().toString().isEmpty())
                {
                    String First=first.getText().toString();
                    String Last=last.getText().toString();
                    String Email=email.getText().toString();

                    String Select=select.getText().toString();
                    Map<String,Object> user= new HashMap<>();
                    user.put ("first",First);
                    user.put ("last",Last);
                    user.put ("email",Email);

                    user.put("male",Select);
                    docref.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),myprofile.class));
                                finish();
                            }else{
                                Toast.makeText(splashactivity.this,"Data is not inserted",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(splashactivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


    }
    public void Selected(View view){
        int R1=radiogrp.getCheckedRadioButtonId();

        ok=findViewById(R1);
        Toast.makeText(getApplicationContext(),"The gender selected is"+ok.getText(),Toast.LENGTH_SHORT).show();
    }
}
