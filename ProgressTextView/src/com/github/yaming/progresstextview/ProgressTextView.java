package com.github.yaming.progresstextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class ProgressTextView extends TextView {
	private boolean mShouldStartAnimationDrawable;
	
	@SuppressWarnings("unused")
	private boolean mAttached;
	
	private LinearInterpolator mInterpolator;
	private Animation mAnimation;
	private int animResid;

	public ProgressTextView(Context context) {
		super(context);
		init(context, null, 0);
	}

	public ProgressTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public ProgressTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
		
	}

	private void init(Context context, AttributeSet attrs, int defStyle) {
		if (attrs == null)
			return;
		
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.ProgressTextView, R.attr.font,
				R.style.ProgressTextView);

		try {
			animResid = a.getResourceId(R.styleable.ProgressTextView_anim, R.anim.loading);
			String font = a.getString(R.styleable.ProgressTextView_font);
			if (font != null) {
				Typeface typeface = TypefaceManager.getTypeface(context, font);
				if (typeface != null) {
					this.setTypeface(typeface);
				}
			}
		} finally {
			a.recycle();
		}
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);

	}
	
	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		mAttached = true;
		startAnimation();
	}
	
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		mAttached = false;
		stopAnimation();
	}

	@Override
	public void setVisibility(int v) {
		if (getVisibility() != v) {
			super.setVisibility(v);
			 if (v == GONE || v == INVISIBLE) {
                 stopAnimation();
             } else {
                 startAnimation();
             }
		}
	}
	
	private void startAnimation(){
		if (getVisibility() != VISIBLE) {
            return;
        }
		
		if (mInterpolator == null) {
            mInterpolator = new LinearInterpolator();
        }
		if(mAnimation == null){
			mAnimation = AnimationUtils.loadAnimation(getContext(), animResid);  
			mAnimation.setInterpolator(mInterpolator);
			setAnimation(mAnimation);
		}else{
			mAnimation.reset();
		}
		mShouldStartAnimationDrawable = true;
	}
	
	private void stopAnimation(){
		mShouldStartAnimationDrawable = false;
		if(mAnimation != null){
			mAnimation.cancel();
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(mShouldStartAnimationDrawable){
			mAnimation.start();
			mShouldStartAnimationDrawable = false;
		}
	}
}
