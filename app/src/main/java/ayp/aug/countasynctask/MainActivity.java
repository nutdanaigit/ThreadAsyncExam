package ayp.aug.countasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.EOFException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private EditText mEditText;
    private TextView mTextView;
    private String s;
    private int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.btn_set);
        mEditText = (EditText) findViewById(R.id.edit_txt);
        mTextView = (TextView) findViewById(R.id.txt_view);




        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = mEditText.getText().toString();
                mTextView.setText(s);
                try{
                    temp = Integer.valueOf(s);
                    new PearlTest().execute(new Integer[]{0,temp});
                }catch (Exception e){
                    mTextView.setText("You must set Integer Number");
                }
            }
        });





    }

    public class PearlTest extends AsyncTask<Integer,Integer, String>{
        @Override
        protected String doInBackground(Integer ... args) {
            Integer integer = args[1];
            while (true){
               integer--;
                try{
                    Thread.sleep(1000);
                }catch (Exception e){

                }
                publishProgress(integer);

                if(integer == 0){
                    break;
                }
            }
            return "Finish";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mTextView.setText(values[0].toString());
        }

        @Override
        protected void onPostExecute(String s) {
            mTextView.setText(s);
        }
    }
}
