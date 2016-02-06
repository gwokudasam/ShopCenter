package com.exequiel.shopcenter.componentes.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UtilImagen {

	// Decodes image and scales it to reduce memory consumption
	public static Bitmap decodeFile(File f){
	    try {
	        // Decode image size
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeStream(new FileInputStream(f), null, o);
	        // The new size we want to scale to
	        final int REQUIRED_SIZE=350;
	        // Find the correct scale value. It should be the power of 2.
	        int scale = 1;
	        while(o.outWidth / scale / 2 >= REQUIRED_SIZE && 
	              o.outHeight / scale / 2 >= REQUIRED_SIZE) {
	            scale *= 2;
	        }
	        // Decode with inSampleSize
	        BitmapFactory.Options o2 = new BitmapFactory.Options();
	        o2.inSampleSize = scale;
	        return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
	    } catch (FileNotFoundException e) {}
	    return null;
	}

	public static void cutImage(){
	/*
		Matrix matrix = new Matrix();
		matrix.postScale(0.5f, 0.5f);
		Bitmap original = BitmapFactory.decodeResource(view.getResources(), R.drawable.img_cargando_qr);
		Bitmap croppedBitmap = Bitmap.createBitmap(original, 50, 50,50, 50, matrix, true);
		imgUsr.setImageBitmap(croppedBitmap);
	*/
	}

	public static Bitmap getBitmapFromUri(Activity activity,String path){
		Bitmap bitmap = null;
		Uri imageUri = Uri.parse(path);
		try {
			bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), imageUri);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static RoundedBitmapDrawable getRoundedNativeBitmap(Context context, int resources){
		Drawable originalDrawable = context.getResources().getDrawable(resources);
		Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
		RoundedBitmapDrawable roundedDrawable =
				RoundedBitmapDrawableFactory.create(context.getResources(), originalBitmap);
		roundedDrawable.setCornerRadius(originalBitmap.getHeight());
		return roundedDrawable;
	}

	public static RoundedBitmapDrawable getRoundedNativeBitmap(Context context, Bitmap originalBitmap){
		RoundedBitmapDrawable roundedDrawable =
				RoundedBitmapDrawableFactory.create(context.getResources(), originalBitmap);
		roundedDrawable.setCornerRadius(originalBitmap.getHeight());
		return roundedDrawable;
	}

		public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
					.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(output);

			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			final RectF rectF = new RectF(rect);
			final float roundPx = pixels;

			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);

			return output;
		}

	public static Bitmap getRoundedCornerBitmap( Drawable drawable, boolean square) {
		int width = 0;
		int height = 0;

		Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap() ;

		if(square){
			if(bitmap.getWidth() < bitmap.getHeight()){
				width = bitmap.getWidth();
				height = bitmap.getWidth();
			} else {
				width = bitmap.getHeight();
				height = bitmap.getHeight();
			}
		} else {
			height = bitmap.getHeight();
			width = bitmap.getWidth();
		}

		Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, width, height);
		final RectF rectF = new RectF(rect);
		final float roundPx = 90;
///data/data/com.jeanscentre.mobile/app_Imagenes/EstaImagen.png.png
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static String guardarImagen (Context context, String nombre, Bitmap imagen){
		File dirImages = UtilDirectorio.getDirectorioImagenes(context);
		File myPath = new File(dirImages, nombre);
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(myPath);

			imagen.compress(Bitmap.CompressFormat.PNG,100,fos);
			fos.flush();
		}catch (FileNotFoundException ex){
			ex.printStackTrace();
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return myPath.getAbsolutePath();
	}

	public static Point getDimOfImageView(ImageView imageView){
		int actualHeight, actualWidth;
		int imageViewHeight = imageView.getHeight(), imageViewWidth = imageView.getWidth();
		int bitmapHeight = imageView.getDrawable().getIntrinsicHeight();
		int bitmapWidth = imageView.getDrawable().getIntrinsicWidth();
		if (imageViewHeight * bitmapWidth <= imageViewWidth * bitmapHeight) {
			actualWidth = bitmapWidth * imageViewHeight / bitmapHeight;
			actualHeight = imageViewHeight;
		} else {
			actualHeight = bitmapHeight * imageViewWidth / bitmapWidth;
			actualWidth = imageViewWidth;
		}
		return new Point(actualHeight,actualWidth);
	}
}
