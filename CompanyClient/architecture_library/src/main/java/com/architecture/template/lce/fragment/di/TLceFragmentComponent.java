package com.architecture.template.lce.fragment.di;

import com.architecture.di.PerFragment;
import com.architecture.template.lce.fragment.TLceFragment;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerFragment
@Component(modules = {TLceFragmentModule.class})
public interface TLceFragmentComponent {
    void inject(TLceFragment lceFragment);
}
