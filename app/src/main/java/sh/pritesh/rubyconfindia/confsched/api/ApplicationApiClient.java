package sh.pritesh.rubyconfindia.confsched.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import sh.pritesh.rubyconfindia.confsched.model.Contributor;
import sh.pritesh.rubyconfindia.confsched.model.Session;
import sh.pritesh.rubyconfindia.confsched.model.SessionFeedback;
import sh.pritesh.rubyconfindia.confsched.util.LocaleUtil;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

@Singleton
public class ApplicationApiClient {

    private static final String SESSIONS_API_ROUTES = "/priteshjain/rubyconfindia2016/rubysessions/app/src/main/res/raw/";

    private final ApplicationApiService service;
    private final GoogleFormService googleFormService;
    private final GithubService githubService;

    @Inject
    public ApplicationApiClient(OkHttpClient client) {
        Retrofit feedburnerRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://raw.githubusercontent.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build();
        service = feedburnerRetrofit.create(ApplicationApiService.class);

        Retrofit googleFormRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://docs.google.com/forms/d/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build();
        googleFormService = googleFormRetrofit.create(GoogleFormService.class);

        Retrofit githubRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build();
        githubService = githubRetrofit.create(GithubService.class);
    }

    public static Gson createGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public Observable<List<Session>> getSessions(@NonNull String languageId) {
        switch (languageId) {
            case LocaleUtil.LANG_EN_ID:
                return service.getSessionsEn();
            default:
                return service.getSessionsEn();
        }
    }

    public Observable<Response<Void>> submitSessionFeedback(SessionFeedback f) {
        return googleFormService.submitSessionFeedback(f.sessionId, f.sessionName, f.relevancy, f.asExpected, f.difficulty, f.knowledgeable, f.comment);
    }

    public Observable<List<Contributor>> getContributors() {
        return githubService.getContributors("priteshjain", "rubyconfindia2016");
    }

    public interface ApplicationApiService {
        @GET(SESSIONS_API_ROUTES + "sessions_en.json")
        Observable<List<Session>> getSessionsEn();
    }

    public interface GoogleFormService {
        @POST("1PrHh5PXH1NkBbDe7eFfOuu311X4LlyKF95TBYFFy6nw/formResponse")
        @FormUrlEncoded
        Observable<Response<Void>> submitSessionFeedback(
                @Field("entry.36792886") int id,
                @Field("entry.288043897") String name,
                @Field("entry.1914381797") int relevancy,
                @Field("entry.907172560") int asExpected,
                @Field("entry.1839418272") int difficulty,
                @Field("entry.675295234") int knowledgeable,
                @Field("entry.1455307059") String comment
        );
    }

    public interface GithubService {
        @GET("/repos/{owner}/{repo}/contributors?per_page=100")
        Observable<List<Contributor>> getContributors(@Path("owner") String owner, @Path("repo") String repo);
    }
}
