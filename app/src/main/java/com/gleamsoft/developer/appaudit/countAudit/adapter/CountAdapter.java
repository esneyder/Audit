package com.gleamsoft.developer.appaudit.countAudit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gleamsoft.developer.appaudit.R;
import com.gleamsoft.developer.appaudit.countAudit.clases.Operaciones;
import com.gleamsoft.developer.appaudit.countAudit.model.CountData;

import java.util.List;

/**
 * Created by Developer on 26/08/2016.
 */
public class CountAdapter extends RecyclerView.Adapter<CountAdapter.MyViewHolder> {
Operaciones op;
private List<CountData> countList;
Context context;
public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView id, barcode;
    EditText count;

    public MyViewHolder(View view) {
        super(view);
        id = (TextView) view.findViewById(R.id.idedit);
        barcode = (TextView) view.findViewById(R.id.barcodeedit);
        count = (EditText) view.findViewById(R.id.edtcountedit);
    }
}


public CountAdapter(List<CountData> moviesList, Context ctx) {
    this.countList = moviesList;
    this.context=ctx;
    op=new Operaciones();
}

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.count_list_row, parent, false);

    return new MyViewHolder(itemView);
}

@Override
public void onBindViewHolder(MyViewHolder holder, int position) {
    final CountData c = countList.get(position);
    holder.id.setText(c.getId()+"");
    holder.barcode.setText(c.getBarcode());
    holder.count.setText(c.getCount());
    holder.count.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
           String data=charSequence.toString();
            if(data.isEmpty()){
                Toast.makeText(context, "Actualizando", Toast.LENGTH_SHORT).show();
            }else {
                op.finalCountdown(String.valueOf(c.getId()),
                        charSequence.toString(),
                        c.getCount());
                Toast.makeText(context, "Conteo finalizado", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
         
        }
    });
}

@Override
public int getItemCount() {
    return countList.size();
}
}
