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

import com.taufiq.e_bayar.R;
import com.taufiq.e_bayar.Model.spp.DataItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.HolderSPP> {
    Context context;
    ArrayList<DataItem> tagihan;
    TextView textView;
    public ArrayList<DataItem> checkedmodelTagihan = new ArrayList<>();

    public Adapter(Context context, ArrayList<DataItem> tagihan, TextView textView) {
        this.context = context;
        this.tagihan = tagihan;
        this.textView = textView;
    }

    @NonNull
    @Override
    public Adapter.HolderSPP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_detail_spp, null);
        return new Adapter.HolderSPP(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter.HolderSPP holder, int position) {
        DataItem modelTagihan = tagihan.get(position);
        holder.bulan.setText(tagihan.get(position).getBulan());
        holder.harga.setText("Rp. "+ tagihan.get(position).getJumlahBayar());
        holder.checkBox.setOnCheckedChangeListener(null);

        //set change value when chaked
        holder.checkBox.setChecked(modelTagihan.isChecked());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (modelTagihan.setChecked(isChecked)){
                checkedmodelTagihan.add(tagihan.get(position));
                updateTotal();
            } else {
                checkedmodelTagihan.remove(tagihan.get(position));
                updateTotal();
            }

        });

    }

    @SuppressLint("SetTextI18n")
    private void updateTotal() {
        double total = 0;
        for (DataItem item : tagihan) {
            if (item.isChecked()) {
                total += (double) item.getJumlahBayar();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
        String hasil = decimalFormat.format(total);
        textView.setText("Rp."+ hasil);
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
