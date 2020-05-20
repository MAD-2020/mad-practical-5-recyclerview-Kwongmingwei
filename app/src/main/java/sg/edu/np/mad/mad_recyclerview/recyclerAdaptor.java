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
import java.util.List;

public class recyclerAdaptor extends RecyclerView.Adapter<recyclerViewHolder> {

    private static final String TAG="recycler view adaptor";

    private List<String> list_items= new ArrayList<String>(){};

    public recyclerAdaptor(List<String> obj){
        this.list_items=obj;
    }

    @NonNull
    @Override
    public recyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        recyclerViewHolder holder =new recyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final recyclerViewHolder holder,final int position){
        String list_member=list_items.get(position);
        holder.txt.setText(list_member);
        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setMessage("Would you like to delete this item?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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

    @Override
    public int getItemCount(){
        return list_items.size();
    }



}
