package com.example.phai0512.helloapp;

/**
 * Created by phai0512 on 2017/07/19.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by phai0512 on 2017/07/19.
 */

public class MainFragment extends Fragment {

    private TextView textView;

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
        Button addButton = (Button)view.findViewById(R.id.button);
        // clearButton
        Button clearButton = (Button)view.findViewById(R.id.button_clear);
        // textView
        textView = (TextView)view.findViewById(R.id.text_view);
        // editText
        final EditText edittext = (EditText)view.findViewById(R.id.edit_text);

        // リスナをボタンに登録(add)
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText() + "\n" + edittext.getText().toString());
                edittext.getEditableText().clear();
            }
        });
        // リスナをボタンに登録(clear)
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                edittext.getEditableText().clear();
            }
        });
    }
}
