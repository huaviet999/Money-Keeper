package utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Viet Hua on 3/11/2020
 */
public class AndroidDialogUtils {
    private static AndroidDialogUtils INSTANCE;

    private AndroidDialogUtils() {

    }

    public static AndroidDialogUtils getInstance() {
        AndroidDialogUtils localInstance;
        if (INSTANCE == null) {
            synchronized (AndroidDialogUtils.class) {
                if (INSTANCE == null) {
                    localInstance = new AndroidDialogUtils();
                    INSTANCE = localInstance;
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Show / Hide progress dialog
     */
    private ProgressDialog progressDialog;

    public void hideProgressDilog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog = null;
    }

    public void showProgressDialog(Context context, String message) {
        hideProgressDilog();
        progressDialog = ProgressDialog.show(context, null, message, false, false, null);
    }
}
