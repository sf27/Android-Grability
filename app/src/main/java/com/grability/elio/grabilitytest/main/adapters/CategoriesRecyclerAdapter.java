package com.grability.elio.grabilitytest.main.adapters;

/**
 * Created by elio on 12/27/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.grability.elio.grabilitytest.R;
import com.grability.elio.grabilitytest.entities.Category;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;


public class CategoriesRecyclerAdapter extends
        RealmRecyclerViewAdapter<Category, CategoriesRecyclerAdapter.AppsViewHolder> {

    public CategoriesRecyclerAdapter(Context context, OrderedRealmCollection<Category> data, boolean autoUpdate) {
        super(context, data, autoUpdate);
    }

    @Override
    public AppsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.cell_category, viewGroup, false);
        return new AppsViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(AppsViewHolder holder, int position) {
        Category obj = this.getData().get(position);
        holder.txtName.setText(obj.getName());
    }

    public class AppsViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;

        public AppsViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            YoYo.with(Techniques.RotateIn)
                    .duration(700)
                    .playOn(itemView.findViewById(R.id.txtName));
        }
    }
}
