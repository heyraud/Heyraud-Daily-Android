package io.github.thismj.android.samples.document_provider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import io.github.thismj.android.BasicActivity;
import io.github.heyraud.daily.android.R;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/8/21 12:27
 */
public class DocumentProviderActivity extends BasicActivity {

    @Override
    public int getContent() {
        return R.layout.content_document_provider;
    }

    @Override
    public void initComponent(View view) {

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

        ImageView imageView = findViewById(R.id.image);
        Paint p = new Paint();
        p.setColor(Color.RED);//红色的光晕
        BitmapDrawable bd = (BitmapDrawable) imageView.getDrawable();
        Bitmap b = bd.getBitmap();
        Bitmap bitmap = Bitmap.createBitmap(bd.getIntrinsicWidth(), bd.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(b.extractAlpha(), 0, 0, p);

        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, new BitmapDrawable(bitmap));

        imageView.setBackgroundDrawable(sld);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
