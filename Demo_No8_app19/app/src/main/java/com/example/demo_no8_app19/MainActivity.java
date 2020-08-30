package com.example.demo_no8_app19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    long[] totalTime =new long[500];  //存储歌曲时长


    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static int REQUEST_PERMISSION_CODE = 1;


    private ImageView next;
    private ImageView play;
    private ImageView last;
    private TextView singer;
    private TextView song;
    private RecyclerView music_rv;

//    数据源
    private List<LocalMusicBean> mDatas;
    private LocalMusicAdapter adapter;

    private Uri uri;

    //    记录当前正在播放的音乐的位置
    int currnetPlayPosition = -1;

    //    记录暂停音乐时进度条的位置    点击上一首  下一首需要清零  点击屏幕播放需要清零
    int currentPausePositionInSong = 0;
    MediaPlayer mediaPlayer;
    private SeekBar jindutiao;
    private boolean isStop;

    int time =0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            jindutiao.setProgress(msg.what);


        }
    };
    private SimpleDateFormat sdf;
    private long duration;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mediaPlayer = new MediaPlayer();
        mDatas = new ArrayList<>();
//创建适配器
        adapter = new LocalMusicAdapter(this, mDatas);
        music_rv.setAdapter(adapter);
//        设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        music_rv.setLayoutManager(layoutManager);

        //获取权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
            initView();
        }

//        加载本地数据源
        loadLocalMusicData();

//        设置每一项的点击事件
        setEventListener();

        jindutiao.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void setEventListener() {
        /* 设置每一项的点击事件*/
        adapter.setOnItemClickListener(new LocalMusicAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                currentPausePositionInSong = 0;
                currnetPlayPosition = position;
                LocalMusicBean musicBean = mDatas.get(position);

                playMusicInMusicBean(musicBean);

            }
        });
    }

    private void playMusicInMusicBean(LocalMusicBean musicBean) {

        ///获取歌曲时长
        time = (int) totalTime[currnetPlayPosition];


        /*根据传入对象播放音乐*/
        //设置底部显示的歌手名称和歌曲名
        singer.setText(musicBean.getSinger());
        song.setText(musicBean.getSong());
//        stopMusic();
        mediaPlayer.stop();
//                重置多媒体播放器
        mediaPlayer.reset();
//                设置新的播放路径
        try {
            mediaPlayer.setDataSource(musicBean.getPath());
            playMusic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
     * 点击播放按钮播放音乐，或者暂停从新播放
     * 播放音乐有两种情况：
     * 1.从暂停到播放
     * 2.从停止到播放
     * */
    private void playMusic() {

        ////////////////////////////////进度条/////////////////////////////////////////////////



        new Thread(new SeekBarThread()).start();
        jindutiao.setMax(time);
        ////////////////////////////////进度条/////////////////////////////////////////////////

        /* 播放音乐的函数*/
        if (mediaPlayer!=null&&!mediaPlayer.isPlaying()) {
            if (currentPausePositionInSong == 0) {
                try {
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
//                从暂停到播放
                mediaPlayer.seekTo(currentPausePositionInSong);
                mediaPlayer.start();
            }
            play.setImageResource(R.drawable.pause);
        }
    }
    private void pauseMusic() {
        /* 暂停音乐的函数*/
        if (mediaPlayer!=null&&mediaPlayer.isPlaying()) {
            currentPausePositionInSong = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
          play.setImageResource(R.drawable.play);
        }
    }

//
//    private void stopMusic() {
//        /* 停止音乐的函数*/
//        if (mediaPlayer!=null) {
//            mediaPlayer.pause();
//            mediaPlayer.seekTo(0);
//            mediaPlayer.stop();
////            play.setImageResource(R.drawable.play);
//        }
//    }


    private void loadLocalMusicData() {
//        加载本地存储音乐文件到集合中
//        1.获取ContentResolver对象

        ContentResolver resolver = getContentResolver();

//        2.获取本地音乐存储的uri地址

        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        3.开始查询地址

        Cursor cursor = resolver.query(uri, null, null, null, MediaStore.Audio.AudioColumns.IS_MUSIC);
//        4.遍历cursor  找到歌曲的相应信息
        int id = 0;

        int j=0;

        while(cursor.moveToNext()){

                String song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));  //歌曲名称
                String singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)); //歌手名称
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));  //专辑名称
                id++;
                String sid = String.valueOf(id);
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));   //路径

            //歌曲时长   long型需要转换
            duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
            totalTime[j++] = duration;

            sdf = new SimpleDateFormat("mm:ss");
                String time = sdf.format(new Date(duration));

    //            将一行中的数据封装到对象
                LocalMusicBean bean = new LocalMusicBean(sid, song, singer, album, time, path);

                mDatas.add(bean);  //添加元素
//                LocalMusicBean bean1 = new LocalMusicBean("1", "2", uri.toString(), "1", "1", "1");
//                mDatas.add(bean1);

       }



//        数据变化,提示适配器更新
        adapter.notifyDataSetChanged();
    }


    private void initView() {

//        初始化控件
        next = findViewById(R.id.music_bottom_next);
        play = findViewById(R.id.music_bottom_play);
        last = findViewById(R.id.music_bottom_last);
        singer = findViewById(R.id.music_bottom_singer);
        song = findViewById(R.id.music_bottom_song);
        music_rv = findViewById(R.id.music_rv);
        jindutiao = findViewById(R.id.music_seekBar);
        txt = findViewById(R.id.item_music_durtion);
        next.setOnClickListener(this);
        play.setOnClickListener(this);
        last.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.music_bottom_last:
                if (currnetPlayPosition ==0) {
                    Toast.makeText(this,"已经是第一首了，没有上一曲！",Toast.LENGTH_SHORT).show();
                    return;
                }

                currentPausePositionInSong = 0;
                currnetPlayPosition = currnetPlayPosition-1;
                LocalMusicBean lastBean = mDatas.get(currnetPlayPosition);
                playMusicInMusicBean(lastBean);

                break;


            case R.id.music_bottom_next:
                if (currnetPlayPosition ==mDatas.size()-1) {
                    Toast.makeText(this,"已经是最后一首了，没有下一曲！",Toast.LENGTH_SHORT).show();
                    return;
                }
                currentPausePositionInSong = 0;
                currnetPlayPosition = currnetPlayPosition+1;
                LocalMusicBean nextBean = mDatas.get(currnetPlayPosition);
                playMusicInMusicBean(nextBean);
                break;


            case R.id.music_bottom_play:
                if (currnetPlayPosition == -1) {
//                    并没有选中要播放的音乐
                    Toast.makeText(this,"请选择想要播放的音乐",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mediaPlayer.isPlaying()) {
//                    此时处于播放状态，需要暂停音乐

                    pauseMusic();
                }else {
//                    此时没有播放音乐，点击开始播放音乐

                    playMusic();
                }
                break;

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.reset();
        isStop = true;
    }
    class SeekBarThread implements Runnable {

        @Override
        public void run() {
            while (mediaPlayer != null && isStop == false) {
                // 将SeekBar位置设置到当前播放位置
                handler.sendEmptyMessage(mediaPlayer.getCurrentPosition());
                try {
                    // 每100毫秒更新一次位置
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}
