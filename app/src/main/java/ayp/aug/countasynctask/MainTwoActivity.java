package ayp.aug.countasynctask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Nutdanai on 9/14/2016.
 */
public class MainTwoActivity extends AppCompatActivity {
    private Button mButton;
    private EditText mEditText;
    private TextView mTextView;
    private Handler handler ;
    private String s;
    private int temp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.btn_set);
        mEditText = (EditText) findViewById(R.id.edit_txt);
        mTextView = (TextView) findViewById(R.id.txt_view);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mTextView.setText(String.valueOf(temp));
            }
        };

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = mEditText.getText().toString();
                mTextView.setText(s);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        temp = Integer.valueOf(s);
                        while (true){
                            try{
                                Thread.sleep(1000);
                                handler.sendEmptyMessage(0);
                            }catch (Exception e){

                            }
                            if(temp == 0){
                                break;
                            }
                            temp--;
                        }
                    }
                }).start();
            }
        });



    }
}
