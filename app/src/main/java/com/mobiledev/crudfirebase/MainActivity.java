package com.mobiledev.crudfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobiledev.crudfirebase.Adapters.ListViewRegistersAdapter;
import com.mobiledev.crudfirebase.Models.Register;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Register> listRegisters = new ArrayList<Register>();
    ArrayAdapter<Register> arrayAdapterRegister;
    ListViewRegistersAdapter listViewRegistersAdapter;
    LinearLayout linearLayoutEdit;
    ListView listViewRegisters;
    EditText inputSerialNumber,
            inputDescription,
            inputProcessor,
            inputMemoryRam,
            inputHardDisk,
            inputOperativeSystem,
            inputPort,
            inputConnectivity,
            inputCamera;

    Button btnCancel;

    Register registerSelected;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSerialNumber = findViewById(R.id.inputSerialNumber);
        inputDescription = findViewById(R.id.inputDescription);
        inputProcessor = findViewById(R.id.inputProcessor);
        inputMemoryRam = findViewById(R.id.inputRamMemory);
        inputHardDisk = findViewById(R.id.inputHardDisk);
        inputOperativeSystem = findViewById(R.id.inputOperativeSystem);
        inputPort = findViewById(R.id.inputPorts);
        inputConnectivity = findViewById(R.id.inputConnectivity);
        inputCamera = findViewById(R.id.inputCameras);

        listViewRegisters = findViewById(R.id.listViewRegistros);
        linearLayoutEdit = findViewById(R.id.linearLayoutEditar);

        initialFirebase();

    }

    private void initialFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}