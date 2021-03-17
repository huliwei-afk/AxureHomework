package com.example.axurehomework;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirstFragment extends Fragment {

    private String fragName;

    public FirstFragment(String fragName){
        this.fragName = fragName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment,container,false);
        RecyclerView msgRecyclerView = (RecyclerView)view.findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        msgRecyclerView.setLayoutManager(layoutManager);
        MsgAdapter adapter = new MsgAdapter(getMsgs());
        msgRecyclerView.setAdapter(adapter);
        return view;
    }

    int[] msgArray = new int[]{ R.drawable.timg3, R.drawable.timg13, R.drawable.timg22 };

    private List<Msg> getMsgs(){
        List<Msg> msgList = new ArrayList<>();
        for(int i = 0; i <= 20; i++){
            Msg msg = new Msg();
            msg.setName("Msg " + i);
            Random random = new Random();
            int randomNumber = random.nextInt(3);
            msg.setMsgId(msgArray[randomNumber]);
            msg.setContent(getRandomLengthContent("This is news context" + i + "."));
            msgList.add(msg);
        }
        return msgList;
    }

    private String getRandomLengthContent(String content){
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i< length; i++){
            stringBuilder.append(content);
        }
        return stringBuilder.toString();
    }

    class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

        private List<Msg>mMsgList;

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView msgImage;
            TextView msgText;

            public ViewHolder(View view){
                super(view);
                msgImage = (ImageView)view.findViewById(R.id.msg_image);
                msgText = (TextView)view.findViewById(R.id.msg_name);

            }
        }

        public MsgAdapter(List<Msg> msgList){
            mMsgList = msgList;
        }

        public MsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent,false);
            final MsgAdapter.ViewHolder holder = new MsgAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder holder, int position) {
            Msg msg = mMsgList.get(position);
            holder.msgImage.setImageResource(msg.getMsgId());
            holder.msgText.setText(msg.getName());
        }

        @Override
        public int getItemCount() {
            return mMsgList.size();
        }
        }


}
