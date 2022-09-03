package com.example.resturentadminside.Order_Related_Stuff;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.resturentadminside.R;

import java.util.List;

public class Order_List_Adapter extends RecyclerView.Adapter<Order_List_Adapter.ImageViewHolder> {
    private Context mContext;
    private List<Firebase_Order_Jobs> mUploads;
    private OnItemClickListener mListener;

    public Order_List_Adapter(Context context, List<Firebase_Order_Jobs> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.order_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Order_List_Adapter.ImageViewHolder holder, int position) {
        Firebase_Order_Jobs uploadCurrent = mUploads.get(position);

        holder.txt_resturent_name.setText(uploadCurrent.getOrder_resturent_name());
        holder.txt_order_item.setText(uploadCurrent.getOrder_item());
        holder.txt_order_quantity.setText(uploadCurrent.getOrder_quantity());
        holder.txt_order_price.setText(uploadCurrent.getOrder_price());
        holder.txt_order_location.setText( uploadCurrent.getOrder_location());
        holder.txt_order_type.setText(uploadCurrent.getOrder_type());
        holder.txt_additional_nots.setText(uploadCurrent.getOrder_additional_notes());


//

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        ////////////////////////////////

        public TextView txt_order_item,txt_resturent_name,
                txt_order_quantity,txt_order_price,txt_order_location,txt_order_type,txt_additional_nots;

        ////////////////////////////////////////////

        public ImageViewHolder(View itemView) {
            super(itemView);


            txt_order_item =itemView.findViewById(R.id.txt_order_item);
            txt_resturent_name =itemView.findViewById(R.id.txt_resturent_name);
            txt_order_quantity =itemView.findViewById(R.id.txt_order_quantity);
            txt_order_price =itemView.findViewById(R.id.txt_order_price);
            txt_order_location =itemView.findViewById(R.id.txt_order_location);
            txt_order_type =itemView.findViewById(R.id.txt_order_type);
            txt_additional_nots =itemView.findViewById(R.id.txt_additional_nots);



            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatever = menu.add(Menu.NONE, 1, 1, "Do whatever");
            MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");

            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    switch (item.getItemId()) {
                        case 1:
                            mListener.onWhatEverClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;
    }
}



