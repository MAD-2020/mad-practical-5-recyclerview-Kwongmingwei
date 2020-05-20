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

    Button button1= findViewById(R.id.addButton);
    ArrayList<String> recyclerList=new ArrayList<>();
    EditText nameOfItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameOfItem=findViewById(R.id.addTasks);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput=nameOfItem.getText().toString();
                if (userInput.isEmpty()){
                    Log.v(TAG,"NO INPUT");
                }
                else{
                    Log.v(TAG,"Adding to list");
                    addNewItem(userInput);
                }
            }
        });







        RecyclerView listRecycler=findViewById(R.id.checkRecycler);
        recyclerAdaptor rAdaptor=new recyclerAdaptor(recyclerList);
        LinearLayoutManager rLayoutManager=new LinearLayoutManager(this);
        listRecycler.setLayoutManager(rLayoutManager);
        listRecycler.setItemAnimator(new DefaultItemAnimator());
        listRecycler.setAdapter(rAdaptor);

    }

    ///**
     //* Upon calling this method, the keyboard will retract
    // * and the recyclerview will scroll to the last item
    // *
    // * @param rv RecyclerView for scrolling to
    // * @param data ArrayList that was passed into RecyclerView
     //*/

    private void addNewItem(String item){
        recyclerList.add(item);
        Log.v(TAG,"Adding new item to the recycler view");
    }



    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }
}
