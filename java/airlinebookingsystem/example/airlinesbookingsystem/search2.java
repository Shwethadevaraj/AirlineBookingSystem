package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class search2 extends AppCompatActivity {
    private Toolbar toolbar;
    Button Pay,ok;
    FirebaseAuth myauth;
    RadioGroup radiogrp;
    FirebaseFirestore fstore;
    String userId;
    TextView fid,fname,arrival,departure,amt,src,des,tv01,child,infant,select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        toolbar =findViewById(R.id.tools);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Book Flight");

        fid=findViewById(R.id.id);
        fname=findViewById(R.id.fname);
        arrival=findViewById(R.id.arr);
        departure=findViewById(R.id.dep);
        amt=findViewById(R.id.amt);
        Pay=findViewById(R.id.btn);
        des=findViewById(R.id.destination);
        src=findViewById(R.id.src);
        tv01=findViewById(R.id.A1);
        child=findViewById(R.id.chil);
        infant=findViewById(R.id.infan);
        radiogrp=findViewById(R.id.Radiogroup);
        select=findViewById(R.id.radio3);


        ok=findViewById(R.id.radio4);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int R1=radiogrp.getCheckedRadioButtonId();

                ok=findViewById(R1);
                select.setText("Selected Cabin Class: "+ok.getText());
            }
        });

        myauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        userId=myauth.getCurrentUser().getUid();
        final DocumentReference docref=fstore.collection("users2").document(userId);
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    fid.setText(documentSnapshot.getString("fid"));
                    fname.setText(documentSnapshot.getString("fname"));
                    arrival.setText(documentSnapshot.getString("arr"));
                   departure.setText(documentSnapshot.getString("dep"));
                    des.setText(documentSnapshot.getString("des"));
                    src.setText(documentSnapshot.getString("src"));
                    amt.setText(documentSnapshot.getString("amt"));
                    tv01.setText(documentSnapshot.getString("a1"));
                    child.setText(documentSnapshot.getString("c1"));
                    infant.setText(documentSnapshot.getString("i1"));


                }
            }
        });



        Pay.setOnClickListener(new View.OnClickListener() {
            //required field
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),payment.class));
            }
        });

    }

    public void Selected(View view){
        int R1=radiogrp.getCheckedRadioButtonId();

        ok=findViewById(R1);
        Toast.makeText(getApplicationContext(),"the cabin class selected is"+ok.getText(),Toast.LENGTH_SHORT).show();
    }
}
