package sh.pritesh.rubyconfindia.confsched.di;

import sh.pritesh.rubyconfindia.confsched.di.scope.FragmentScope;
import sh.pritesh.rubyconfindia.confsched.fragment.AboutFragment;
import sh.pritesh.rubyconfindia.confsched.fragment.SessionDetailFragment;
import sh.pritesh.rubyconfindia.confsched.fragment.SessionsFragment;
import sh.pritesh.rubyconfindia.confsched.fragment.SessionsTabFragment;
import sh.pritesh.rubyconfindia.confsched.fragment.SettingsFragment;
import sh.pritesh.rubyconfindia.confsched.fragment.SponsorsFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(SettingsFragment fragment);

    void inject(AboutFragment fragment);

    void inject(SessionsTabFragment fragment);

    void inject(SessionsFragment fragment);

    void inject(SessionDetailFragment fragment);

    void inject(SponsorsFragment fragment);
}
