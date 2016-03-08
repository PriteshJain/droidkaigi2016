package sh.pritesh.rubyconfindia.confsched.fragment;

import java.util.List;

import sh.pritesh.rubyconfindia.confsched.model.Session;
import rx.Observable;
import rx.Subscription;

public class MyScheduleFragment extends SessionsFragment {

    public static MyScheduleFragment newInstance() {
        return new MyScheduleFragment();
    }

    @Override
    protected Subscription loadData() {
        showLoadingView();
        Observable<List<Session>> cachedSessions = dao.findByChecked();
        return cachedSessions.subscribe(sessions -> {
            hideLoadingView();
            if (sessions.isEmpty()) {
                showEmptyView();
            } else {
                groupByDateSessions(sessions);
            }
        });
    }

    @Override
    protected SessionsTabFragment createTabFragment(List<Session> sessions) {
        return MyScheduleSessionsTabFragment.newInstance(sessions);
    }
}
