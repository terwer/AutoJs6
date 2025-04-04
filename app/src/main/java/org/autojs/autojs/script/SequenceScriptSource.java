package org.autojs.autojs.script;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.autojs.autojs.io.ConcatReader;

import java.io.Reader;
import java.io.StringReader;

/**
 * Created by Stardust on Apr 2, 2017.
 */
public class SequenceScriptSource extends JavaScriptSource {

    private String mScript;
    private final JavaScriptSource mSecondScriptSource;
    private final JavaScriptSource mFirstScriptSource;

    public SequenceScriptSource(String name, JavaScriptSource firstScriptSource, JavaScriptSource secondScriptSource) {
        super(name);
        mSecondScriptSource = secondScriptSource;
        mFirstScriptSource = firstScriptSource;
    }

    @NonNull
    @Override
    public String getScript() {
        concatScriptsIfNeeded();
        return mScript;
    }

    private void concatScriptsIfNeeded() {
        if (mScript != null)
            return;
        mScript = mFirstScriptSource.getScript() + mSecondScriptSource.getScript();
    }

    @Nullable
    @Override
    public Reader getScriptReader() {
        if (mScript != null)
            return new StringReader(mScript);
        return new ConcatReader(mFirstScriptSource.getNonNullScriptReader(), mSecondScriptSource.getNonNullScriptReader());
    }

    @NonNull
    @Override
    public String toString() {
        return mSecondScriptSource.toString();
    }

}
