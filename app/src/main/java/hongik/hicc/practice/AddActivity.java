package hongik.hicc.practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddActivity extends AppCompatActivity {

    private Button cancelBtn, saveBtn;
    private EditText scheduleName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_Calendar_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cancelBtn = findViewById(R.id.cancelBtn);
        saveBtn = findViewById(R.id.addBtn);
        scheduleName = findViewById(R.id.scheduleNameTxt);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSchedule();
                finish();
            }
        });

    }
    private void addSchedule(){

    }
}
