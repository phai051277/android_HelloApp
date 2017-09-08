package com.example.phai0512.helloapp;

/**
 * Created by phai0512 on 2017/07/19.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by phai0512 on 2017/07/19.
 */

public class MainFragment extends Fragment {

    private TextView textView, calcView;
    // 計算キー
    private int recentOperator;
    // 結果
    private double result = 0;
    // 計算キー押下保存
    private boolean isOperatorKeyPushed;
    // 計算内容
    private String calcText;
    // 計算結果
    private String calcResult;

    // MainFragmentと紐づいたActivityの参照を格納
    private MyListener mListener;
    // コールバック用インターフェース定義
    public interface MyListener {
        // 履歴ボタンinterface
        public void onHisButton(String date, String calcView, String result);
        // 通知ボタンinterface
        public void onNotifyButton(String date, String calcView, String result);
    }
    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // 先ほどのレイアウトをここでViewとして作成します
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    // Viewが生成し終わった時に呼ばれるメソッド 
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        // addButton
        Button addButton = (Button) view.findViewById(R.id.ent_1);
        // clearButton
        Button clearButton = (Button) view.findViewById(R.id.ent_2);
        // textView
        textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(String.valueOf(result));
        // editText
        final EditText edittext = (EditText) view.findViewById(R.id.edit_text);
        // 計算キー初期化
        recentOperator = R.id.ent_equal;
        // 計算内容
        calcView = (TextView) view.findViewById(R.id.calc_text);
        // 数値アクション
        View.OnClickListener buttonNumberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;

                // EditTextの表示
                if(isOperatorKeyPushed) {
                    edittext.setText(button.getText());
                } else {
                    edittext.append(button.getText());
                }
                calcView.append(button.getText());
                isOperatorKeyPushed = false;
            }
        };
        // オペレーションアクション
        View.OnClickListener buttonActionListener = new View.OnClickListener() {
            // 計算キー
            @Override
            public void onClick(View view) {

                Button button = (Button) view;
                double value = Double.parseDouble(edittext.getText().toString());

                if(recentOperator == R.id.ent_equal) {
                    result = value;
                    edittext.setText(button.getText());
                    calcView.append(button.getText());
                } else {
                    result = calc(recentOperator, value, result);
                    textView.setText(String.valueOf(result));
                    edittext.setText("");
                    if(button.getId() != R.id.ent_equal) {
                        calcView.append(button.getText());
                    }
                }
                // オペレーションキー保存
                recentOperator = button.getId();
                isOperatorKeyPushed = true;
            }
        };
        // Clearアクション
        View.OnClickListener buttonClearListener = new View.OnClickListener() {
            // 計算キー
            @Override
            public void onClick(View view) {
                // 初期化
                result = 0;
                recentOperator = R.id.ent_equal;
                edittext.setText("");
                textView.setText(String.valueOf(result));
                isOperatorKeyPushed = false;
                calcView.setText("");
            }
        };
        // 履歴アクション
        View.OnClickListener historyActionListener = new View.OnClickListener() {
            // 計算キー
            @Override
            public void onClick(View view) {
                // 履歴通知
                if (mListener != null) {
                    calcText = calcView.getText().toString();
                    calcResult = textView.getText().toString();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    mListener.onHisButton(df.format(new Date()), calcText, calcResult);
                }
            }
        };
        // 通知アクション
        View.OnClickListener notifyActionListener = new View.OnClickListener() {
            // 計算キー
            @Override
            public void onClick(View view) {
                // 履歴通知
                if (mListener != null) {
                    calcText = calcView.getText().toString();
                    calcResult = textView.getText().toString();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    mListener.onNotifyButton(df.format(new Date()), calcText, calcResult);
                }
            }
        };


        // NumberButton
        view.findViewById(R.id.ent_1).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_2).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_3).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_4).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_5).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_6).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_7).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_8).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_9).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_0).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_00).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_dot).setOnClickListener(buttonNumberListener);
        view.findViewById(R.id.ent_divide).setOnClickListener(buttonActionListener);
        view.findViewById(R.id.ent_minus).setOnClickListener(buttonActionListener);
        view.findViewById(R.id.ent_multiply).setOnClickListener(buttonActionListener);
        view.findViewById(R.id.ent_plus).setOnClickListener(buttonActionListener);
        view.findViewById(R.id.ent_ac).setOnClickListener(buttonClearListener);
        view.findViewById(R.id.ent_equal).setOnClickListener(buttonActionListener);
        view.findViewById(R.id.ent_his).setOnClickListener(historyActionListener);
        view.findViewById(R.id.ent_notify).setOnClickListener(notifyActionListener);
    }

    // FragmentがActivityに追加されたら呼ばれるメソッド
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // contextクラスがMyListenerを実装しているかをチェックする
        if (context instanceof MyListener) {
            // リスナーをここでセットするようにします
            mListener = (MyListener) context;
        }
    }

    // FragmentがActivityから離れたら呼ばれるメソッド
    @Override
    public void onDetach() {
        super.onDetach();
        // 画面からFragmentが離れたあとに処理が呼ばれることを避けるためにNullで初期化しておく
        mListener = null;
    }

    double calc(int recentOperator, double value, double result) {
        // オペレーションによる処理の振り分け
        switch(recentOperator){
            case R.id.ent_plus:
                return result + value;
            case R.id.ent_minus:
                return result - value;
            case R.id.ent_multiply:
                return result * value;
            case R.id.ent_divide:
                return result / value;
            default:
                return value;
        }
    }

}