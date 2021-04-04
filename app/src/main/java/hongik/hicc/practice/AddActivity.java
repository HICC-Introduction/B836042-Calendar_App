package hongik.hicc.practice;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import java.util.Date;
import java.text.SimpleDateFormat;

public class AddActivity extends AppCompatActivity {

    private Button cancelBtn, saveBtn, dateBtn;
    private EditText scheduleName;
    private String dateMessage;
    private DBHelper dbHelper;
    private  Date date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_Calendar_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateBtn = findViewById(R.id.dateBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        saveBtn = findViewById(R.id.addBtn);


        Button dateBtn = findViewById(R.id.dateBtn);
        dateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDatePicker(v);
            }
        });
        saveBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSchedule();
                finish();
            }
        });

        cancelBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void addSchedule(){
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        EditText titleTxt = findViewById(R.id.titleTxt);
        String title = titleTxt.getText().toString();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(title == null ) title = "None";
        if(dateMessage == null ) date = new Date();
        dateMessage = dateFormat.format(date);

        String insertSQL = ContactDB.SQL_INSERT+" ( '" +
                title +"', '"+
                dateMessage
                + "')";
        Log.d("JIHO ","jhtest : "+insertSQL);
        db.execSQL(insertSQL);


    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        dateMessage = (year_string + "-" +  month_string + "-" + day_string);
        date = java.sql.Date.valueOf(dateMessage);
        dateBtn.setText(dateMessage);

    }

}
