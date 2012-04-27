package org.source.comunicapa.utils;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;

public class Utils {

	private static final boolean ENABLE_LOG = true;
	private static final String TAG_LOG = "ComunicaPA";

	private static final HashMap<String, Typeface> typefaces = new HashMap<String, Typeface>();

	public static boolean isExternalStorageAvailable() {

		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media
			mExternalStorageWriteable = true;
		}

		return mExternalStorageWriteable;

	}

	public static String getExternalStorageFolder() {

		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	public static void log(String message) {

		if (ENABLE_LOG) {
			Log.d(TAG_LOG, message);
		}

	}

	public static Typeface getTypeface(Context context, String name) {
		if (typefaces.containsKey(name)) {
			return typefaces.get(name);
		} else {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), name);
			typefaces.put(name, typeface);
			return typeface;
		}
	}
}
