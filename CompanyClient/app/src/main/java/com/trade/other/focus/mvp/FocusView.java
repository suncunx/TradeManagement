package com.trade.other.focus.mvp;


import com.architecture.mvp.BaseView;
import com.trade.other.focus.model.FocusBean;

import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface FocusView extends BaseView {
    void setData(List<FocusBean> data);
}
