package nam.com.rsa_demo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import nam.com.rsa_demo.R;
import nam.com.rsa_demo.event.SimpleRSAEvent;
import nam.com.rsa_demo.presenter.SimpleRSAPresenter;

public class SimpleRSAActivity extends AppCompatActivity implements View.OnClickListener , SimpleRSAEvent {
    EditText original;
    Button change;
    TextView decrytper,encrypter;
    SimpleRSAPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_rsa);
        init();
    }

    private void init(){
        initView();
        setupPresenter();
        onViewClick();
    }
    private void initView(){
        original = findViewById(R.id.original);
        change = findViewById(R.id.change);
        decrytper = findViewById(R.id.decrytper);
        encrypter = findViewById(R.id.encrypter);
    }
    private void onViewClick(){
        change.setOnClickListener(this);
    }
    private void setupPresenter(){
        presenter = new SimpleRSAPresenter(this);
        presenter.onSimplePresenter(this);
    }

    @Override
    public void onClick(View v) {
        if (v == change){
            try {
                presenter.onDecrypter(original.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSimpleRSADecrypterEvent(String text) {
        decrytper.setText(text);
        try {
            presenter.onEncrypter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSimpleRSAEncrypterEvent(String text) {
        encrypter.setText(text);
    }

    @Override
    public void onEmpty(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
