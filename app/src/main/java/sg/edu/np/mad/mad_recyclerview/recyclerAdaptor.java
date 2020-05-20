package sg.edu.np.mad.mad_recyclerview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdaptor extends RecyclerView.Adapter<recyclerViewHolder> {

    private static final String TAG="recycler view adaptor";

    private ArrayList<String> list_items;

    public recyclerAdaptor(ArrayList<String> obj){
        this.list_items=obj;
    }

    public recyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        recyclerViewHolder holder =new recyclerViewHolder(view);
        return holder;
    }
    public void onBindViewHolder(final recyclerViewHolder holder,int position){
        String list_member=list_items.get(position);
        holder.txt.setText(list_member);
        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setMessage("Would you like to delete this item?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG,"User accepts");
                        list_items.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyItemRangeChanged(holder.getAdapterPosition(),list_items.size());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG,"Don't delete item from list");
                    }
                });
                AlertDialog alert= builder.create();
                alert.setTitle("Delete item from list");
                alert.show();
            }
        });
    }
    public int getItemCount(){
        return list_items.size();
    }



}
