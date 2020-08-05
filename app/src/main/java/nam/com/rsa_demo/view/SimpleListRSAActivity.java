package nam.com.rsa_demo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import nam.com.rsa_demo.R;
import nam.com.rsa_demo.presenter.SimpleListEditRSAPresenter;

public class SimpleListRSAActivity extends AppCompatActivity implements View.OnClickListener {
    Button back,add;
    RecyclerView list_rsa;
    SimpleListEditRSAPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_rsa_list);

        init();
    }
    private void init(){
        initView();
        setupPresenter();
        onEventClick();
    }
    private void initView(){
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        list_rsa = findViewById(R.id.list_rsa);
    }
    private void setupPresenter(){
        try {
            presenter = new SimpleListEditRSAPresenter(this);
            presenter.getList();
            presenter.onSimpleListAdapter();
            presenter.setupAdapterList(list_rsa);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void onEventClick(){
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == back){
            finish();
        }
    }

    private void goOn(Class c){
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }

}
