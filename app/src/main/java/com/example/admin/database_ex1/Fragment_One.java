package com.example.admin.database_ex1;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_One extends Fragment {

EditText ed1,ed2;
    Button b1,b2;
    TextView tv;
    MyDatabase myDatabase;
    public Fragment_One() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //12open data base,onthe top declare Mydata base
         myDatabase=new MyDatabase(getActivity());
        myDatabase.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__one, container, false);
        //11step
        ed1= (EditText) view.findViewById(R.id.et1);
        ed2= (EditText) view.findViewById(R.id.et2);
        b1= (Button) view.findViewById(R.id.button1);
        b2= (Button) view.findViewById(R.id.button2);
        tv= (TextView) view.findViewById(R.id.textview1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //13=insert data into table(student)
                String name=ed1.getText().toString();
                String course=ed2.getText().toString();
                myDatabase.insertStudent(name,course);//REQUEST FOR DB FILE FOR INSERTION
                ed1.setText("");
                ed2.setText("");
                ed1.requestFocus();
                Toast.makeText(getActivity(), "INSERTED A ROW", Toast.LENGTH_SHORT).show();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //14---read data into table(student)
                Cursor c=myDatabase.queryStudent();
                if (c!=null){
                    //that means there are some rows in our table
                    StringBuilder sb=new StringBuilder();
                    //it means we are reading next row
                    while (c.moveToNext()){
                        int sno=c.getInt(0);
                        String name=c.getString(1);
                        String course=c.getString(2);
                        sb.append(sno+" : "+name+" : "+course+"\n");
                        //LET US APPLY ON TEXTVIEW
                        tv.setText(sb.toString());
                    }
                }

            }
        });
        return  view;
    }

}
