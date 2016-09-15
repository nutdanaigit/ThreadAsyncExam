package ayp.aug.countasynctask;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Nutdanai on 9/15/2016.
 */
public class MainThreeActivity extends AppCompatActivity implements TestLooper.CallBack {
    private static final String TAG = "MainThreeActivity";
    protected Button mButton;
    protected EditText mEditText;
    protected TextView mTextView;
    private Handler handlerMain;
    private String s;
    private  TestLooper testLooper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.btn_set);
        mEditText = (EditText) findViewById(R.id.edit_txt);
        mTextView = (TextView) findViewById(R.id.txt_view);

        handlerMain = new Handler();

        testLooper = new TestLooper("ThreadName",this);
        testLooper.setHandlerMainThread(handlerMain);
        testLooper.start();
        testLooper.onLooperPrepared();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = mEditText.getText().toString();
                testLooper.addMessage(s);
            }
        });

    }

    @Override
    public void setText(String s) {
        Log.d(TAG,"Check : "+s);
        mTextView.setText(s);
    }
}
