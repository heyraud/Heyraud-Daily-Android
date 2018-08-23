package io.github.heyraud.daily.android.document;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.github.heyraud.daily.android.BasicActivity;
import io.github.heyraud.daily.android.R;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/8/21 12:27
 */
public class DocumentProviderActivity extends BasicActivity {

    @Override
    protected int getContent() {
        return R.layout.content_document_provider;
    }

    @Override
    protected void initComponent() {

        Button button = findViewById(R.id.btn_get_content);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_GET_CONTENT);
                it.setType("*/*");
                it.addCategory(Intent.CATEGORY_OPENABLE);
                try {
                    startActivityForResult(Intent.createChooser(it, "select files"), 100);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(DocumentProviderActivity.this, "there is not valid activity", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
