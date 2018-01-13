package com.neildg.androiddemoone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    private final static String TAG = "MainActivity";

    private final String displayFormat = "Hello %s! Welcome to your first mobile application!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setupBehavior();
    }

    private void setupBehavior() {


        //get all layout references
        final EditText nameText = (EditText) this.findViewById(R.id.name_text);
        final TextView msgView = (TextView) this.findViewById(R.id.display_msg_view);
        final Button submitBtn = (Button) this.findViewById(R.id.submit_btn);
        CheckBox refreshCheck = (CheckBox) this.findViewById(R.id.refresh_checkbox);

        //add button listener
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = (String) nameText.getText().toString();
                msgView.setText(String.format(displayFormat, name));
            }
        });

        refreshCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    //on the fly listener
                    nameText.addTextChangedListener(MainActivity.this);
                    submitBtn.setEnabled(false);
                }
                else {
                    nameText.removeTextChangedListener(MainActivity.this);
                    submitBtn.setEnabled(true);
                }
            }
        });



    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        final TextView msgView = (TextView) this.findViewById(R.id.display_msg_view);
        msgView.setText(String.format(displayFormat, s));
    }
}
