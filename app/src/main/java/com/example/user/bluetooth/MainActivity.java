package com.example.user.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends Activity {


    Button btnTurnOn,btnGetVisible,btnListDevices,btnTurnOff;
    ListView listView;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice>pairedDevice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTurnOn = findViewById(R.id.btnTurnOn);
        btnGetVisible = findViewById(R.id.btnGetVisible);
        btnListDevices = findViewById(R.id.btnListDevices);
        btnTurnOff = findViewById(R.id.btnTurnOff);
        listView=findViewById(R.id.listView);
        BA=BluetoothAdapter.getDefaultAdapter();

        btnTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TurnOn(view);
            }
        });

        btnGetVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetVisible(view);
            }
        });
        btnListDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListDevices(view);
            }
        });

        btnTurnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TurnOff(view);
            }
        });

    }
    public void TurnOn(View view)
    {
        if(!BA.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,0);
            Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
        }
    }
    public void GetVisible(View view)
    {
        Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(intent,0);
    }
    public void ListDevices(View view)
    {
        pairedDevice=BA.getBondedDevices();
        ArrayList arrayList=new ArrayList();
        for(BluetoothDevice bluetoothDevice:pairedDevice)arrayList.add(bluetoothDevice.getName());
        Toast.makeText(getApplicationContext(), "Paired Devices", Toast.LENGTH_SHORT).show();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(arrayAdapter);
    }
    public void TurnOff(View view)
    {
        BA.disable();
        Toast.makeText(getApplicationContext(), "Turned Off", Toast.LENGTH_LONG).show();
    }

}
