package nam.com.rsa_demo.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import nam.com.rsa_demo.R;
import nam.com.rsa_demo.event.SimpleEditRSAEvent;
import nam.com.rsa_demo.presenter.SimpleListEditRSAPresenter;

public class SimpleEditAcitivity extends AppCompatActivity implements View.OnClickListener, SimpleEditRSAEvent {
    EditText original;
    Button back, add;
    SimpleListEditRSAPresenter presenter;
    JSONArray jsonArray;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_rsa_edit);
        init();

    }

    private void init() {
        setupPresenter();
        initView();
        onViewClick();
    }

    private void setupPresenter() {
        presenter = new SimpleListEditRSAPresenter(this);
        presenter.onSimpleEditRSAPresenter(this);
        try {
            presenter.getList();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        original = findViewById(R.id.original);
    }

    private void onViewClick() {
        add.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == add) {
            try {
                presenter.pushNewArray(jsonArray,original.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (v == back) {
            finish();
        }
    }

    @Override
    public void onEmptyRSA(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public void onArrayRSA(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        Log.e("20202002",jsonArray.toString());
    }

    @Override
    public void onPushRSA(String result) {
        Log.e("20200202020",result);
    }

    @Override
    public void onEmpty(String result) {
        Log.e("20200202",result);
    }
}
