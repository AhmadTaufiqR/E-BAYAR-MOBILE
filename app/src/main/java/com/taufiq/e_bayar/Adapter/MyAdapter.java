package com.taufiq.e_bayar.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.taufiq.e_bayar.Model.ModelTagihan;
import com.taufiq.e_bayar.R;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.HolderSPP> {

    Context context;
    ArrayList<ModelTagihan> tagihan;
    TextView textView;
    public ArrayList<ModelTagihan> checkedmodelTagihan = new ArrayList<>();

    public MyAdapter(Context context, ArrayList<ModelTagihan> tagihan, TextView textView) {
        this.context = context;
        this.tagihan = tagihan;
        this.textView = textView;
    }

    @NonNull
    @Override
    public HolderSPP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_detail_spp, null);
        return new HolderSPP(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HolderSPP holder, int position) {
        ModelTagihan modelTagihan = tagihan.get(position);
        holder.bulan.setText(tagihan.get(position).getBulan());
        holder.harga.setText(tagihan.get(position).getHarga().toString());
        holder.checkBox.setOnCheckedChangeListener(null);

        //set change value when chaked
        holder.checkBox.setChecked(modelTagihan.isChecked());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (modelTagihan.setChecked(isChecked)){
                checkedmodelTagihan.add(tagihan.get(position));
                updateTotal();
            } else {
                checkedmodelTagihan.remove(tagihan.get(position));
            }

        });

    }

    @SuppressLint("SetTextI18n")
    private void updateTotal() {
        int total = 0;
        for (ModelTagihan item : tagihan) {
            if (item.isChecked()) {
                total += item.getHarga();
            }
        }
        textView.setText("Rp."+total);
    }

    @Override
    public int getItemCount() {
        return tagihan.size();
    }


    public static class HolderSPP extends RecyclerView.ViewHolder {
        TextView bulan, harga;
        CheckBox checkBox;

        public HolderSPP(@NonNull View itemView) {
            super(itemView);
            bulan = itemView.findViewById(R.id.nama_bulan);
            harga = itemView.findViewById(R.id.price);
            checkBox = itemView.findViewById(R.id.checkbox_spp);

        }

    }
}
