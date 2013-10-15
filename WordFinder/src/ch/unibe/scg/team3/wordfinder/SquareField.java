package ch.unibe.scg.team3.wordfinder;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

public class SquareField extends TextView {

	public SquareField(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int width = getMeasuredWidth();
		//final int pHeight = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(width, width);
	}
} 