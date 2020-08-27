package com.hieuminh.exercise3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNumbers[] = new EditText[3];
    private Button solve;
    private ListView lv;
//    private HashMap<String, String> result = new HashMap<>();
    private List<HashMap<String, String>> list = new ArrayList<>();
    private SimpleAdapter adapter;
    private double number[] = new double[3], delta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
//        result.put("xxxxxxx", "yyyyyyyyyyyyyy");
        adapter = new SimpleAdapter(this, list, R.layout.frame_lv,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});
        lv.setAdapter(adapter);
    }

    private void connectView() {
        etNumbers[0] = findViewById(R.id.numberA);
        etNumbers[1] = findViewById(R.id.numberB);
        etNumbers[2] = findViewById(R.id.numberC);
        solve = findViewById(R.id.solve);
        lv = findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,detailActivity.class);
                HashMap<String,String> value = list.get(i);
                intent.putExtra("text1",value.get("First Line"));
                intent.putExtra("text2",value.get("Second Line"));
                startActivity(intent);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        solve.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.solve) {
            for (int i = 0; i < 3; i++) {
                if (etNumbers[i].getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter full data", Toast.LENGTH_SHORT).show();
                    return;
                }
                number[i] = Double.parseDouble(etNumbers[i].getText().toString());
            }
            String value[] = GiaiPTB2(number);
            HashMap<String, String> result = new HashMap<>();
            result.put(value[0],value[1]);
            Iterator it = result.entrySet().iterator();
            while (it.hasNext()) {
                HashMap<String, String> resultsMap = new HashMap<>();
                Map.Entry pair = (Map.Entry) it.next();
                resultsMap.put("First Line", pair.getKey().toString());
                resultsMap.put("Second Line", pair.getValue().toString());
                list.add(resultsMap);
            }
            adapter.notifyDataSetChanged();
        }
    }

    public String[] GiaiPTB2(double[] numbers) {
        String bieuthuc = numbers[0] + "*x^2 + " + numbers[1] + "*x + " + numbers[2] + " = 0";
        String ketqua;
        if (numbers[0] == 0) {
            if (numbers[1] == 0) {
                if (numbers[2] == 0) {
                    ketqua = "phuong trinh vo so nghiem";
                } else {
                    ketqua = "phuong trinh vo nghiem";
                }
            } else {
                ketqua = "x = " + (-numbers[2] / numbers[1]);
            }
        } else {
            delta = numbers[1] * numbers[1] - 4 * numbers[0] * numbers[2];
            if (delta < 0) {
                ketqua = "phuong trinh vo nghiem";
            } else if (delta == 0) {
                ketqua = "x = " + (-numbers[1] / (2 * numbers[0]));
            } else {
                ketqua = "x1 = " + ((-numbers[1] - Math.sqrt(delta)) / (2 * numbers[0]))
                        + ", x2 = " + ((-numbers[1] + Math.sqrt(delta)) / (2 * numbers[0]));
            }
        }
        return new String[] {bieuthuc,ketqua};
    }
}