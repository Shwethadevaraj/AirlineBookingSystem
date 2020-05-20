package airlinebookingsystem.example.airlinesbookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class phoneactivity extends AppCompatActivity {
    public static final String TAG="TAG";
FirebaseAuth myauth;
EditText phoneno,codeenter;
FirebaseFirestore fstore;
Button next;
ProgressBar progressBar;
TextView state;
CountryCodePicker codepicker;
String verificatinid;
PhoneAuthProvider.ForceResendingToken token;
Boolean verificationinprogress=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneactivity);


        myauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();


        phoneno=findViewById(R.id.phone);
        codeenter=findViewById(R.id.codeEnter);
        progressBar=findViewById(R.id.progressBar);
        next=findViewById(R.id.nextBtn);
        state=findViewById(R.id.state);
        codepicker=findViewById(R.id.ccp);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!verificationinprogress){
                   if(!phoneno.getText().toString().isEmpty()  && phoneno.getText().toString().length()==10){
                       String phno="+"+codepicker.getSelectedCountryCode()+phoneno.getText().toString();
                       Log.d(TAG,"on click: Phone No->"+phno);
                       progressBar.setVisibility(View.VISIBLE);
                       state.setText("Sending OTP...");
                       state.setVisibility(View.VISIBLE);
                       requestOTP(phno);

                   }else{
                       phoneno.setError("Phone Number is not Valid");
                   }
               }else{
                String userOTP=codeenter.getText().toString();
                if(!userOTP.isEmpty() && userOTP.length()==0){
                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificatinid,userOTP);
                    verifyAuth(credential);
                }else{
                    codeenter.setError("Valid OTP Is Required..");
                }
               }
            }
        });
    }





    private void verifyAuth(PhoneAuthCredential credential) {
        myauth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   checkUserProfile();
                }
                else{
                    Toast.makeText(phoneactivity.this,"Authentication failed!.",Toast.LENGTH_SHORT).show();;

                }
            }
        });
    }
    private void checkUserProfile() {


        DocumentReference docref=fstore.collection("users").document(myauth.getCurrentUser().getUid());
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    startActivity(new Intent(getApplicationContext(),splashactivity.class));
                    finish();
                }else
                {
                    startActivity(new Intent(getApplicationContext(),splashactivity.class));
                    finish();
                }
            }
        });

    }
    private void requestOTP(String phno) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phno, 60L, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                progressBar.setVisibility(View.GONE);
                state.setVisibility(View.GONE);
                codeenter.setVisibility(View.VISIBLE);
                verificatinid=s;
                token=forceResendingToken;
                next.setText("Verify");
                next.setEnabled(false);

            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                Toast.makeText(phoneactivity.this,"OTP Expired, Re-Request the OTP",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
               verifyAuth(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(phoneactivity.this,"Cannot Create Account"+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
