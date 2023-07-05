package com.example.calculatorplus.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.example.calculatorplus.R;

import java.util.function.Supplier;

public class DeleteAlertDialog {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static AlertDialog dialog(Context context, Supplier<Void> supplier) {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        // 自定义确认框
        View confirm = View.inflate(context, R.layout.fragment_confirm_del, null);
        EditText pwdText = confirm.findViewById(R.id.confirm_del_pwd);
        Button ok = confirm.findViewById(R.id.confirm_del_ok);
        Button cancel = confirm.findViewById(R.id.confirm_del_cancel);
        ok.setOnClickListener(v1 -> {
            if ("abc123".equals(pwdText.getText().toString())) {
                supplier.get();
                Toast.makeText(context, "已删除", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            } else {
                Toast.makeText(context, "密码错误", Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(v2 -> {
            dialog.dismiss();
        });
        dialog.setView(confirm);
        return dialog;
    }
}
