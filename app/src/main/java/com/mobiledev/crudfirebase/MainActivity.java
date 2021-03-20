package com.mobiledev.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobiledev.crudfirebase.Adapters.ListViewRegistersAdapter;
import com.mobiledev.crudfirebase.Models.Register;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

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
//        registerSelected = new Register();
        btnCancel = findViewById(R.id.btnCancel);

        listViewRegisters = findViewById(R.id.listViewRegistros);
        linearLayoutEdit = findViewById(R.id.linearLayoutEditar);

        listViewRegisters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                registerSelected = (Register) parent.getItemAtPosition(position);
                inputSerialNumber.setText(registerSelected.getNumberSerial());
                inputDescription.setText(registerSelected.getDescription());
                inputProcessor.setText(registerSelected.getProcessor());
                inputMemoryRam.setText(registerSelected.getMemoryRam());
                inputHardDisk.setText(registerSelected.getHardDisk());
                inputOperativeSystem.setText(registerSelected.getOperativeSystem());
                inputPort.setText(registerSelected.getPorts());
                inputConnectivity.setText(registerSelected.getConnectivity());
                inputCamera.setText(registerSelected.getCamera());

                linearLayoutEdit.setVisibility(View.VISIBLE);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutEdit.setVisibility(View.GONE);
                registerSelected = null;
            }
        });

        initialFirebase();
        listRegister();

    }

    private void initialFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listRegister(){
        databaseReference.child("Registers").orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listRegisters.clear();
                for(DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Register r = objSnapshot.getValue(Register.class);
                    listRegisters.add(r);
                }

                listViewRegistersAdapter = new ListViewRegistersAdapter(
                        MainActivity.this,
                        listRegisters);
//                arrayAdapterRegister = new ArrayAdapter<Register>(
//                        MainActivity.this,
//                        android.R.layout.simple_list_item_1,
//                        listRegisters
//                );
                listViewRegisters.setAdapter(listViewRegistersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.crud_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String serialNumber = inputSerialNumber.getText().toString();
        String description = inputDescription.getText().toString();
        String processor = inputProcessor.getText().toString();
        String memoryRam = inputMemoryRam.getText().toString();
        String hardDisk = inputHardDisk.getText().toString();
        String operativeSystem = inputOperativeSystem.getText().toString();
        String ports = inputPort.getText().toString();
        String connectivity = inputConnectivity.getText().toString();
        String camera = inputCamera.getText().toString();

        switch (item.getItemId()){
            case R.id.add_menu:
                insert();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void insert(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(
                MainActivity.this
        );
        View mView = getLayoutInflater().inflate(R.layout.insert, null);
        Button btnInsert = (Button) mView.findViewById(R.id.btnInsert);
        final EditText mInputSerialNumber = (EditText) mView.findViewById(R.id.inputSerialNumber);
        final EditText mInputDescription = (EditText) mView.findViewById(R.id.inputDescription);
        final EditText mInputProcessor = (EditText) mView.findViewById(R.id.inputProcessor);
        final EditText mInputMemoryRam = (EditText) mView.findViewById(R.id.inputRamMemory);
        final EditText mInputHardDisk = (EditText) mView.findViewById(R.id.inputHardDisk);
        final EditText mInputOperativeSystem = (EditText) mView.findViewById(R.id.inputOperativeSystem);
        final EditText mInputPort = (EditText) mView.findViewById(R.id.inputPorts);
        final EditText mInputConnectivity = (EditText) mView.findViewById(R.id.inputConnectivity);
        final EditText mInputCamera = (EditText) mView.findViewById(R.id.inputCameras);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serialNumber = mInputSerialNumber.getText().toString();
                String description = mInputDescription.getText().toString();
                String processor = mInputProcessor.getText().toString();
                String memoryRam = mInputMemoryRam.getText().toString();
                String hardDisk = mInputHardDisk.getText().toString();
                String operativeSystem = mInputOperativeSystem.getText().toString();
                String port = mInputPort.getText().toString();
                String connectivity = mInputConnectivity.getText().toString();
                String camera = mInputCamera.getText().toString();

                if (serialNumber.isEmpty() || serialNumber.length()<9){
                    showError(mInputSerialNumber, "Número de Serie inválido (Mínimo 9 caracteres)");
                } else if(description.isEmpty()){
                    showError(mInputDescription, "Debe tener una descripción");
                }else if(processor.isEmpty()){
                    showError(mInputProcessor, "Debe tener un procesador");
                }else if(memoryRam.isEmpty()){
                    showError(mInputMemoryRam, "Debe tener memoria RAM");
                }else if(hardDisk.isEmpty()){
                    showError(mInputHardDisk, "Debe tener un disco duro");
                }else if(operativeSystem.isEmpty()){
                    showError(mInputOperativeSystem, "Debe tener un OS");
                }else if(port.isEmpty()){
                    showError(mInputPort, "Debe tener puertos de conexión");
                }else if(connectivity.isEmpty()){
                    showError(mInputConnectivity, "Debe tener conectividad disponible");
                }else if(camera.isEmpty()){
                    showError(mInputCamera, "Debe tener alguna cámara");
                }else{
                    Register r = new Register();
                    r.setIdRegister(UUID.randomUUID().toString());
                    r.setNumberSerial(serialNumber);
                    r.setDescription(description);
                    r.setProcessor(processor);
                    r.setMemoryRam(memoryRam);
                    r.setHardDisk(hardDisk);
                    r.setOperativeSystem(operativeSystem);
                    r.setPorts(port);
                    r.setConnectivity(connectivity);
                    r.setCamera(camera);
                    r.setDateRegister(getDateNormal(getDateMls()));
                    r.setTimestamp(getDateMls()*-1);
                    databaseReference.child("Registers").child(r.getIdRegister()).setValue(r);
                    Toast.makeText(
                            MainActivity.this,
                            "Registrado correctamente",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void showError(EditText input, String message){
        input.requestFocus();
        input.setError(message);
    }

    public long getDateMls(){
        Calendar calendar = Calendar.getInstance();
        long timeUnix = calendar.getTimeInMillis();

        return  timeUnix;
    }

    public String getDateNormal(long dateMls){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));

        String date = sdf.format(dateMls);

        return date;
    }
}