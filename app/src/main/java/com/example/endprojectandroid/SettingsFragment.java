package com.example.endprojectandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Collections;
import java.util.Set;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
        updatePreferenceSummary(getPreferenceScreen());
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        Preference saveFiltersButton = findPreference(getString(R.string.save_filters_button));
        if(saveFiltersButton != null) {
            saveFiltersButton.setOnPreferenceClickListener(preference -> {
                if (getActivity() == null) return false;

                Intent mainActivity = new Intent(getContext(), MainActivity.class);
                startActivity(mainActivity);
                getActivity().finish();
                return true;
            });
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updatePreferenceSummary(getPreferenceScreen());
    }

    private String updatePreferenceSummary(Preference preference) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
        preference.setSummary("");

        if (preference instanceof EditTextPreference) {
            preference.setSummary(preferences.getString(preference.getKey(), ""));
        } else if (preference instanceof MultiSelectListPreference) {
            Set<String> values = preferences.getStringSet(preference.getKey(), Collections.emptySet());
            for (String value : values) {
                int index = ((MultiSelectListPreference) preference).findIndexOfValue(value);
                if (index >= 0) {
                    preference.setSummary(preference.getSummary() + (preference.getSummary().length() == 0 ? ""
                            : ", ") + ((MultiSelectListPreference) preference).getEntries()[index]);
                }
            }
        } else if (preference instanceof PreferenceCategory) {
            PreferenceCategory preferenceCategory = (PreferenceCategory) preference;
            for (int i = 0; i < preferenceCategory.getPreferenceCount(); i++) {
                updatePreferenceSummary(preferenceCategory.getPreference(i));
            }
        } else if (preference instanceof PreferenceGroup) {
            PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
            for (int i = 0; i < preferenceGroup.getPreferenceCount(); i++) {
                preference.setSummary(preference.getSummary() + (preference.getSummary().length() == 0 ? ""
                        : ", ") + updatePreferenceSummary(preferenceGroup.getPreference(i)));
            }
        }
        return preference.getSummary().toString();
    }
}