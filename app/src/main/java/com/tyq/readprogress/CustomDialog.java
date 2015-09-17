package com.tyq.readprogress;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by tyq on 2015/9/13.
 */
public class CustomDialog extends Dialog {

    private EditText ed_page;
   // private Button btn_nag;
    private ImageButton btn_pos;


    public CustomDialog(Context context,int theme) {
        super(context,theme);
        setCustomDialog();
    }

    private void setCustomDialog(){
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_custom, null);
        super.setContentView(mView);

        Window dialogWindow = this.getWindow();

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        lp.width = 800;
        lp.height = 600;

        ed_page = (EditText) findViewById(R.id.et_page_num);
        //btn_nag = (Button) findViewById(R.id.nagetiveButton);
        btn_pos = (ImageButton) findViewById(R.id.positiveButton);
    }
    public  View getEditText(){
        return ed_page;
    }

    public View getPosBtn(){
        return btn_pos;
    }

  //  public View getNegBtn(){
      //  return btn_nag;
  //  }


}
