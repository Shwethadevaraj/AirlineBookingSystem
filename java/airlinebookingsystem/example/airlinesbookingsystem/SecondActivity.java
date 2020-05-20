package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Calendar;


public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerlayout;
    NavigationView navigation;


    private Toolbar toolbar;
    private TextView Flight;
    private Button one;
    private Button two;
    private Button multi;
    private ImageButton search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /*hooks*/
        drawerlayout =findViewById(R.id.drawerlayout);
      navigation =findViewById(R.id.nav);
      toolbar =findViewById(R.id.toolbar);

       setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SKY PLAN");



/*navigation drawer menu*/
        navigation.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerlayout,toolbar,R.string.open_navi,R.string.close_navi);
        drawerlayout.addDrawerListener(toggle);
         toggle.syncState();



navigation.setNavigationItemSelectedListener(this);
navigation.setCheckedItem(R.id.home);
Menu menu=navigation.getMenu();
menu.findItem(R.id.Flight_schedule).setVisible(false);
menu.findItem(R.id.home1).setVisible(false);


        Flight = (TextView) findViewById(R.id.tvflights);
        one = (Button) findViewById(R.id.btone);
        two = (Button) findViewById(R.id.bttwo);
        multi = (Button) findViewById(R.id.btthree);
        search = (ImageButton) findViewById(R.id.bt);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), home_activity.class));
            }
        });
two.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
});
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), splashactivity.class));
            }
        });



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

        Button departure=(Button)findViewById(R.id.btdeparture);
        departure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker= new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });
        Button returnn=(Button)findViewById(R.id.btreturn);
        returnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker2= new DatePickerFragment();
                datePicker2.show(getSupportFragmentManager(),"date picker");
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        Calendar C=Calendar.getInstance();
        C.set(Calendar.YEAR,year);
        C.set(Calendar.MONTH,month);
        C.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        String currentDateString2= DateFormat.getDateInstance(DateFormat.FULL).format(C.getTime());
        TextView Departures =(TextView)findViewById(R.id.eddeparture);
        Departures.setText(currentDateString);
        TextView Returns =(TextView)findViewById(R.id.edreturn);
        Returns.setText(currentDateString2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()){
            case R.id.home:
                startActivity(new Intent(getApplicationContext(),SecondActivity.class));
            break;
            case R.id.pro:
                startActivity(new Intent(getApplicationContext(),profile1.class));
                break;
            case R.id.Home1:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                break;
            case R.id.Home3:
                startActivity(new Intent(getApplicationContext(),payment.class));
                Toast.makeText(getApplicationContext(),"BOOK_FLIGHT",Toast.LENGTH_SHORT).show();
                break;
            case R.id.book:
                startActivity(new Intent(getApplicationContext(),search.class));
                break;

            case R.id.trips:
                startActivity(new Intent(getApplicationContext(),Flightdetails.class));
                break;

        }
        drawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}



