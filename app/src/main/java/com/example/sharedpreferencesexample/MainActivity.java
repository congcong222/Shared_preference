package com.example.sharedpreferencesexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View .OnClickListener{
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    EditText etData;
    Button btnSave;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initPreferences();

        etData = (EditText) findViewById(R.id.et_data);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnClear = (Button) findViewById(R.id.btn_clear);

        String savedData = sharedPreferences.getString("DATA", "");
        etData.setText(savedData);

        btnSave.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    private void initPreferences() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        if (v == btnSave) {
            // Nếu click vào nút Save, ta sẽ lưu dữ liệu lại.
            String data = etData.getText().toString();
            // "DATA" là key, data tham số thứ 2 là value.
            editor.putString("DATA", data);
            editor.commit();
        } else if (v == btnClear) {
            // Nếu click vào nút Clear, ta sẽ xoá dữ liệu đi.
            etData.setText("");
            editor.clear();
            editor.commit();
        }
    }
}