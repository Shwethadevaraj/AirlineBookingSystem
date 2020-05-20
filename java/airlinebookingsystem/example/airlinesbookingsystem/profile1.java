package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class profile1 extends AppCompatActivity {
    private TextView name;
    private TextView mail;
    private TextView phno, male;
    FirebaseAuth myauth;
    FirebaseFirestore fstore;
    private Button done;
    private ImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("My  Profile");

        name = findViewById(R.id.namess);
        mail = findViewById(R.id.mailids);
        phno = findViewById(R.id.phnos);
        male = findViewById(R.id.male1);
        ProfileImage = findViewById(R.id.img);


        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albums = new Intent();
               albums.setType("image/*");
                albums.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(albums, "select picture"), PICK_IMAGE);
            }
        });

        myauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        done = findViewById(R.id.doness);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SecondActivity.class));
                Toast.makeText(getApplicationContext(), "Succesful", Toast.LENGTH_SHORT).show();

            }
        });

        DocumentReference docref = fstore.collection("users").document(myauth.getCurrentUser().getUid());
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String Fullname = documentSnapshot.getString("first") + documentSnapshot.getString("last");
                    name.setText(Fullname);
                    mail.setText(documentSnapshot.getString("email"));
                    phno.setText(documentSnapshot.getString("phno"));
                    male.setText(documentSnapshot.getString("male"));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data!=null  && data.getData()!=null) {
            imageUri=data.getData();
           ProfileImage.setImageURI(imageUri);



                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    ProfileImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

