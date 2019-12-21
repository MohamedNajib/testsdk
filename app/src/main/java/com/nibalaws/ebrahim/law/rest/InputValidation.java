package com.nibalaws.ebrahim.law.rest;

import android.widget.EditText;

public class InputValidation {

    public static boolean emptyInput (EditText input) {
        if (input.getText().toString().trim().isEmpty()){
            return false;
        }
        return true;
    }

    public static String isValidFromTo(String fromInput, String toInput) {

        if (fromInput.trim().isEmpty() && toInput.trim().isEmpty()){
            return null;

        }else if (!fromInput.trim().isEmpty() && !toInput.trim().isEmpty()) {
            return null;

        }else if (fromInput.trim().isEmpty()){
            return toInput;

        }else if (toInput.trim().isEmpty()){
            return fromInput;

        } else {
            return null;
        }
    }
}
