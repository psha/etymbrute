package eu.psha.etymbrute;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WordCompound extends LinearLayout {
	
	private TextView word;
	private TextView pos;
	private TextView pro;
	private TextView etym;
	
	public WordCompound(Context context, AttributeSet attrs) {
		super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.word_compound, this);
 
        loadViews();
	}

	public WordCompound(Context context) {
		super(context);
		
		loadViews();
	}

	private void loadViews(){
		word = (TextView)findViewById(R.id.wv_wc_word);
		pos = (TextView)findViewById(R.id.wv_wc_pos );
		pro = (TextView)findViewById(R.id.wv_wc_pro);
		etym = (TextView)findViewById(R.id.wv_wc_etym);
	}
	
	public void setData(String word, String pos, String pro, String etym){
		this.word.setText(word);
		if(pos != null)
			this.pos.setText(pos);
		if(pro != null)
			this.pro.setText(pro);
		if(pro != null)
			this.etym.setText(etym);
	}
	
}
