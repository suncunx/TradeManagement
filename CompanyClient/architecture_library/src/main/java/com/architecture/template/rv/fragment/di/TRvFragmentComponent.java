package com.architecture.template.rv.fragment.di;

import com.architecture.di.PerFragment;
import com.architecture.template.rv.fragment.TRvFragment;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerFragment
@Component(modules = {TRvFragmentModule.class})
public interface TRvFragmentComponent {
    void inject(TRvFragment fragment);
}
