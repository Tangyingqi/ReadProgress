package com.tyq.readprogress;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by tyq on 2015/9/13.
 */
public class CustomDialog extends Dialog {

    private EditText ed_page;
    private Button btn_pos,btn_nag;


    public CustomDialog(Context context) {
        super(context);
        setCustomDialog();
    }

    private void setCustomDialog(){
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_custom, null);
        super.setContentView(mView);

        Window dialogWindow = this.getWindow();

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = 700;
        lp.height = 500;
        lp.alpha = 0.7f;
        lp.y = 20;
        ed_page = (EditText) findViewById(R.id.et_page_num);
        btn_nag = (Button) findViewById(R.id.nagetiveButton);
        btn_pos = (Button) findViewById(R.id.positiveButton);
    }
    public  View getEditText(){
        return ed_page;
    }

    public View getPosBtn(){
        return btn_pos;
    }

    public View getNegBtn(){
        return btn_nag;
    }


}
