package sh.pritesh.rubyconfindia.confsched.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apmem.tools.layouts.FlowLayout;

import javax.inject.Inject;

import sh.pritesh.rubyconfindia.confsched.R;
import sh.pritesh.rubyconfindia.confsched.databinding.FragmentSponsorsBinding;
import sh.pritesh.rubyconfindia.confsched.model.Sponsor;
import sh.pritesh.rubyconfindia.confsched.util.AnalyticsTracker;
import sh.pritesh.rubyconfindia.confsched.util.AppUtil;
import sh.pritesh.rubyconfindia.confsched.widget.SponsorImageView;
import rx.Observable;

public class SponsorsFragment extends BaseFragment {

    private FragmentSponsorsBinding binding;

    @Inject
    AnalyticsTracker analyticsTracker;

    public static SponsorsFragment newInstance() {
        return new SponsorsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSponsorsBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
    }

    private void initView() {
        Observable.from(Sponsor.createGoldList())
                .forEach(sponsor -> addView(sponsor, binding.goldContainer));
        Observable.from(Sponsor.createSilverList())
                .forEach(sponsor -> addView(sponsor, binding.silverContainer));
    }

    private void addView(Sponsor sponsor, FlowLayout container) {
        SponsorImageView imageView = new SponsorImageView(getActivity());
        imageView.bindData(sponsor, v -> {
            if (TextUtils.isEmpty(sponsor.url))
                return;
            analyticsTracker.sendEvent("sponsor", sponsor.url);
            AppUtil.showWebPage(getActivity(), sponsor.url);
        });
        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(
                FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        int margin = (int) getResources().getDimension(R.dimen.spacing_small);
        params.setMargins(margin, margin, 0, 0);
        container.addView(imageView, params);
    }

}
