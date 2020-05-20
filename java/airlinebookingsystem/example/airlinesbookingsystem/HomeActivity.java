package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,DatePickerDialog.OnDateSetListener {

    private Button search;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        search = (Button) findViewById(R.id.button);






        Spinner spinner1 = findViewById(R.id.tvfromdetails);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SOURCE, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);


        Spinner spinner2 = findViewById(R.id.tvtodetails);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.DESTINATION, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);
        spinner2.setOnItemSelectedListener(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner1 = findViewById(R.id.tvfromdetails);
                Spinner spinner2 = findViewById(R.id.tvtodetails);
                if (spinner1.getSelectedItem().equals("SOURCE")
                        && spinner2.getSelectedItem().equals("DESTINATION"))
                {
                    Toast.makeText(getApplicationContext(), "Source And Destination is not selected", Toast.LENGTH_SHORT).show();
                }

                else if (spinner1.getSelectedItem().equals("SOURCE")
                        && spinner2.getSelectedItem().equals("BENGALURU-Kempegowda International Airport"))
                {
                    Toast.makeText(getApplicationContext(), "Source is not selected", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("SOURCE")
                        && spinner2.getSelectedItem().equals("CHENNAI- Chennai International Airport"))
                {
                    Toast.makeText(getApplicationContext(), "Source is not selected", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("SOURCE")
                        && spinner2.getSelectedItem().equals("HYDERABAD-Rajiv Gandhi International Airport"))
                {
                    Toast.makeText(getApplicationContext(), "Source is not selected", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("SOURCE")
                        && spinner2.getSelectedItem().equals("MUMBAI-Chatrapathi Shivaji International Airport"))
                {
                    Toast.makeText(getApplicationContext(), "Source is not selected", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("BENGALURU-Kempegowda International Airport")
                        && spinner2.getSelectedItem().equals("DESTINATION"))
                {
                    Toast.makeText(getApplicationContext(), "Destination is not selected", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("CHENNAI- Chennai International Airport")
                        && spinner2.getSelectedItem().equals("DESTINATION"))
                {
                    Toast.makeText(getApplicationContext(), "Destination is not selected", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("HYDERABAD-Rajiv Gandhi International Airport")
                        && spinner2.getSelectedItem().equals("DESTINATION"))
                {
                    Toast.makeText(getApplicationContext(), "Destination is not selected", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("MUMBAI-Chatrapathi Shivaji International Airport")
                        && spinner2.getSelectedItem().equals("DESTINATION"))
                {
                    Toast.makeText(getApplicationContext(), "Destination is not selected", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("BENGALURU-Kempegowda International Airport")
                        && spinner2.getSelectedItem().equals("BENGALURU-Kempegowda International Airport"))
                {
                    Toast.makeText(getApplicationContext(), "Source And Destination Cannot Be Same", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("CHENNAI- Chennai International Airport")
                        && spinner2.getSelectedItem().equals("CHENNAI- Chennai International Airport"))
                {
                    Toast.makeText(getApplicationContext(), "Source And Destination Cannot Be Same", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("HYDERABAD-Rajiv Gandhi International Airport")
                        && spinner2.getSelectedItem().equals("HYDERABAD-Rajiv Gandhi International Airport"))
                {
                    Toast.makeText(getApplicationContext(), "Source And Destination Cannot Be Same", Toast.LENGTH_SHORT).show();
                }
                else if (spinner1.getSelectedItem().equals("MUMBAI-Chatrapathi Shivaji International Airport")
                        && spinner2.getSelectedItem().equals("MUMBAI-Chatrapathi Shivaji International Airport"))
                {
                    Toast.makeText(getApplicationContext(), "Source And Destination Cannot Be Same", Toast.LENGTH_SHORT).show();
                }

                else{
                    startActivity(new Intent(getApplicationContext(), search.class));
                }
            }
        });


        Button departure = (Button) findViewById(R.id.btdeparture);
        departure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        Button returnn = (Button) findViewById(R.id.btreturn);
        returnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker2 = new DatePickerFragment();
                datePicker2.show(getSupportFragmentManager(), "date picker");
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Calendar C = Calendar.getInstance();
        C.set(Calendar.YEAR, year);
        C.set(Calendar.MONTH, month);
        C.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        String currentDateString2 = DateFormat.getDateInstance(DateFormat.FULL).format(C.getTime());
        TextView Departures = (TextView) findViewById(R.id.eddeparture);
        Departures.setText(currentDateString);
        TextView Returns = (TextView) findViewById(R.id.edreturn);
        Returns.setText(currentDateString2);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



