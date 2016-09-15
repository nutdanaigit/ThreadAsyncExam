package ayp.aug.countasynctask;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * Created by Nutdanai on 9/15/2016.
 */
public class TestLooper extends HandlerThread {
    private Handler handler;
    private Handler handlerMain;
    private CallBack callBack;
    protected String s ="Finish";
    private int MESSAGE_CODE = 2;
    protected int count;

    public TestLooper(String ThreadName, Context ctx) {
        super(ThreadName);
        callBack = (CallBack) ctx;
    }

    interface CallBack{
        void setText(String s);
    }

    public void setHandlerMainThread(Handler handlerMainThread){
        this.handlerMain = handlerMainThread;
    }


    @Override
    protected void onLooperPrepared() {
       handler = new Handler(){
           @Override
           public void handleMessage(Message msg) {
                if(msg.what == MESSAGE_CODE){
                    count = Integer.valueOf(msg.obj.toString());
                    while(true){
                        try{
                            handlerMain.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(count >= 0) {
                                        callBack.setText(String.valueOf(count));
                                    }else {
                                        callBack.setText(s);
                                    }
                                }
                            });
                            sleep(1000);
                        }catch (Exception e){

                        }
                        if(count < 0){
                            break;
                        }
                        count--;

                    }
                }

           }
       };
    }

    public void addMessage(String s){
        Message mgs = handler.obtainMessage(MESSAGE_CODE,s);
        mgs.sendToTarget();
    }
}
