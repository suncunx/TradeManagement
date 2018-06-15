package com.architecture.template.base.fragment.di;

import com.architecture.di.PerFragment;
import com.architecture.template.base.fragment.TFragment;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerFragment
@Component(modules = {TFragmentModule.class})
public interface TFragmentComponent {
    void inject(TFragment fragment);
}
