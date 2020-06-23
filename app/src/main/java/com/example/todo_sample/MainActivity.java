package com.example.todo_sample;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ListViewオブジェクトの取得
        ListView listView = (ListView) findViewById(R.id.list);

        //ArrayAdapterオブジェクト生成
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        //Buttonオブジェクト取得
        Button btn = (Button) findViewById(R.id.btn);

        //クリックイベント
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                //項目の追加メソッドの呼び出し
                addStringData();

                //残りToDoのカウント増加
                int count = adapter.getCount();
                ((TextView)findViewById(R.id.text)).setText("" + count);

            }
        });


        //Adapterのセット
        listView.setAdapter(adapter);

        //項目削除
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) adapter.getItem(position);
                adapter.remove(adapter.getItem(position));
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, item + "を削除しました", Toast.LENGTH_SHORT).show();

                //残りToDoカウントの減少
                int count = adapter.getCount();
                ((TextView)findViewById(R.id.text)).setText("" + count);
                return false;
            }
        });
    }


    //項目の追加メソッド

    private void addStringData(){

        //EditTextオブジェクト取得
        EditText edit = (EditText) findViewById(R.id.edit_text);

        //EditText(テキスト)を取得し、アダプタに追加
        adapter.add(edit.getText().toString());
        edit.setText("");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }
}