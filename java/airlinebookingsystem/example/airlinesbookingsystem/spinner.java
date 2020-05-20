package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class spinner extends AppCompatActivity {



    // define spinner

    Spinner sp1 ;
    Spinner sp2 ;
    Button btn;
    FirebaseAuth myauth;
    FirebaseFirestore fstore;
    String userId;


    //DEFINE TEXT VIEW

    TextView display_data ;

    Button done ;

    //make string Arrary

    String names[] = {"Red","Blue","Green"};

    //defins array adapter of string type

    ArrayAdapter<String> adapter;

    //define string variable for record

    String record= "";


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spinner);

        sp1 = (Spinner)findViewById(R.id.tvspin);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);

        display_data = (TextView)findViewById(R.id.spin);

btn=findViewById(R.id.ok1);
        //set adapter to spinner

        sp1.setAdapter(adapter);

        //set spinner method

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //use postion value

                switch (position)

                {

                    case 0:

                        record = "Red";

                        break;

                    case 1:

                        record = "Blue";

                        break;

                    case 2:

                        record = "Green";

                        break;

                }

            }


            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });







    }
    public void displayResult(View view)

    {

        display_data.setTextSize(18);

        display_data.setText(record);

        myauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        final DocumentReference docref=fstore.collection("users3").document(userId);
        userId=myauth.getCurrentUser().getUid();

        done.setOnClickListener(new View.OnClickListener() {
            //required field
            @Override
            public void onClick(View v) {
                if(!display_data.getText().toString().isEmpty() )
                {
                    String data=display_data.getText().toString();

                    Map<String,Object> user= new HashMap<>();
                    user.put ("data1",data);
                    docref.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                finish();
                            }else{
                                Toast.makeText(spinner.this,"Data is not inserted",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(spinner.this,"All fields are required",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });



    }
    //set display button click to show result

}

