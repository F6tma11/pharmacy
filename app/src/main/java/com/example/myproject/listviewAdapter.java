package com.example.myproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class listviewAdapter extends BaseAdapter {


    ArrayList<cart>cart;
    int layoutId;
    Context context;
    public listviewAdapter(Context context,int layoutId,ArrayList<cart>cart){
        this.context=context;
        this.layoutId=layoutId;
        this.cart=cart;

    }
    @Override
    public int getCount() {
        return cart.size();
    }

    @Override
    public Object getItem(int i) {
        return cart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layout=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layout.inflate(layoutId,viewGroup ,false);

        }
        ImageView im=view.findViewById(R.id.image_cart);
        im.setImageBitmap(convertCompressedByteArrayToBitmap(cart.get(i).getImg()));
        TextView t1=view.findViewById(R.id.cart_name);
        t1.setText(cart.get(i).getName());
        TextView t2=view.findViewById(R.id.cart_price);
        t2.setText(cart.get(i).getPrice());
        TextView t3=view.findViewById(R.id.cart_count);
        t2.setText(cart.get(i).getCount());
        return view;
    }



    public static Bitmap convertCompressedByteArrayToBitmap(byte[] src) {
        ByteArrayInputStream stream = new ByteArrayInputStream(src);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        return bitmap;
    }
}
