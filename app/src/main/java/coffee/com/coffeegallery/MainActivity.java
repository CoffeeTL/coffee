package coffee.com.coffeegallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import coffee.com.coffeegallery.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
