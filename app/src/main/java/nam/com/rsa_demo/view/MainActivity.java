package nam.com.rsa_demo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import nam.com.rsa_demo.R;
import nam.com.rsa_demo.event.MainEvent;
import nam.com.rsa_demo.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainEvent , View.OnClickListener {
    Button simple_demo_rsa,simple_edit,simple_list_rsa;
    MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        setupPresenter();
        initView();
        onClickView();
    }
    private void initView(){
        simple_demo_rsa = findViewById(R.id.simple_demo_rsa);
        simple_edit = findViewById(R.id.simple_edit);
        simple_list_rsa = findViewById(R.id.simple_list_rsa);
    }
    private void onClickView(){
        simple_demo_rsa.setOnClickListener(this);
        simple_edit.setOnClickListener(this);
        simple_list_rsa.setOnClickListener(this);
    }
    private void setupPresenter(){
        presenter = new MainPresenter(this);
        presenter.onMainEvent(this);
        presenter.generateKeys();
    }

    @Override
    public void onViewSuccess() {
        try {
            presenter.getPrivateKey();
            presenter.getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewFails() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            boolean isCheck = false;
            for (int i  : grantResults){
                if (i == PackageManager.PERMISSION_GRANTED){
                    isCheck = true;
                }else {
                    isCheck = false;
                }
            }
            if (isCheck){
                try {
                    presenter.getPublicKey();
                    presenter.getPrivateKey();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(this, "Please accept permission for using this application", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == simple_demo_rsa){
            onGo(SimpleRSAActivity.class);
        }
        if (v == simple_edit){
            onGo(SimpleEditAcitivity.class);
        }
        if (v == simple_list_rsa){
            onGo(SimpleListRSAActivity.class);
        }
    }
    private void onGo(Class c){
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }
}
