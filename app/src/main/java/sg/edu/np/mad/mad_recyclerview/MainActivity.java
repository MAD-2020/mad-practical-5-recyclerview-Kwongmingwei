package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="Recycler View ItemList";

    Button button1;
    List<String> recyclerList=new ArrayList<>();
    EditText nameOfItem;
    RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerItems(recyclerList);
        nameOfItem=findViewById(R.id.addTasks);
        button1=findViewById(R.id.addButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput=nameOfItem.getText().toString();
                if (userInput.isEmpty()){
                    Log.v(TAG,"NO INPUT");
                }
                else{
                    Log.v(TAG,"Adding to list");
                    recyclerList.add(userInput);
                    recyclerItems(recyclerList);
                    showNewEntry(myRecyclerView,(ArrayList)recyclerList);
                    nameOfItem.setText("");
                }
            }
        });



    }
    private void recyclerItems(List<String>rList){
        RecyclerView myRecyclerView=findViewById(R.id.checkRecycler);
        recyclerAdaptor rAdaptor=new recyclerAdaptor(rList);
        LinearLayoutManager rLayoutManager=new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(rLayoutManager);
        myRecyclerView.setAdapter(rAdaptor);
    }

    ///**
     //* Upon calling this method, the keyboard will retract
    // * and the recyclerview will scroll to the last item
    // *
    // * @param rv RecyclerView for scrolling to
    // * @param data ArrayList that was passed into RecyclerView
     //*/


    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }

}
