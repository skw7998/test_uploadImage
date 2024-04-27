//UploadImgActivity.java
package com.sk7998.uploadimage;

//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//import java.io.ByteArrayOutputStream;
//
//public class UploadImgActivity extends AppCompatActivity {
//    Button chooseImg, uploadImg;
//    ImageView imgView;
//    int PICK_IMAGE_REQUEST = 111;
//    Uri filePath;
//    ProgressDialog pd;
//
//    // 连接到 Firebase
//    FirebaseStorage storage = FirebaseStorage.getInstance();
//    StorageReference storageRef = storage.getReferenceFromUrl("gs://test-e7e01.appspot.com");
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upload_img);
//
//        chooseImg = findViewById(R.id.chooseImg);
//        uploadImg = findViewById(R.id.uploadImg);
//        imgView = findViewById(R.id.imgView);
//
//        pd = new ProgressDialog(this);
//        pd.setMessage("正在上传....");
//
//        chooseImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 创建一个拍照的意图
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                // 确保有处理拍照的活动
//                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                    // 启动拍照意图
//                    startActivityForResult(takePictureIntent, PICK_IMAGE_REQUEST);
//                } else {
//                    // 如果设备没有相机，显示消息
//                    Toast.makeText(UploadImgActivity.this, "相机不可用", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        uploadImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (filePath != null) {
//                    pd.show();
//
//                    // 生成唯一的文件名
//                    String timeStamp = String.valueOf(System.currentTimeMillis());
//                    String imageFileName = "image_" + timeStamp + ".jpg";
//
//                    // 创建 StorageReference
//                    StorageReference childRef = storageRef.child(imageFileName);
//
//                    // 上传图像
//                    UploadTask uploadTask = childRef.putFile(filePath);
//
//                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            pd.dismiss();
//                            Toast.makeText(UploadImgActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            pd.dismiss();
//                            Toast.makeText(UploadImgActivity.this, "上传失败 -> " + e, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } else {
//                    Toast.makeText(UploadImgActivity.this, "选择照片", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
//            // 检查结果是否来自相机
//            if (data != null && data.getExtras() != null) {
//                // 获取拍摄的图像作为 Bitmap
//                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//
//                // 设置图像到 ImageView
//                imgView.setImageBitmap(bitmap);
//
//                // 创建一个 Uri 从 Bitmap，设置合适的 inSampleSize
//                int inSampleSize = 1; // 你可以根据实际需要调整这个值
//                filePath = getImageUri(getApplicationContext(), bitmap, inSampleSize);
//            }
//        }
//    }
//    private Uri getImageUri(Context context, Bitmap bitmap, int inSampleSize) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
//        return Uri.parse(path);
//    }
//}

