package o.masllo.wsgeocoding;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class GecodingAPITest {

	@Value("${geocoding.api.key}")
	String apikey;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGeoCoding() throws Exception {

		GeoApiContext context = new GeoApiContext.Builder().apiKey(apikey).build();
		GeocodingResult[] results = GeocodingApi.geocode(context, "1600 Amphitheatre Parkway Mountain View, CA 94043")
				.await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String lat = gson.toJson(results[0].geometry.location.lat);
		String lng = gson.toJson(results[0].geometry.location.lng);
		Assert.assertEquals(lat, "37.4223106");
		Assert.assertEquals(lng, "-122.0846328");

	}

}
