package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity{

    private  static int SPLASH_SCREEN=5000;


    //variables

    Animation topanim,bottomanim;
    ImageView image;
    TextView logo,slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //animation
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooke
        image=findViewById(R.id.imageView);
        logo=findViewById(R.id.textView2);
        slogan=findViewById(R.id.textView3);

        image.setAnimation(topanim);
        image.setAnimation(bottomanim);
        slogan.setAnimation(bottomanim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }

        },SPLASH_SCREEN);

    }
}
