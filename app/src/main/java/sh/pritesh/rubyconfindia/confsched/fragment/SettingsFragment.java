package sh.pritesh.rubyconfindia.confsched.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import sh.pritesh.rubyconfindia.confsched.R;
import sh.pritesh.rubyconfindia.confsched.activity.ActivityNavigator;
import sh.pritesh.rubyconfindia.confsched.dao.SessionDao;
import sh.pritesh.rubyconfindia.confsched.databinding.FragmentSettingsBinding;
import sh.pritesh.rubyconfindia.confsched.prefs.DefaultPrefs;
import sh.pritesh.rubyconfindia.confsched.util.LocaleUtil;
import rx.Observable;

public class SettingsFragment extends BaseFragment {

    public static final String TAG = SettingsFragment.class.getSimpleName();

    @Inject
    ActivityNavigator activityNavigator;
    @Inject
    SessionDao dao;

    private FragmentSettingsBinding binding;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
    }

    private void initView() {

        boolean shouldNotify = DefaultPrefs.get(getContext()).getNotificationFlag(true);
        binding.notificationSwitchRow.init(shouldNotify, ((v, isChecked) -> {
            DefaultPrefs.get(getContext()).putNotificationFlag(isChecked);
            binding.headsUpSwitchRow.setEnabled(isChecked);
        }));
        binding.headsUpSwitchRow.setEnabled(shouldNotify);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            boolean headsUp = DefaultPrefs.get(getContext()).getHeadsUpFlag(true);
            binding.headsUpSwitchRow.init(headsUp, (v, isChecked) -> {
                DefaultPrefs.get(getContext()).putHeadsUpFlag(isChecked);
            });
            binding.headsUpSwitchRow.setVisibility(View.VISIBLE);
            binding.headsUpBorder.setVisibility(View.VISIBLE);
        }
    }

}
