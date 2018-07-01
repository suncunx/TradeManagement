package com.trade.widget.popup;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.trade.BR;

/**
 * Created by Stephen Sun on 2017/8/4 0004.
 * Email:suncunx@qq.com
 */

public class PopupItem extends BaseObservable {
    private String text;
    private boolean checked;

    public PopupItem(String text, boolean checked) {
        this.text = text;
        this.checked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Bindable
    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        notifyPropertyChanged(BR.checked);
    }
}
