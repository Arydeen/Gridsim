package com.example.gridsim;

import static java.util.Arrays.fill;

import android.content.Context;

import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;


public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    private int image;

    //Constructor
    public ImageAdapter(Context c, int image) {
        mContext = c;
        this.image = image;
    }

    public int getCount() {
        return 256;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Index", "Index: " + position);
                }
            });
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(image);
        return imageView;
    }

    

    // Keep all Images in array
//    public Integer[] mThumbIds = {
//
//    };

}
