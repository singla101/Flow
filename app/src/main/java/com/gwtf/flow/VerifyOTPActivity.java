package com.gwtf.phoneauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.gwtf.phoneauthentication.databinding.ActivityOtpactivityBinding;

public class VerifyOTPactivity extends AppCompatActivity {
private ActivityVerifyOTPActivityBinding binding;
private String VerificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityVerifyOTPActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.Etphone.setText(String.format("+91-%s",getIntent().getStringExtra("phone")));
VerificationId=getIntent().getStringExtra("verificationId");
binding.btnresend.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(VerifyOTPActivity.this, "OTP sent Successfully", Toast.LENGTH_SHORT).show();
    }
});
binding.btnsendotp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if(binding.contact.getText().toString().trim().isEmpty()){
            Toast.makeText(VerifyOTPActivity.this, "OTP not Valid", Toast.LENGTH_SHORT).show();
        }
        else{
            if(VerificationId!=null){
                String code=binding.contact.getText().toString().trim();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId, code);
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent intent =new Intent(VerifyOTPActivity.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(VerifyOTPActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }
    }
});
    }

}
