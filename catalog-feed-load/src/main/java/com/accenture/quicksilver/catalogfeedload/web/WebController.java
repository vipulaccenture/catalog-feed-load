package com.accenture.quicksilver.catalogfeedload.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Optional;
import java.util.OptionalDouble;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.quicksilver.catalogfeedload.integration.PersistService;
import com.accenture.quicksilver.catalogfeedload.integration.messages.AggregateRequest;
import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.ProductCatalog;
import com.facebook.ads.sdk.ProductCatalog.APIRequestCreateProductFeed;
import com.facebook.ads.sdk.ProductCatalogDataSource;
import com.facebook.ads.sdk.ProductFeed;
import com.facebook.ads.sdk.ProductFeed.APIRequestCreateUpload;

@RestController
public class WebController {

	@Autowired
	private MessageChannel aggregationChannel;

	@GetMapping("/trigger")
	boolean triggerFlow() {
		return aggregationChannel.send(new GenericMessage<>(new AggregateRequest()));
	}
	
	private static final Logger log = LoggerFactory.getLogger(WebController.class);

	public static final APIContext context = new APIContext(
			"EAAEgBvqwYpsBO6JeUZCnbnr2E1ZCsOvdE1SPOWe0bMuhIVSB1HZAVN4pT08vmuVjL0IZBZA0waSaTLj6VW77gz7ZCRUIyjJbbjSHBxYdqOeDvuSJVVWeuFuBHy3GFyPiESo8FWsSIpa6F0bxJWz8ZCyjtsssYQV1ACFqa1RdNcvYoD9MN4A1nHKrOGHkzGCcTuYyCoSkUJGkocZAydZCO6Y9WyjSaX6p7");
	@PostMapping("/uploadAPI")
	public void uploadAPI() {
		try {

			log.info("-----------call to fetch product_feed_id--------- ");
			APINodeList<ProductCatalogDataSource> apiNodeList = new ProductCatalog(650561566983178l, context)
					.getDataSources().requestAllFields().execute();
			ProductFeed productFeedobj = null;
			String productFeedId = null;

			if (apiNodeList == null || apiNodeList.isEmpty()) {
				log.info("----------- call to create product_feed --------- ");
				productFeedobj = new ProductCatalog(650561566983178l, context).createProductFeed().setName("Shirts")
						.execute();
				productFeedId = productFeedobj.getId();
			} else {
				Optional<String> id = apiNodeList.stream().map(ProductCatalogDataSource::getId).max(String::compareTo);
				productFeedId = id.get();
			}
			String path = "products.csv";
			Resource resource = new ClassPathResource(path);
			File csvFile = resource.getFile();

			ProductFeed productFeed = new ProductFeed(productFeedId, context);
			log.info("----------- call to /upload end point --------- ");
			APIRequestCreateUpload uploadRequest = productFeed.createUpload().addUploadFile("file", csvFile);
			uploadRequest.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping("/product_feeds")
	public void productFeedsAPI() {
		try {

			ProductCatalog productFeedobj = null;
			String productFeedId = null;

			productFeedobj = new ProductCatalog(650561566983178l, context);
					
			APIRequestCreateProductFeed uploadRequest = productFeedobj
					.createProductFeed().setName("T-shirts")
					.setSchedule("{ \"interval\": \"DAILY\", \"url\": \"http://www.example.com/Catalog_feed.tsv\", \"hour\": \"20\"}");
				
					uploadRequest.execute();
			
			productFeedId = productFeedobj.getId().toString();
			log.info("----------- productFeedId --------- "+productFeedId);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
