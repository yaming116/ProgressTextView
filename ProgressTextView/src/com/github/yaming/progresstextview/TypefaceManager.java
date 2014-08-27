package com.github.yaming.progresstextview;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

public class TypefaceManager {

	private static final String TAG = "TypefaceManager";

	public static Map<String, Typeface> typefaces = new HashMap<String, Typeface>();

	public static Typeface getTypeface(Context context, String font) {
		if (typefaces.containsKey(font))
			return typefaces.get(font);
		else
			return loadTypeface(context, font);
	}

	private static synchronized Typeface loadTypeface(Context context,
			String font) {
		if (typefaces.containsKey(font)) {
			return typefaces.get(font);
		}

		Typeface typeface = null;

		try {
			String path = "fonts/" + font;
			typeface = Typeface.createFromAsset(context.getAssets(), path);
		} catch (RuntimeException e) {
			Log.e(TAG, "Failed to load typeface.");
		}

		// Even if we failed, store the result to prevent Android trying
		// repeatedly
		typefaces.put(font, typeface);
		return typeface;
	}

}
