package com.example.yangchenglei.twelfday_listview_down;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {

    private SpinnerView mSpinnerView;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mSpinnerView = (SpinnerView) findViewById(R.id.sv);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            mDatas.add(100 + i + "我很帅");
        }
        mSpinnerView.setAdapter(new MyAdapter());
        mSpinnerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
                String data = mDatas.get(position);
                mSpinnerView.setText(data);
                mSpinnerView.setSelection(data.length());
                //隐藏popwindow
                mSpinnerView.dismiss();
            }
        });
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
                convertView = View.inflate(DemoActivity.this, R.layout.item, null);
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
