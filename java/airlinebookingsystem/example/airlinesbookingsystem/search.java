package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class search extends AppCompatActivity {
    SearchView searchview;
    ListView list;
    String[] namelist ={"Bangalore to Mumbai Flights","Bangalore To Chennai Flights","Bangalore To Hyderabad Flights","Mumbai To Chennai Flights",
            "Mumbai To Bangalore Flights","Chennai To Bangalore Flights","Hyderabad To Bangalore Flights","Chennai To Mumbai Flights",
            "Chennai To Hyderabad Flights","Hyderabad To Chennai Flights","Hyderabad To Mumbai Flights"};

    ArrayAdapter<String> ArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchview=(SearchView)findViewById(R.id.Searchview);
        list=(ListView) findViewById(R.id.list);

        ArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,namelist);
        list.setAdapter(ArrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(search.this,"Flight-"+  parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT ).show();
                if(position==0){
                    startActivity(new Intent(getApplicationContext(),Book.class));
                }
                else if(position==1){
                    startActivity(new Intent(getApplicationContext(),Book1.class));
                }
                else if(position==2){
                    startActivity(new Intent(getApplicationContext(),Book2.class));
                } else if(position==3){
                    startActivity(new Intent(getApplicationContext(),Book11.class));
                } else if(position==4){
                    startActivity(new Intent(getApplicationContext(),Book3.class));
                } else if(position==5){
                    startActivity(new Intent(getApplicationContext(),Book4.class));
                } else if(position==6){
                    startActivity(new Intent(getApplicationContext(),Book5.class));
                } else if(position==7){
                    startActivity(new Intent(getApplicationContext(),Book6.class));
                } else if(position==8){
                    startActivity(new Intent(getApplicationContext(),Book7.class));
                }else if(position==9){
                    startActivity(new Intent(getApplicationContext(),Book8.class));
                }
                else if(position==10){
                    startActivity(new Intent(getApplicationContext(),Book9.class));
                }


            }
        });



        //search filter


        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                search.this.ArrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                search.this.ArrayAdapter.getFilter().filter(newText);
                return false;
            }
        });


    }
}
