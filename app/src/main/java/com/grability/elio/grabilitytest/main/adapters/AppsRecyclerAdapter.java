package com.grability.elio.grabilitytest.main.adapters;

/**
 * Created by elio on 12/27/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grability.elio.grabilitytest.R;
import com.grability.elio.grabilitytest.entities.App;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;


public class AppsRecyclerAdapter extends
        RealmRecyclerViewAdapter<App, AppsRecyclerAdapter.AppsViewHolder> {

    public AppsRecyclerAdapter(Context context, OrderedRealmCollection<App> data, boolean autoUpdate) {
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
        holder.txtTitle.setText(obj.getTitle());
        //holder.txtDescription.setText(obj.getDescription());
        if (!obj.getImages().isEmpty()) {
            Glide.with(context)
                    .load(obj.getImages().get(0).getUrl())
                    .override(100, 100)
                    .fitCenter()
                    .into(holder.imgView);
        }

    }

    public class AppsViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView imgView;

        public AppsViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            //txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
            // cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
