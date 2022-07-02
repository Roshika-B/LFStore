package com.roshh.lfstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Database extends AppCompatActivity {
    FirebaseFirestore fs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        fs = FirebaseFirestore.getInstance();

        TextView name = findViewById(R.id.name);
        TextView address = findViewById(R.id.address);
        TextView age = findViewById(R.id.age);
        TextView phoneNumber = findViewById(R.id.phoneNumber);
        Button btn = findViewById(R.id.button);

        String nameValue = name.getText().toString();
        String addressValue = address.getText().toString();
        String ageValue = age.getText().toString();
        String phoneValue = phoneNumber.getText().toString();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference df = fs.collection("db").document("users");
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("name", nameValue);
                hm.put("address", addressValue);
                hm.put("age", ageValue);
                hm.put("phone", phoneValue);

                df.set(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){

                            NotificationChannel channel= new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
                            NotificationManager manager =getSystemService(NotificationManager.class);
                            manager.createNotificationChannel(channel);
                        }
                        String message="Successfully inserted";
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(Database.this,"My Notification");
                        builder.setContentTitle("FIREBASE");
                        builder.setContentText(message);
                        builder.setSmallIcon(R.drawable.ic_launcher_background);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(Database.this);
                        managerCompat.notify(1,builder.build());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
