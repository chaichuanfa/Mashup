package com.felix.mashup.controller.main.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.felix.mashup.R;
import com.felix.mashup.base.BaseFragment;
import com.felix.mashup.controller.main.MainViewModel;
import com.felix.mashup.controller.main.di.MainComponent;
import com.felix.mashup.databinding.MainFragmentBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import android.view.Gravity;
import android.view.View;

import javax.inject.Inject;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Route(path = "/main/fragment")
public class MainFragment extends BaseFragment<MainViewModel, MainFragmentBinding> {

    @Inject
    EventBus mBus;

    @Override
    protected int getLayoutRes() {
        return R.layout.main_fragment;
    }

    @Override
    protected void bindViews(View view) {
        setupNavigationDrawer();
        mDataBinding.toolbar.setNavigationOnClickListener(
                v -> mDataBinding.drawerLayout.openDrawer(Gravity.START));
    }

    @Override
    protected void bindViewModel() {
        super.bindViewModel();
        mDataBinding.setViewmodel(mViewModel);
    }

    @Override
    protected void injectDependencies() {
        MainComponent component = this.getComponent(MainComponent.class);
        component.inject(this);
        if (mViewModel != null) {
            component.inject(mViewModel);
            if (!mBus.isRegistered(mViewModel)) {
                try {
                    mBus.register(mViewModel);
                } catch (EventBusException e) {
                    // ignore
                }
            }
        }
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }

    private void setupNavigationDrawer() {
        mDataBinding.drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        mDataBinding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.isChecked()) {
                mDataBinding.drawerLayout.closeDrawers();
                return true;
            }
            switch (menuItem.getItemId()) {
                case R.id.navigation_menu_news:
                    mViewModel.setMenuItemSelected(0);
                    break;
                case R.id.navigation_menu_wechat:
                    mViewModel.setMenuItemSelected(1);
                    break;
                default:
                    break;
            }
            menuItem.setChecked(true);
            mDataBinding.drawerLayout.closeDrawers();
            return true;
        });
    }

}
