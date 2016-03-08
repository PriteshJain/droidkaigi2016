package sh.pritesh.rubyconfindia.confsched.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import sh.pritesh.rubyconfindia.confsched.R;

public final class IntentUtil {

    private static final String HASH_TAG = "#RubyConfIndia";

    private IntentUtil() {
        throw new AssertionError();
    }

    public static void share(Context context, @NonNull String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url + " " + HASH_TAG);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)));
    }

}
