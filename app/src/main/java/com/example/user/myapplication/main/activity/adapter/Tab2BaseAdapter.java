package com.example.user.myapplication.main.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.myapplication.R;
import com.example.user.myapplication.domain.Product;
import com.example.user.myapplication.lockscreen.task.ProductsTask;
import com.example.user.myapplication.util.Constants;

import java.util.concurrent.ExecutionException;

/**
 * Created by Junho on 2016-03-15.
 */
public class Tab2BaseAdapter extends ArrayAdapter<Integer> {

    private Context mContext;

    Product[] products;


    public Tab2BaseAdapter(Context context, int resource, int textViewResourceId, Integer[] objects) {
        super(context, resource, textViewResourceId, objects);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        // inflate layout from xml
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        try {
            products = new ProductsTask().execute().get();
        }catch(InterruptedException e){
        }catch (ExecutionException e){
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.tab2_list_view_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            holder.productIcon = (ImageView)convertView.findViewById(R.id.product_icon);
            holder.productTitle = (TextView)convertView.findViewById(R.id.product_title);
            holder.productDesc = (TextView)convertView.findViewById(R.id.product_desc);
            holder.productStart = (TextView)convertView.findViewById(R.id.product_start);
            holder.productEnd = (TextView)convertView.findViewById(R.id.product_end);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        Glide.with(mContext)
                .load(Constants.IP+products[position].getLoc1())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(300,200)
                .into(holder.productIcon);

        holder.productTitle.setText(products[position].getTitle());
        holder.productDesc.setText(products[position].getDescr());
        holder.productStart.setText(products[position].getSdate());
        holder.productEnd.setText(products[position].getFdate());

        return convertView;



    }

    private class ViewHolder{
        private ImageView productIcon;
        private TextView productTitle;
        private TextView productDesc;
        private TextView productStart;
        private TextView productEnd;


        public ViewHolder(View v){
            productIcon = (ImageView)v.findViewById(R.id.product_icon);
            productTitle = (TextView)v.findViewById(R.id.product_title);
            productDesc = (TextView)v.findViewById(R.id.product_desc);
            productStart = (TextView)v.findViewById(R.id.product_start);
            productEnd = (TextView)v.findViewById(R.id.product_end);

        }

    }


}
