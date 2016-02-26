package com.jarvislin.producepricechecker.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jarvislin.producepricechecker.R;

/**
 * Created by jarvis on 15/5/26.
 */
public class ToolsHelper {
    private static ProgressDialog progressDialog;
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static Toast showToast(Toast toast, Context context, String string, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, string, duration);
        } else {
            toast.setText(string);
            toast.setDuration(duration);
        }
        toast.show();
        return toast;
    }

    public static void shareText(Context context, String title, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, title));
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static ProgressDialog getProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(context.getString(R.string.loading));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static void showProgressDialog(final Context context, boolean isNowOnUiThread) {
        if (isNowOnUiThread) {
            showProgressDialog(context);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showProgressDialog(context);
                }
            });
        }
    }

    public static void closeProgressDialog(boolean isNowOnUiThread) {
        if (isNowOnUiThread) {
            closeProgressDialog();
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    closeProgressDialog();
                }
            });
        }
    }

    private static void showProgressDialog(Context context) {
        closeProgressDialog();
        getProgressDialog(context).show();
    }

    private static void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
            }
        }
    }

    public static void showDialog(Context context, String titleText, String messageText){
        final Dialog dialog = new Dialog(context, R.style.alertDialog);
        dialog.setContentView(R.layout.dialog_info);
        TextView title = (TextView) dialog.findViewById(R.id.title);
        TextView message = (TextView) dialog.findViewById(R.id.info_text);

        title.setText(titleText);
        message.setText(messageText);

        Button dismiss = (Button) dialog.findViewById(R.id.info_dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void rating(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static void openUrl(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        Intent chooser = Intent.createChooser(intent, context.getString(R.string.choose_service));
        context.startActivity(chooser);
    }

    public static void changeToGrayScale(ImageView v) {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);  //0 means grayscale
        ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
        v.setColorFilter(cf);
        v.setAlpha(0.5f);
    }

    public static void changeToFullColor(ImageView v) {
        v.setColorFilter(null);
        v.setAlpha(1.f);
    }




}