//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class UploadImgActivity extends AppCompatActivity {
//    Button chooseImg, uploadImg;
//    ImageView imgView;
//    int PICK_IMAGE_REQUEST = 111;
//    Uri filePath;
//    ProgressDialog pd;
//
//    // 云端服务器的URL
//    private static final String URL = "https://images.bemfa.com/upload/v1/upimages.php";
//    // 图片格式
//    private static final String Contenttype = "JPG";
//    // 用户密钥
//    private static final String Authorization = "564710eeef9fa0683528faf9e3b5e079";
//    // 主题名称
//    private static final String Authtopic = "test1";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upload_img);
//
//        chooseImg = findViewById(R.id.chooseImg);
//        uploadImg = findViewById(R.id.uploadImg);
//        imgView = findViewById(R.id.imgView);
//
//        pd = new ProgressDialog(this);
//        pd.setMessage("正在上传....");
//
//        chooseImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 创建一个拍照的意图
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                // 确保有处理拍照的活动
//                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                    // 启动拍照意图
//                    startActivityForResult(takePictureIntent, PICK_IMAGE_REQUEST);
//                } else {
//                    // 如果设备没有相机，显示消息
//                    Toast.makeText(UploadImgActivity.this, "相机不可用", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        uploadImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (filePath != null) {
//                    pd.show();
//
//                    // 获取图像的字节数组
//                    Bitmap bitmap = null;
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                    byte[] data = baos.toByteArray();
//
//                    // 使用OkHttp发送POST请求上传图片
//                    OkHttpClient client = new OkHttpClient();
//                    RequestBody requestBody = new MultipartBody.Builder()
//                            .setType(MultipartBody.FORM)
//                            .addFormDataPart("image", "image.jpg", RequestBody.create(MediaType.parse("image/jpeg"), data))
//                            .build();
//
//                    Request request = new Request.Builder()
//                            .url(URL)
//                            .addHeader("Authorization", Authorization)
//                            .addHeader("Authtopic", Authtopic)
//                            .post(requestBody)
//                            .build();
//
//                    client.newCall(request).enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            e.printStackTrace();
//                            pd.dismiss();
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(UploadImgActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            if (response.isSuccessful()) {
//                                pd.dismiss();
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(UploadImgActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//                        }
//                    });
//                } else {
//                    Toast.makeText(UploadImgActivity.this, "选择照片", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////
////        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
////            // 检查结果是否来自相册
////            if (data != null && data.getData() != null) {
////                filePath = data.getData();
////                try {
////                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
////                    imgView.setImageBitmap(bitmap);
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }
//@Override
//protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//
//    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
//        // 检查结果是否来自相机
//        if (data != null && data.getExtras() != null) {
//            // 获取拍摄的图像作为 Bitmap
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//
//            // 设置图像到 ImageView
//            imgView.setImageBitmap(bitmap);
//
//            // 将 Bitmap 保存为文件
//            saveBitmapToFile(bitmap);
//        }
//    }
//}
//
//    // 将 Bitmap 保存为文件并返回文件路径
//    private void saveBitmapToFile(Bitmap bitmap) {
//        // 创建一个唯一的文件名
//        String timeStamp = String.valueOf(System.currentTimeMillis());
//        String imageFileName = "image_" + timeStamp + ".jpg";
//
//        // 将 Bitmap 保存为文件
//        File file = new File(getFilesDir(), imageFileName);
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//            filePath = Uri.fromFile(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

//TODO:UploadImgActivity
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadImgActivity extends AppCompatActivity {
    ImageButton chooseImg, uploadImg;
    ImageView imgView;
    int PICK_IMAGE_REQUEST = 111;
    Uri filePath;
    ProgressDialog pd;

    // 云端服务器的URL
    private static final String URL = "https://images.bemfa.com/upload/v1/upimages.php";
    // 图片格式
    private static final String Contenttype = "JPG";
    // 用户密钥
    private static final String Authorization = "564710eeef9fa0683528faf9e3b5e079";
    // 主题名称
    private static final String Authtopic = "test1";

    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_img);

        chooseImg = findViewById(R.id.chooseImg);
        uploadImg = findViewById(R.id.uploadImg);
        imgView = findViewById(R.id.imgView);

        pd = new ProgressDialog(this);
        pd.setMessage("正在上传....");

        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath != null) {
                    pd.show();
                    // 获取图片的字节数组
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                        // 压缩图片并返回File对象
                        File compressedFile = compressImage(bitmap, new File(currentPhotoPath));
                        filePath = Uri.fromFile(compressedFile);
                        imgView.setImageURI(filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data = baos.toByteArray();

                    // 使用OkHttp发送POST请求上传图片
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), data);

                    Request request = new Request.Builder()
                            .url(URL)
                            .addHeader("Contenttype", Contenttype)
                            .addHeader("Authorization", Authorization)
                            .addHeader("Authtopic", Authtopic)
                            .post(requestBody)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                            pd.dismiss();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UploadImgActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                pd.dismiss();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UploadImgActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                } else {
                    Toast.makeText(UploadImgActivity.this, "选择照片", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File file = new File(currentPhotoPath);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            File compressedFile = compressImage(bitmap, file);
            filePath = Uri.fromFile(compressedFile);
            imgView.setImageURI(filePath);
        }
    }


    private File compressImage(Bitmap bitmap, File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 90; // 初始压缩质量设为90
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        while (baos.toByteArray().length / 1024 > 18 && quality > 10) {
            baos.reset();
            quality -= 10; // 递减压缩质量
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}


