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

public class Book extends AppCompatActivity {
    private Toolbar toolbar;
    Button book;
    FirebaseAuth myauth;
    FirebaseFirestore fstore;
    String userId;
    TextView fid,fname,arrival,departure,amt,src,des;
    Toolbar tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        fid=findViewById(R.id.id);
        fname=findViewById(R.id.fname);
        arrival=findViewById(R.id.arr);
        departure=findViewById(R.id.dep);
        amt=findViewById(R.id.amt);
        book=findViewById(R.id.btn);
        des=findViewById(R.id.destination);
        src=findViewById(R.id.src);
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
                        !src.getText().toString().isEmpty()&& !arrival.getText().toString().isEmpty()&& !arrival.getText().toString().isEmpty())
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
                                Toast.makeText(Book.this,"Data is not inserted",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(Book.this,"All fields are required",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });






    }




}

