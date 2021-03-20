package com.mobiledev.crudfirebase.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobiledev.crudfirebase.Models.Register;
import com.mobiledev.crudfirebase.R;

import java.util.ArrayList;

public class ListViewRegistersAdapter extends BaseAdapter {

    Context context;
    ArrayList<Register> registerData;
    LayoutInflater layoutInflater;
    Register registerModel;

    public ListViewRegistersAdapter(Context context, ArrayList<Register> registerData) {
        this.context = context;
        this.registerData = registerData;
        layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
    }

    @Override
    public int getCount() {
        return registerData.size();
    }

    @Override
    public Object getItem(int position) {
        return registerData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null){
            rowView = layoutInflater.inflate(
                    R.layout.list_registers,
                    null,
                    true);
        }
        TextView serialNumbers = rowView.findViewById(R.id.serialNumbers);
        TextView description = rowView.findViewById(R.id.Description);
        TextView processor = rowView.findViewById(R.id.Processor);
        TextView memoryRam = rowView.findViewById(R.id.memoryRam);
        TextView hardDisk = rowView.findViewById(R.id.HardDisk);
        TextView operativeSystem = rowView.findViewById(R.id.OperativeSystem);
        TextView ports = rowView.findViewById(R.id.Ports);
        TextView connectivity = rowView.findViewById(R.id.Connectivity);
        TextView camera = rowView.findViewById(R.id.Camera);
        TextView dateRegister = rowView.findViewById(R.id.DateRegister);

        registerModel = registerData.get(position);
        serialNumbers.setText(registerModel.getNumberSerial());
        description.setText(registerModel.getDescription());
        processor.setText(registerModel.getProcessor());
        memoryRam.setText(registerModel.getMemoryRam());
        hardDisk.setText(registerModel.getHardDisk());
        operativeSystem.setText(registerModel.getOperativeSystem());
        ports.setText(registerModel.getPorts());
        connectivity.setText(registerModel.getConnectivity());
        camera.setText(registerModel.getCamera());
        dateRegister.setText(registerModel.getDateRegister());

        return rowView;
    }
}
