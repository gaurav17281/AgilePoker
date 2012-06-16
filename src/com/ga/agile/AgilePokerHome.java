package com.ga.agile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AgilePokerHome extends Activity implements OnClickListener {
    private EditText mEditText = null;
    private Button mButton = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poker_card_list);
        initViews();
    }
    
    
    private void initViews() {
    	mEditText = (EditText) findViewById(R.id.edittext);
    	if (null != mButton) {
    		mButton.requestFocus();
    	}

    	mButton = (Button) findViewById(R.id.button);
    	if (null != mButton) {
    		mButton.setOnClickListener(this);
    	}
    }


	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button) {
			openCard();
		}
	}
	
	private void openCard() {
		String cardText = mEditText.getText().toString();
		if(cardText.length() > 0) {
			Intent intent = new Intent();
			intent.setClass(this, AgilePokerCard.class);
			intent.putExtra(Constants.KEY_CARD_TEXT,
					cardText);
			startActivity(intent);
		}
	}
}