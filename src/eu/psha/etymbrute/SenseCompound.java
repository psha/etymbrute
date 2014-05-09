package eu.psha.etymbrute;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SenseCompound extends RelativeLayout {

	private TextView index;
	private TextView gloss;
	private TextView example;
	
	public SenseCompound(Context context) {
		super(context);
		inflateThis(context);
	}

	public SenseCompound(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflateThis(context);
	}

	public SenseCompound(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inflateThis(context);
	}
	
	private void inflateThis(Context context){
	    LayoutInflater inflater = LayoutInflater.from(context);
	    inflater.inflate(R.layout.sense_compound, this);

	    loadViews();
	}
	
	private void loadViews(){
		index = (TextView)findViewById(R.id.wv_entry_index );
		gloss = (TextView)findViewById(R.id.wv_entry_gloss);
		example = (TextView)findViewById(R.id.wv_entry_example );
	}
	
	public void setData(String index, String gloss, String example){
		this.index.setText(index);
		this.gloss.setText(gloss);
		
		
		if(example != null)
			this.example.setText(example);
		else
			this.example.setVisibility(TextView.GONE);
	
	}
	
}







