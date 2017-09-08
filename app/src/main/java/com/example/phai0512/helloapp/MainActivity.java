package com.example.phai0512.helloapp;

import android.app.Notification;
 import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.media.RingtoneManager;
import android.net.Uri;
import android.app.NotificationChannel;

public class MainActivity extends AppCompatActivity implements MainFragment.MyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fragment追加
        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }

    // interface内のメソッドを実装します。
    @Override
    public void onHisButton(String date, String calcView, String result) {
//        Toast.makeText(this, String.format("MainFragmentから%sがクリックされました!", calcView), Toast.LENGTH_SHORT).show();
        // Intent による画面遷移
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("date", date);
        intent.putExtra("history", calcView);
        intent.putExtra("result", result);
        startActivity(intent);
    }

    // interface内のメソッドを実装します。
    @Override
    public void onNotifyButton(String date, String calcView, String result) {
//        Toast.makeText(this, String.format("MainFragmentから%sがクリックされました!", calcView), Toast.LENGTH_SHORT).show();

        // Intent による通知
        Intent intent = new Intent(this, ListActivity.class);
        // PendingIntent オブジェクトの生成。このオブジェクトを他のアプリに渡すことで、引数に渡した Intent の送信を委ねることができる
        PendingIntent activityIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                // タイトル
                .setContentTitle("計算通知")
                // 詳細
                .setContentText(String.format("日時：%s、内容：%s、結果：%s", date, calcView, result))
                // アイコン
                .setSmallIcon(R.mipmap.ic_launcher)
                // タップした時の挙動
                .setTicker("通知タップ")
                // タップしたら消えるようにする
                .setAutoCancel(true)
                // 通知をタップした時に使う PendingIntent
                .setContentIntent(activityIntent);

        // 直接インスタンス化せず、Context を経由してインスタンスを取得する
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }
}
