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
		
		inflateThis(context);
	}

	public WordCompound(Context context) {
		super(context);
		
		inflateThis(context);
	}
	
	private void inflateThis(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.word_compound, this);
 
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
		else
			this.pro.setVisibility(TextView.GONE);
		
		if(etym != null)
			this.etym.setText(etym);
		else
			this.etym.setVisibility(TextView.GONE);

	}
	
}
