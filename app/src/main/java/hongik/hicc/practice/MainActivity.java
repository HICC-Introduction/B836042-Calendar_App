package hongik.hicc.practice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper = null ;

    CalendarView simpleCalendarView;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //test2
        setTheme(R.style.Theme_Calendar_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView dateTxtView = findViewById(R.id.dateTxtView);
        //캘린더뷰 날짜 이상함 한달 느리게 나옴....젠장
        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
                /*String dateText = month + "월 "+dayOfMonth+"일의 일정";
                dateTxtView.setText(dateText);*/

            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        String todayText = sdf.format(simpleCalendarView.getDate())+"의 일정";
        dateTxtView.setText(todayText);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "일정 추가 팝업창", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
                load_values();
            }
        });

        list = new ArrayList<>();

        init_tables();
        load_values();

        RecycleAdapter adapter = new RecycleAdapter(list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        SwipeController swipeController = new SwipeController();
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);


    }

    private void init_tables() {
        dbHelper = new DBHelper(this);
    }

    private void load_values() {

        SQLiteDatabase sqlDB2 = dbHelper.getWritableDatabase();
       // sqlDB2.execSQL(ContactDB.SQL_TEST) ;//테스트용-ㅊ삭제할것

        SQLiteDatabase sqlDB = dbHelper.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(ContactDB.SQL_SELECT, null);

        while (cursor.moveToNext()) { // 레코드가 존재한다면
            String name = cursor.getString(1) ;
            list.add(name);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}