package com.bemore.southerntireservice.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 *
 */
public class Keyboard
{
    public static void dismiss(Activity activity)
    {
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        dismissKeyboard(view);
    }

    public static void dismiss(View view)
    {
        dismissKeyboard(view);
    }

    private static void dismissKeyboard(View view)
    {
        if(view != null)
        {
            Context context = view.getContext();

            if (view instanceof EditText)
            {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            else if (view instanceof ViewGroup)
            {
                int children = ((ViewGroup) view).getChildCount();

                for (int i = 0; i < children; i++)
                {
                    View child = ((ViewGroup) view).getChildAt(i);

                    dismissKeyboard(child);
                }
            }
        }
    }

    public static void showKeyboard(EditText editText)
    {
        if (editText == null)
            return;

        Context context = editText.getContext();

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }
}