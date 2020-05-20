package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Book8 extends AppCompatActivity {
    private Toolbar toolbar;
    Button book,book1,book2;
    FirebaseAuth myauth;
    FirebaseFirestore fstore;
    String userId;
    TextView fid,fname,arrival,departure,amt,src,des,fid1,fname1,arrival1,departure1,amt1,src1,des1,fid2,fname2,arrival2,departure2,amt2,src2,des2;
    Toolbar tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book8);

        toolbar=findViewById(R.id.tools);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Book Flight");

        fid=findViewById(R.id.id);
        fname=findViewById(R.id.fname);
        arrival=findViewById(R.id.arr);
        departure=findViewById(R.id.dep);
        amt=findViewById(R.id.amt);
        book=findViewById(R.id.btn);
        des=findViewById(R.id.destination);
        src=findViewById(R.id.src);
        fid1=findViewById(R.id.id1);
        fname1=findViewById(R.id.fname1);
        arrival1=findViewById(R.id.arr1);
        departure1=findViewById(R.id.dep1);
        amt1=findViewById(R.id.amt1);
        book1=findViewById(R.id.btn1);
        des1=findViewById(R.id.destination1);
        src1=findViewById(R.id.src1);
        fid2=findViewById(R.id.id2);
        fname2=findViewById(R.id.fname2);
        arrival2=findViewById(R.id.arr2);
        departure2=findViewById(R.id.dep2);
        amt2=findViewById(R.id.amt2);
        book2=findViewById(R.id.btn2);
        des2=findViewById(R.id.destination2);
        src2=findViewById(R.id.src2);

        tools =findViewById(R.id.tools);
        setSupportActionBar(tools);
        getSupportActionBar().setTitle("Book Flight");


        myauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        userId=myauth.getCurrentUser().getUid();


        final  DocumentReference docref1=fstore.collection("users2").document(myauth.getCurrentUser().getUid());
        book.setOnClickListener(new View.OnClickListener() {
            //required field
            @Override
            public void onClick(View v) {
                if(!fid.getText().toString().isEmpty() && !fname.getText().toString().isEmpty()  && !arrival.getText().toString().isEmpty() && !des.getText().toString().isEmpty()&&
                        !src.getText().toString().isEmpty()&& !departure.getText().toString().isEmpty()&& !amt.getText().toString().isEmpty())
                {
                    String Fid=fid.getText().toString();
                    String Fname=fname.getText().toString();
                    String Arr=arrival.getText().toString();
                    String Dep=departure.getText().toString();
                    String Src=src.getText().toString();
                    String Des=des.getText().toString();
                    String Amt=amt.getText().toString();
                    Map<String,Object> user= new HashMap<>();
                    user.put ("fid",Fid);
                    user.put ("fname",Fname);
                    user.put ("arr",Arr);
                    user.put("dep",Dep);
                    user.put("des",Des);
                    user.put("src",Src);
                    user.put("amt",Amt);
                    docref1.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),search2.class));
                                finish();
                            }else{
                                Toast.makeText(Book8.this,"Data is not inserted",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(Book8.this,"All fields are required",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!fid1.getText().toString().isEmpty() && !fname1.getText().toString().isEmpty()  && !arrival1.getText().toString().isEmpty() && !des1.getText().toString().isEmpty()&&
                        !src1.getText().toString().isEmpty()&& !departure1.getText().toString().isEmpty()&& !amt1.getText().toString().isEmpty())
                {
                    String Fid=fid1.getText().toString();
                    String Fname=fname1.getText().toString();
                    String Arr=arrival1.getText().toString();
                    String Dep=departure1.getText().toString();
                    String Src=src1.getText().toString();
                    String Des=des1.getText().toString();
                    String Amt=amt1.getText().toString();
                    Map<String,Object> user= new HashMap<>();
                    user.put ("fid",Fid);
                    user.put ("fname",Fname);
                    user.put ("arr",Arr);
                    user.put("dep",Dep);
                    user.put("des",Des);
                    user.put("src",Src);
                    user.put("amt",Amt);
                    docref1.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),search2.class));
                                finish();
                            }else{
                                Toast.makeText(Book8.this,"Data is not inserted",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(Book8.this,"All fields are required",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!fid2.getText().toString().isEmpty() && !fname2.getText().toString().isEmpty()  && !arrival2.getText().toString().isEmpty() && !des1.getText().toString().isEmpty()&&
                        !src2.getText().toString().isEmpty()&& !departure2.getText().toString().isEmpty()&& !amt2.getText().toString().isEmpty())
                {
                    String Fid=fid2.getText().toString();
                    String Fname=fname2.getText().toString();
                    String Arr=arrival2.getText().toString();
                    String Dep=departure2.getText().toString();
                    String Src=src2.getText().toString();
                    String Des=des2.getText().toString();
                    String Amt=amt2.getText().toString();
                    Map<String,Object> user= new HashMap<>();
                    user.put ("fid",Fid);
                    user.put ("fname",Fname);
                    user.put ("arr",Arr);
                    user.put("dep",Dep);
                    user.put("des",Des);
                    user.put("src",Src);
                    user.put("amt",Amt);
                    docref1.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),search2.class));
                                finish();
                            }else{
                                Toast.makeText(Book8.this,"Data is not inserted",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(Book8.this,"All fields are required",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });




    }




}

