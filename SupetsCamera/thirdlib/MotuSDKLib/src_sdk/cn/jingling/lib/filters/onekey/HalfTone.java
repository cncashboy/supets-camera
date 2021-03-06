package cn.jingling.lib.filters.onekey;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import cn.jingling.lib.filters.CMTProcessor;
import cn.jingling.lib.filters.Layer;
import cn.jingling.lib.filters.OneKeyFilter;

public class HalfTone extends OneKeyFilter {

	@Override
	public Bitmap apply(Context cx, Bitmap bm) {
		// TODO Auto-generated method stub
		int w = bm.getWidth();
		int h = bm.getHeight();
		int[] pixels = new int[w * h];
		int[] model = new int[20 * 20];
		bm.getPixels(pixels, 0, w, 0, 0, w, h);
		
		Bitmap tmpBm = Layer.getLayerImage(cx, "layers/dot10", 20, 20, Layer.Type.NORMAL, 100, true);
		
		tmpBm.getPixels(model, 0, 20, 0, 0, 20, 20);
		
		CMTProcessor.HalfTone(pixels, w, h, model, 20, 20, 1);
		
		bm.setPixels(pixels, 0, w, 0, 0, w, h);
		
		return bm;
	}

}
