package com.accenture.quicksilver.catalogfeedload.productfeed;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.ProductFeed;
import java.io.File;


public class ProductFeedIntegration {

	public static final APIContext context = new APIContext("");

	public void uploadCSVToProductFeed() {
		try {
			String path = "path/to/catalog.csv";
			new ProductFeed(2061119917597675l, context)
			.createUpload()
			.addUploadFile("products", new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
