package com.grability.elio.grabilitytest.main.adapters;

/**
 * Created by elio on 12/27/16.
 */

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.grability.elio.grabilitytest.R;
import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.main.UI.AppDetailsActivity;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;


public class AppsRecyclerAdapter extends
        RealmRecyclerViewAdapter<App, AppsRecyclerAdapter.AppsViewHolder> {

    public AppsRecyclerAdapter(Context context, @Nullable OrderedRealmCollection<App> data, boolean autoUpdate) {
        super(context, data, autoUpdate);
    }

    @Override
    public AppsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.cell_app, viewGroup, false);
        return new AppsViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(AppsViewHolder holder, int position) {
        App obj = this.getData().get(position);

        holder.imgView.setOnClickListener(v -> {
            Intent myIntent = new Intent(context, AppDetailsActivity.class);
            myIntent.putExtra("app_id", obj.getId());
            context.startActivity(myIntent);
        });
        holder.txtTitle.setText(formatTitle(obj.getTitle()));
        if (!obj.getImages().isEmpty()) {
            Glide.with(context)
                    .load(obj.getImages().get(2).getUrl())
                    .override(100, 100)
                    .fitCenter()
                    .into(holder.imgView);
        }
    }

    private String formatTitle(String title) {
        int index = title.indexOf("-");
        return title.substring(0, index);
    }

    public class AppsViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imgView;

        public AppsViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
            YoYo.with(Techniques.RotateIn)
                    .duration(700)
                    .playOn(itemView.findViewById(R.id.imgView));
            YoYo.with(Techniques.Wave)
                    .duration(700)
                    .playOn(itemView.findViewById(R.id.imgSt));
        }
    }
}
