package com.owangwang.xtoastdemo;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wangchao on 2017/10/25.
 */

public class XToast {
    private Context mContext;
    private LayoutInflater mInflater;
    private String message;
    private View mView;
    private TextView mTextView;
    private ViewGroup mViewGroup;
    private ViewGroup mViewRoot;
    private GradientDrawable mToastBackgound;
    private AnimatorSet mShowAnimatorSet;
    private AnimatorSet mHideAnimatorSet;
    private int mShowAnimationType;
    private int mHideAnimationType;
    private int mDuration;
    private int mBackgroundColor;
    private OnDisappearListener mOnDisappearListener;

    public OnDisappearListener getmOnDisappearListener() {
        return mOnDisappearListener;
    }

    public XToast setmOnDisappearListener(OnDisappearListener mOnDisappearListener) {
        this.mOnDisappearListener = mOnDisappearListener;
        return  this;
    }

    public static final int XTOAST_LONG = 3500;
    public static final int XTOAST_SHORT = 2000;
    public interface OnDisappearListener{
        void onDisappear(XToast xToast);
    }
    public XToast(Context context){
        this.mContext=context;
        this.mInflater=LayoutInflater.from(context);
        }
    public XToast(Context context,String message){
        this.mContext=context;
        this.mInflater=LayoutInflater.from(context);
        this.message=message;
    }
    public static XToast create(Context context){
        return new XToast(context);
    }
    public static XToast create(Context context,String message){
        return new XToast(context,message);
    }
    public  boolean isShowing(){
        return mView!=null&&mView.isShown();
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public String getMessage() {
        return message;
    }

    public XToast setMessage(String message) {
        this.message = message;
        return this;
    }

    public View getmView() {
        return mView;
    }

    public void setmView(View mView) {
        this.mView = mView;
    }

    public ViewGroup getmViewGroup() {
        return mViewGroup;
    }

    public void setmViewGroup(ViewGroup mViewGroup) {
        this.mViewGroup = mViewGroup;
    }

    public ViewGroup getmViewRoot() {
        return mViewRoot;
    }

    public XToast setmViewRoot(ViewGroup mViewRoot) {
        this.mViewRoot = mViewRoot;
        return  this;
    }

    public GradientDrawable getmToastBackgound() {
        return mToastBackgound;
    }

    public XToast setmToastBackgound(GradientDrawable mToastBackgound) {
        this.mToastBackgound = mToastBackgound;
        return this;
    }

    public AnimatorSet getmShowAnimatorSet() {
        return mShowAnimatorSet;
    }

    public XToast setmShowAnimatorSet(AnimatorSet mShowAnimatorSet) {
        this.mShowAnimatorSet = mShowAnimatorSet;
        return this;
    }

    public AnimatorSet getmHideAnimatorSet() {
        return mHideAnimatorSet;
    }

    public XToast setmHideAnimatorSet(AnimatorSet mHideAnimatorSet) {
        this.mHideAnimatorSet = mHideAnimatorSet;
        return this;
    }

    public int getmShowAnimationType() {
        return mShowAnimationType;
    }

    public XToast setmShowAnimationType(int mShowAnimationType) {
        this.mShowAnimationType = mShowAnimationType;
        return this;
    }

    public int getmHideAnimationType() {
        return mHideAnimationType;
    }

    public XToast setmHideAnimationType(int mHideAnimationType) {
        this.mHideAnimationType = mHideAnimationType;
        return this;
    }

    public int getmDuration() {
        return mDuration;
    }

    public XToast setmDuration(int mDuration) {
        this.mDuration = mDuration;
        return  this;
    }

    public int getmBackgroundColor() {
        return mBackgroundColor;
    }

    public XToast setmBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
        return this;
    }

    private void initViews(){
        mViewRoot = (ViewGroup)((Activity)mContext).findViewById(android.R.id.content);
        mViewGroup = new LinearLayout(mContext);
        FrameLayout.LayoutParams mViewGroupParams=new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        mViewGroupParams.gravity = Gravity.BOTTOM | Gravity.CENTER;
        mViewGroupParams.bottomMargin = 200;
        mViewGroup.setLayoutParams(mViewGroupParams);
        mViewRoot.addView(mViewGroup);
        //如果用户没有使用自己的View，那么使用默认的mView
        if(mView==null){
            mView=mInflater.inflate(R.layout.xtoast_normal,null);
            mToastBackgound = (GradientDrawable) mView.getBackground();
            mTextView = (TextView) mView.findViewById(R.id.message);
            mTextView.setText(message);
            if(mBackgroundColor!=0){
                mToastBackgound.setColor(mBackgroundColor);
            }
            //对mView的大小进行测量
            int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1<<30) -1, View.MeasureSpec.AT_MOST);
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((1<<30) -1,View.MeasureSpec.AT_MOST);
            mView.measure(widthMeasureSpec,heightMeasureSpec);

        }


    }
    public void show(){
        initViews();
        if(this.mShowAnimationType==0){
            this.mShowAnimatorSet=AnimationUtils.getShowAnimation(this,AnimationUtils.ANIMATION_DEFAULT);
        }else
            this.mShowAnimatorSet = AnimationUtils.getShowAnimation(this,mShowAnimationType);
        if(this.mHideAnimationType == 0)
            this.mHideAnimatorSet = AnimationUtils.getHideAnimation(this, AnimationUtils.ANIMATION_DEFAULT);
        else
            this.mHideAnimatorSet = AnimationUtils.getHideAnimation(this,mHideAnimationType);

        if(mDuration == 0)
            mDuration = XTOAST_SHORT;

        XToastHandler xToastHandler = XToastHandler.getInstance();
        xToastHandler.add(this);


    }
}
