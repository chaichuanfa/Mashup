package com.felix.mashup.controller.main.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
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
    protected void bindViewModel() {
        super.bindViewModel();
        mDataBinding.setViewmodel(mViewModel);
        mDataBinding.setFragment(this);
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

    @Override
    protected void bindViews(View view) {
        setupNavigationDrawer();
        loadRootFragment(R.id.contentFrame, (NewsControllerFragment) ARouter.getInstance()
                .build("/news_controller/fragment")
                .navigation());
    }

    public void navigationOnClick(View view) {
        mDataBinding.drawerLayout.openDrawer(Gravity.START);
    }

    private void setupNavigationDrawer() {
        mDataBinding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.isChecked()) {
                mDataBinding.drawerLayout.closeDrawers();
                return true;
            }
            switch (menuItem.getItemId()) {
                case R.id.navigation_menu_news:
                    mViewModel.setMenuItemSelected(MainMenu.NEWS);
                    showHideFragment(findChildFragment(NewsControllerFragment.class));
                    break;
                case R.id.navigation_menu_wechat:
                    mViewModel.setMenuItemSelected(MainMenu.WECHAT_SIFT);
                    WeChatSiftFragment weChatSiftFragment = findChildFragment(
                            WeChatSiftFragment.class);
                    if (weChatSiftFragment == null) {
                        startChild((WeChatSiftFragment) ARouter.getInstance()
                                .build("/wechat_sift/fragment")
                                .navigation());
                    } else {
                        showHideFragment(weChatSiftFragment);
                    }
                    break;
                case R.id.navigation_menu_joke:
                    JokeFragment jokeFragment = findChildFragment(JokeFragment.class);
                    if (jokeFragment == null) {
                        startChild((JokeFragment) ARouter.getInstance()
                                .build("/joke/fragment")
                                .navigation());
                    } else {
                        showHideFragment(jokeFragment);
                    }
                    break;
                default:
                    break;
            }
            menuItem.setChecked(true);
            mDataBinding.drawerLayout.closeDrawers();
            return true;
        });
    }

    public enum MainMenu {
        NEWS,
        WECHAT_SIFT,
        JOKE
    }

}
