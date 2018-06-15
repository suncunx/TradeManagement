package com.architecture.template.refresh.fragment.di;

import com.architecture.di.PerFragment;
import com.architecture.template.refresh.fragment.TRefreshFragment;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerFragment
@Component(modules = {TRefreshFragmentModule.class})
public interface TRefreshFragmentComponent {
    void inject(TRefreshFragment fragment);
}
