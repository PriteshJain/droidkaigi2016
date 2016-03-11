package sh.pritesh.rubyconfindia.confsched.di;

import dagger.Subcomponent;
import sh.pritesh.rubyconfindia.confsched.activity.ContributorsActivity;
import sh.pritesh.rubyconfindia.confsched.activity.MainActivity;
import sh.pritesh.rubyconfindia.confsched.activity.SearchActivity;
import sh.pritesh.rubyconfindia.confsched.activity.SearchedSessionsActivity;
import sh.pritesh.rubyconfindia.confsched.activity.SessionDetailActivity;
import sh.pritesh.rubyconfindia.confsched.activity.SessionFeedbackActivity;
import sh.pritesh.rubyconfindia.confsched.di.scope.ActivityScope;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(SessionDetailActivity activity);

    void inject(SearchActivity activity);

    void inject(SearchedSessionsActivity activity);

    void inject(SessionFeedbackActivity activity);

    void inject(ContributorsActivity activity);

    FragmentComponent plus(FragmentModule module);

}
