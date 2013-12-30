package com.cyanogenmod.settings.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.content.ContentResolver;
import android.util.Log;



public class TouchscreenGestureSettings extends PreferenceActivity {

    private static final String TAG = "GestureSettings";

    private CheckBoxPreference mKnockOffBox;

    private SharedPreferences mPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main);

        mPref = PreferenceManager.getDefaultSharedPreferences(this);

        mKnockOffBox = (CheckBoxPreference) findPreference(Settings.System.DOUBLE_TAP_SLEEP_GESTURE);

        mKnockOffBox.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                ContentResolver resolver = getContentResolver();
                Settings.System.putInt(resolver, Settings.System.DOUBLE_TAP_SLEEP_GESTURE,
                    ((Boolean)newValue) ? 1 : 0);
                return true;
            }
        });
    }
}
