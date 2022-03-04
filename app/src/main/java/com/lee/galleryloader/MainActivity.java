package com.lee.galleryloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.lee.album.inter.OnGalleryListener;
import com.lee.album.router.GalleryEngine;
import com.lee.galleryloader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityMainBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false);
        setContentView(binding.getRoot());

        binding.insertGallery.setOnClickListener(v -> GalleryEngine.from(MainActivity.this)
                .setGalleryBuilder(MainActivity.this)
                .widthListPictureMargin(5)
                .widthListPictureColumnSpace(5)
                .widthListPictureRowSpace(5)
                .widthListPictureCorner(5)
                .withShouldLoadPaging(false)
                .widthPageSize(10)
                .widthListPicturePlaceholder(R.color.design_snackbar_background_color)
                .widthOnGalleryListener(new OnGalleryListener() {
                    @Override
                    public void clickGallery(String path, int position) {
                        Toast.makeText(MainActivity.this, "------->PATH=" + path + "------->position=" + position, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void bottomSheetState(boolean isOpen, boolean fromUser) {
                        Toast.makeText(MainActivity.this, "抽屉状态" + isOpen, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void clickBadPicture(String path, int position) {
                        Toast.makeText(MainActivity.this, "------->点击了 损坏图片 PATH=" + path + "------->position=" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .startGallery());


        binding.insertGallery2.setOnClickListener(v -> {

            startActivity(new Intent(this, BottomSheetActivity.class));
        });


    }


}