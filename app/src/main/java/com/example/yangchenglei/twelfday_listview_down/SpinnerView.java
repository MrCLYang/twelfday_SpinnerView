package com.example.yangchenglei.twelfday_listview_down;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * 作者：杨成雷
 * 时间：2018/8/3:15:25
 */
public class SpinnerView extends RelativeLayout implements View.OnClickListener {
    private EditText mEtInput;
    private ImageView mIvArroe;
    private PopupWindow mWindow;
    private ListView listView;
    private ListAdapter adapter;
    private AdapterView.OnItemClickListener  mListener;

    public SpinnerView(Context context) {
        super(context);
    }

    public SpinnerView(Context context, AttributeSet attrs) {

        super(context, attrs);
        //将xml和class实现绑定的封装view
        View view = View.inflate(context, R.layout.spinner, this);
        mEtInput = (EditText) findViewById(R.id.et_input);
        mIvArroe = (ImageView) findViewById(R.id.iv_arrow);
        mIvArroe.setOnClickListener(this);

    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){

      this.mListener =listener;
    }

    @Override
    public void onClick(View v) {
        if (adapter==null){
            throw new RuntimeException("请调用我们的setAdapter");
        }
        Log.i("ycl", "点击图片");
        //点击弹出一个数据层,第一个参数是view，pop的宽高、
        if (mWindow == null) {
            listView = new ListView(getContext());
            listView.setAdapter(adapter);
            listView.setBackgroundResource(R.drawable.listview_background);



            int width = mEtInput.getWidth();
            int height = 280;
            mWindow = new PopupWindow(listView, width, height);
            //设置获取焦点
            mWindow.setFocusable(true);
            mWindow.setOutsideTouchable(true);
            mWindow.setBackgroundDrawable(new ColorDrawable());
        }
        listView.setOnItemClickListener(mListener);
        mWindow.showAsDropDown(mEtInput);
    }


    public void setAdapter(ListAdapter adapter){
        this.adapter =adapter;

    }

    public void setText(String data) {
        mEtInput.setText(data);
    }

    public void setSelection(int length) {
        mEtInput.setSelection(length);
    }

    public void dismiss() {
        mWindow.dismiss();
    }
}
