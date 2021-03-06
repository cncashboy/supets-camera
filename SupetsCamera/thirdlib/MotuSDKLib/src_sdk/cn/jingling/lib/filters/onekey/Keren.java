package cn.jingling.lib.filters.onekey;

import android.content.Context;
import android.graphics.Bitmap;
import cn.jingling.lib.filters.CMTProcessor;
import cn.jingling.lib.filters.Curve;
import cn.jingling.lib.filters.ImageProcessUtils;
import cn.jingling.lib.filters.Layer;
import cn.jingling.lib.filters.OneKeyFilter;

public class Keren extends OneKeyFilter {
	@Override
	public Bitmap apply(Context cx, Bitmap bm) {
		this.statisticEvent();
		int w = bm.getWidth();
		int h = bm.getHeight();
			
		int[] pixels = new int[w * h];
		bm.getPixels(pixels, 0, w, 0, 0, w, h);
		ImageProcessUtils.skinSmooth(cx,pixels,w,h,3);
		ImageProcessUtils.saturationPs(pixels, w, h, -20);
		Curve c = new Curve(cx, "curves/ke_ren.dat");
		CMTProcessor.curveEffect(pixels, c.getCurveRed(), c.getCurveGreen(), c.getCurveBlue(), w, h);
		int[] layer = Layer.getLayerPixels(cx, "layers/ke_ren", w, h, Layer.Type.NORMAL, 50);
		CMTProcessor.multiplyEffect(pixels, layer, w, h);
		
		bm.setPixels(pixels, 0, w, 0, 0, w, h);
		pixels = null;
		return bm;
	}

}
