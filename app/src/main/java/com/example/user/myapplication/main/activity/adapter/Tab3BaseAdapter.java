package com.example.user.myapplication.main.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;

/**
 * Created by Junho on 2016-03-13.
 */
public class Tab3BaseAdapter extends ArrayAdapter<Integer> {

    private Context mContext;
    public Tab3BaseAdapter(Context context, int resource, int textViewResourceId, Integer[] objects) {
        super(context, resource, textViewResourceId, objects);
        this.mContext = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        // inflate layout from xml
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.tab3_list_view_item_layout, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            holder.image = (ImageView)convertView.findViewById(R.id.consumeitem);
            holder.text =(TextView)convertView.findViewById(R.id.consumetext);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        //holder.image.setImageResource(getItem(position));
        //getItem은 Integer[] 로 들어오는건데 이걸 Consume으로 대체해서
        //holder에서 ImageView를 Text와 같이 꾸밀 수 있지않을까합니다.
        holder.image.setImageResource(getItem(position));
        holder.text.setText("TEMP"+position);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"ToastTest : "+position,Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private ImageView image;
        private TextView text;
        public ViewHolder(View v) {
            image = (ImageView)v.findViewById(R.id.image);
            text = (TextView)v.findViewById(R.id.consumetext);
        }
    }

}
