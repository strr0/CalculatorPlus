package com.example.calculatorplus.ui.number;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.OcrRequestParams;
import com.baidu.ocr.sdk.model.OcrResponseResult;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.MemberRecord;
import com.example.calculatorplus.entity.NumberRecord;
import com.example.calculatorplus.ui.camera.CameraActivity;
import com.example.calculatorplus.ui.member.MemberViewModel;
import com.example.calculatorplus.ui.util.FileUtil;
import com.example.calculatorplus.view.DeleteAlertDialog;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberEditFragment extends Fragment {
    private static final int REQUEST_CODE_PICK_IMAGE = 201;
    private static final int REQUEST_CODE_CAMERA = 102;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_number_edit, container, false);
        initGridView(root);
        initCameraButton(root);
        initGalleryButton(root);
        initParseButton(root);
        initClearButton(root);
        initSpinner(root);
        initSaveButton(root);
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initGridView(View view) {
        NumberViewModel numberViewModel = new ViewModelProvider(this).get(NumberViewModel.class);
        GridView gridView = view.findViewById(R.id.number_edit_grid);
        NumberChildAdapter adapter = new NumberChildAdapter(getActivity());
        gridView.setAdapter(adapter);
        // 初始化数据
        initData(adapter);
        gridView.setOnItemLongClickListener((a, v, i, l) -> {
            AlertDialog dialog = DeleteAlertDialog.dialog(getActivity(), () -> {
                NumberRecord record = (NumberRecord) adapter.getItem(i);
                if (record.getId() != null) {
                    numberViewModel.remove(record);
                }
                adapter.removeRecord(i);
                adapter.notifyDataSetChanged();
                return null;
            });
            dialog.show();
            return true;
        });
        // 数字点击事件
        gridView.setOnItemClickListener((a, v, i, l) -> {
            NumberRecord record = (NumberRecord) adapter.getItem(i);
            // 修改弹框
            AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
            View edit = View.inflate(getActivity(), R.layout.fragment_number_edit_item, null);
            EditText numberText = edit.findViewById(R.id.number_edit_number);
            EditText moneyText = edit.findViewById(R.id.number_edit_money);
            numberText.setText(String.valueOf(record.getNumber()));
            moneyText.setText(String.valueOf(record.getMoney()));
            Button ok = edit.findViewById(R.id.number_edit_ok);
            Button cancel = edit.findViewById(R.id.number_edit_cancel);
            ok.setOnClickListener(v1 -> {
                String numberStr = numberText.getText().toString();
                String moneyStr = moneyText.getText().toString();
                if ("".equals(numberStr) || "".equals(moneyStr)) {
                    Toast.makeText(getActivity(), "数字和金额不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                record.setNumber(Integer.parseInt(numberStr));
                record.setMoney(Double.parseDouble(moneyStr));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            });
            cancel.setOnClickListener(v2 -> dialog.dismiss());
            dialog.setView(edit);
            dialog.show();
        });
    }

    private void initCameraButton(View view) {
        Button button = view.findViewById(R.id.number_carema);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CameraActivity.class);
            intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                    FileUtil.getSaveFile(getActivity()).getAbsolutePath());
            startActivityForResult(intent, REQUEST_CODE_CAMERA);
        });
    }

    private void initGalleryButton(View view) {
        Button button = view.findViewById(R.id.number_gallery);
        button.setOnClickListener(v -> {
            if (checkGalleryPermission()) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
            }
        });
    }

    private void initParseButton(View view) {
        Pattern pattern = Pattern.compile("\\d+");
        GridView gridView = view.findViewById(R.id.number_edit_grid);
        NumberChildAdapter adapter = (NumberChildAdapter) gridView.getAdapter();
        EditText content = view.findViewById(R.id.number_edit_content);
        Button button = view.findViewById(R.id.number_parse);
        button.setOnClickListener(v -> {
            Matcher matcher = pattern.matcher(content.getText().toString());
            List<NumberRecord> records = new ArrayList<>();
            NumberRecord record = null;
            while (matcher.find()) {
                if (record == null) {
                    record = new NumberRecord();
                    record.setNumber(Integer.parseInt(matcher.group()));
                } else {
                    record.setMoney(Double.parseDouble(matcher.group()));
                    records.add(record);
                    record = null;
                }
            }
            adapter.addRecords(records);
            adapter.notifyDataSetChanged();
        });
    }

    private void initClearButton(View view) {
        EditText content = view.findViewById(R.id.number_edit_content);
        Button button = view.findViewById(R.id.number_clear);
        button.setOnClickListener(v -> {
            content.setText("");
        });
    }

    // 保存按鈕
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initSaveButton(View view) {
        NumberViewModel numberViewModel = new ViewModelProvider(this).get(NumberViewModel.class);
        GridView gridView = view.findViewById(R.id.number_edit_grid);
        NumberChildAdapter adapter = (NumberChildAdapter) gridView.getAdapter();
        Spinner spinner = view.findViewById(R.id.number_edit_spinner);
        Button button = view.findViewById(R.id.number_save);
        button.setOnClickListener(v -> {
            new AlertDialog.Builder(getActivity())
                    .setTitle("保存")
                    .setMessage("是否保存?")
                    .setPositiveButton("確定", (d, i) -> {
                        String time = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
                        MemberRecord item = (MemberRecord) spinner.getSelectedItem();
                        List<NumberRecord> records = adapter.getRecords();
                        records.forEach(record -> {
                            record.setMid(item.getId());
                            if (record.getTime() == null) {
                                record.setTime(time);
                            }
                        });
                        numberViewModel.save(records);
                        Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(v).popBackStack();
                    })
                    .setNegativeButton("取消", (d, i) -> {
                        d.dismiss();
                    }).show();

        });
    }

    // 下拉框
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initSpinner(View view) {
        Bundle bundle = getArguments();
        Integer mid = bundle != null ? bundle.getInt("mid") : null;
        MemberViewModel memberViewModel = new ViewModelProvider(this).get(MemberViewModel.class);
        Spinner spinner = view.findViewById(R.id.number_edit_spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(getActivity());
        spinner.setAdapter(adapter);
        memberViewModel.getLiveData().observe(getViewLifecycleOwner(), records -> {
            adapter.setRecords(records);
            if (mid != null) {
                int size = records.size();
                for (int i = 0; i < size; i++) {
                    if (mid.equals(records.get(i).getId())) {
                        spinner.setSelection(i);
                        break;
                    }
                }
            }
            adapter.notifyDataSetChanged();  // 通知spinner刷新
        });
    }

    private void initData(NumberChildAdapter adapter) {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        int mid = bundle.getInt("mid");
        String time = bundle.getString("time");
        MemberViewModel memberViewModel = new ViewModelProvider(this).get(MemberViewModel.class);
        memberViewModel.getNumberLiveData(mid, time).observe(getViewLifecycleOwner(), records -> {
            adapter.setRecords(records);
            adapter.notifyDataSetChanged();
        });
    }

    private boolean checkGalleryPermission() {
        int ret = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission
                .READ_EXTERNAL_STORAGE);
        if (ret != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    1000);
            return false;
        }
        return true;
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void recWrittenText(String filePath) {
        EditText content = getView().findViewById(R.id.number_edit_content);
        OcrRequestParams param = new OcrRequestParams();
        param.setImageFile(new File(filePath));
        OCR.getInstance(getActivity()).recoginzeWrittenText(param, new OnResultListener<OcrResponseResult>() {
            @Override
            public void onResult(OcrResponseResult result) {
                Toast.makeText(getActivity(), "识别成功", Toast.LENGTH_LONG).show();
                content.setText(result.getJsonRes());
            }

            @Override
            public void onError(OCRError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recWrittenText(filePath);
        }

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getActivity()).getAbsolutePath();
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_GENERAL.equals(contentType)) {
                        recWrittenText(filePath);
                    }
                }
            }
        }
    }
}
