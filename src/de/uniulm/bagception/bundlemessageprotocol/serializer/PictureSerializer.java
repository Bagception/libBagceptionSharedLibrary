package de.uniulm.bagception.bundlemessageprotocol.serializer;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class PictureSerializer {

	public static String serialize(Bitmap picture){
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		picture.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		
		String enc=Base64.encodeToString(byteArray, Base64.DEFAULT);
		
		return enc;
	}
	
	public static Bitmap deserialize(String s){
		byte[] bytes = Base64.decode(s, Base64.DEFAULT);
		Bitmap bmp;
		
		bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return bmp;
	}
}
