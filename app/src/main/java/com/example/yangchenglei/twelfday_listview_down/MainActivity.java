package com.example.yangchenglei.twelfday_listview_down;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private EditText mEtInput;
    private ImageView mIvArroe;
    private List<String> mDatas;
    private ListView listView;
    private PopupWindow mWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtInput = (EditText) findViewById(R.id.et_input);
        mIvArroe = (ImageView) findViewById(R.id.iv_arrow);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            mDatas.add(100 + i + "我很帅");
        }
        mIvArroe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("ycl", "点击图片");
        //点击弹出一个数据层,第一个参数是view，pop的宽高、
        if (mWindow == null) {
            listView = new ListView(this);
            listView.setAdapter(new MyAdapter());
            listView.setBackgroundResource(R.drawable.listview_background);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //设置输入框内容
                    String data = mDatas.get(position);
                    mEtInput.setText(data);
                    mEtInput.setSelection(data.length());
                    //隐藏popwindow
                    mWindow.dismiss();
                }
            });
            int width = mEtInput.getWidth();
            int height = 280;
            mWindow = new PopupWindow(listView, width, height);
            //设置获取焦点
            mWindow.setFocusable(true);
            mWindow.setOutsideTouchable(true);
            mWindow.setBackgroundDrawable(new ColorDrawable());
        }

        mWindow.showAsDropDown(mEtInput);
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (mDatas != null) {
                return mDatas.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mDatas != null) {
                return mDatas.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Viewholder holder = null;
            if (convertView == null) {
                //没有复用
                convertView = View.inflate(MainActivity.this, R.layout.item, null);
                //初始化viewholder
                holder = new Viewholder();
                convertView.setTag(holder);
                //初始化item的view TODD:
                holder.ivUser = (ImageView) convertView.findViewById(R.id.user);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.ivDelete = (ImageView) convertView.findViewById(R.id.delete);


            } else {
                //复用的时候
                holder = (Viewholder) convertView.getTag();
            }
            final String data = mDatas.get(position);
            holder.tvTitle.setText(data);
            //删除按钮
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDatas.remove(data);
                    //UI刷新
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

    class Viewholder {
        ImageView ivUser;
        ImageView ivDelete;
        TextView tvTitle;
    }

}
