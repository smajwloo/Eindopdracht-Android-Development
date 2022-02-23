package com.example.endprojectandroid.Helper;

import android.widget.TextView;

public interface CheckVariableAvailability {

    void setVariableTextInteger(TextView textView, String placeholder, int variableValue);

    void setVariableTextString(TextView textView, String placeholder, String variableValue);
}
