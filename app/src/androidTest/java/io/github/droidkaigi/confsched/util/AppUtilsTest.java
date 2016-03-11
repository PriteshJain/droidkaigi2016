package sh.pritesh.rubyconfindia.confsched.util;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import sh.pritesh.rubyconfindia.confsched.util.AppUtil;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class AppUtilsTest {

    @Test
    public void testTwitterUrl() throws Exception {
        assertThat(AppUtil.getTwitterUrl("konifar")).isEqualTo("https://twitter.com/konifar");
    }

    @Test
    public void testGitHubUrl() throws Exception {
        assertThat(AppUtil.getGitHubUrl("konifar")).isEqualTo("https://github.com/konifar");
    }

}
