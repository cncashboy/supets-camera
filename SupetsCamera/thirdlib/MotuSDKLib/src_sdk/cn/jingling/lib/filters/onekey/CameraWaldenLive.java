package cn.jingling.lib.filters.onekey;

import android.content.Context;
import android.graphics.Bitmap;
import cn.jingling.lib.filters.CMTProcessor;
import cn.jingling.lib.filters.Curve;
import cn.jingling.lib.filters.Layer;
import cn.jingling.lib.filters.OneKeyFilter;

public class CameraWaldenLive extends OneKeyFilter {

	@Override
	public Bitmap apply(Context cx, Bitmap bm) {
		// TODO Auto-generated method stub
		this.statisticEvent();
		int w = bm.getWidth();
		int h = bm.getHeight();
		int[] pixels = new int[w * h];
		bm.getPixels(pixels, 0, w, 0, 0, w, h);
		// pixels = ImageProcessUtils.saturationPs(pixels, w, h, -100);
		
		Curve c = new Curve(cx, "curves/live_walden.dat");
		CMTProcessor.curveEffect(pixels, c.getCurveRed(), c.getCurveGreen(),
				c.getCurveBlue(), w, h);

		CMTProcessor.overlayAlphaEffect(pixels, Layer.getLayerPixels(cx,
				"layers/live_walden", w, h, Layer.Type.NORMAL), w, h, 50);

		bm.setPixels(pixels, 0, w, 0, 0, w, h);
		pixels = null;
		return bm;
	}

}
