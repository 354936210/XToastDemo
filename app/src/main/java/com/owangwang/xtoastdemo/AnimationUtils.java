package com.owangwang.xtoastdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

/**
 * Created by wangchao on 2017/10/25.
 */

public class AnimationUtils {

    public static final int ANIMATION_DEFAULT = 0X000;
    public static final int ANIMATION_DRAWER = 0x001;
    public static final int ANIMATION_SCALE = 0x002;
    public static final int ANIMATION_PULL = 0x003;

    public static AnimatorSet getShowAnimation(XToast xToast, int animationType){
        switch (animationType){
            case ANIMATION_DRAWER:
                AnimatorSet drawerSet = new AnimatorSet();
                drawerSet.playTogether(
                        ObjectAnimator.ofFloat(xToast.getmView(), "translationY", -xToast.getmView().getMeasuredHeight(), 0),
                        ObjectAnimator.ofFloat(xToast.getmView(), "alpha", 0, 1)
                );
                drawerSet.setDuration(500);
                return drawerSet;

            case ANIMATION_SCALE:
                AnimatorSet scaleSet = new AnimatorSet();
                scaleSet.playTogether(
                        ObjectAnimator.ofFloat(xToast.getmView(), "scaleX", 0, 1),
                        ObjectAnimator.ofFloat(xToast.getmView(), "scaleY", 0, 1)
                );
                scaleSet.setDuration(500);
                return scaleSet;

            default:
                AnimatorSet defaultSet = new AnimatorSet();
                defaultSet.play(ObjectAnimator.ofFloat(xToast.getmView(), "alpha", 0, 1));
                defaultSet.setDuration(500);
                return defaultSet;
        }
    }

    //省略Hide动画...
    //...
    public static AnimatorSet getHideAnimation(XToast xToast,int animationType){
        switch (animationType){
            case ANIMATION_DRAWER:
                AnimatorSet drawerSet = new AnimatorSet();
                drawerSet.playTogether(
                        ObjectAnimator.ofFloat(xToast.getmView(), "translationY", 0,-xToast.getmView().getMeasuredHeight()),
                        ObjectAnimator.ofFloat(xToast.getmView(), "alpha", 1, 0)
                );
                drawerSet.setDuration(500);
                return drawerSet;

            case ANIMATION_SCALE:
                AnimatorSet scaleSet = new AnimatorSet();
                scaleSet.playTogether(
                        ObjectAnimator.ofFloat(xToast.getmView(),"scaleX",1,0),
                        ObjectAnimator.ofFloat(xToast.getmView(),"scaleY",1,0)
                );
                scaleSet.setDuration(500);
                return scaleSet;

            case ANIMATION_PULL:
                AnimatorSet pullSet = new AnimatorSet();
                pullSet.playTogether(
                        ObjectAnimator.ofFloat(xToast.getmView(),"translationY",0,xToast.getmView().getMeasuredHeight()),
                        ObjectAnimator.ofFloat(xToast.getmView(),"alpha",1,0)
                );
                pullSet.setDuration(500);
                return pullSet;

            default:
                AnimatorSet defaultSet = new AnimatorSet();
                defaultSet.play(ObjectAnimator.ofFloat(xToast.getmView(), "alpha", 1, 0));
                defaultSet.setDuration(500);
                return defaultSet;
        }
    }
}

