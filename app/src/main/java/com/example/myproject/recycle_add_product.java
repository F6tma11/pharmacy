package com.example.myproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class recycle_add_product extends RecyclerView.Adapter<recycle_add_product.ViewHolder> {

    ArrayList<card> card;
    Context context;

    DatabaseHelper dbHelber;


    public recycle_add_product(@NonNull Context context, ArrayList<card> card) {
        this.card = card;
        this.context = context;

    }

    @NonNull
    @Override
    public recycle_add_product.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageBitmap(convertCompressedByteArrayToBitmap(card.get(position).getImg()));
        holder.textView.setText(card.get(position).getName());
        holder.textView1.setText(card.get(position).getPrice());
        holder.textView2.setText(card.get(position).getCategory());

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        holder.cardView1.setOnClickListener(v -> {
            Intent intent = new Intent(context, Update_Prodoct.class);
            intent.putExtra("card",card.get(position));

            context.startActivity(intent);

        });

        holder.cardView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                dbHelber=new DatabaseHelper(context);
                dbHelber.deleteEntry(card.get(position).getId());
                card.remove(card.get(position));
                context.startActivity(new Intent(context,admin.class));
                Toast.makeText(context, "Delete..", Toast.LENGTH_SHORT).show();

                return true;

            }
        });

    }


    @Override
    public int getItemCount() {
        return card.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView1;
        ImageView imageView;
        TextView textView, textView1, textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView1 = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textViewName);
            textView1 = itemView.findViewById(R.id.textViewPrice);
            textView2 = itemView.findViewById(R.id.textViewCategory);
        }
    }


    public static byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)) {
            return stream.toByteArray();

        } else if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)) {
            return stream.toByteArray();

        } else if (bitmap.compress(Bitmap.CompressFormat.WEBP, 100, stream))
            return stream.toByteArray();

        return stream.toByteArray();

    }

    public static Bitmap convertCompressedByteArrayToBitmap(byte[] src) {
        ByteArrayInputStream stream = new ByteArrayInputStream(src);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        return bitmap;
    }
}












