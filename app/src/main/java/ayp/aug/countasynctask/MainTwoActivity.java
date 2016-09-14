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
    private static final int CODE_SEND_COUNT= 2;
    private static final int CODE_SEND_COUNT2 =3;

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
                //Sol 1
//                mTextView.setText(String.valueOf(temp));
                //Sol 2
                if(msg.what == CODE_SEND_COUNT) {
                    mTextView.setText(msg.obj.toString());
                }

            }
        };

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = mEditText.getText().toString();
                mTextView.setText(s);

                new Thread(new Runnable() {
                    private int temp;
                    private Message mgs;

                    @Override
                    public void run() {
                        temp = Integer.valueOf(s);
                        while (true){
                            try{
                                temp--;

                                Thread.sleep(1000);
                                // Sol 2
                                if(temp < 0){
                                    mgs = handler.obtainMessage(CODE_SEND_COUNT,"Finish");
                                    mgs.sendToTarget();
                                    break;
                                } else {

                                    mgs = handler.obtainMessage(CODE_SEND_COUNT,temp);
                                    mgs.sendToTarget();
                                }

                                // Sol 1
//                                handler.sendEmptyMessage(0);
                                // Sol 2
//                                mgs = handler.obtainMessage(CODE_SEND_COUNT,temp);
//                                mgs.sendToTarget();


                            }catch (Exception e){

                            }

//                            if(temp == 0){
//                                mgs = handler.obtainMessage(CODE_SEND_COUNT,"Pearl");
//                                mgs.sendToTarget();
//                                break;
//                            }

                        }
                    }
                }).start();
            }
        });



    }
}
