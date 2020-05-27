package o.masllo.wsgeocoding.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import o.masllo.wsgeocoding.exception.RemoteConnectionException;
import o.masllo.wsgeocoding.exception.UploadedCSVFileException;
import o.masllo.wsgeocoding.model.dto.Coordinates;
import o.masllo.wsgeocoding.model.dto.Location;
import o.masllo.wsgeocoding.model.dto.Response;
import o.masllo.wsgeocoding.util.FileUtil;

@Service
public class GeocodingServiceImpl implements GeocodingService {
	
	Logger logger = LoggerFactory.getLogger(GeocodingServiceImpl.class);
	private static final String MALFORMED_PARAM = "Malformed request: file contains errors";
	private static final String MALFORMED_FILE = "Malformed request: file contains errors";

	@Value("${geocoding.api.key}")
	String apikey;

	@Autowired
	FileUtil fileUtil;

	@Override
	public void validateRequest(String address) throws HttpMessageNotReadableException {
		
		if(address == null || address.equals("")) {
			logger.error(MALFORMED_PARAM);
			throw new HttpMessageNotReadableException(MALFORMED_PARAM);
		}
	}
	
	@Override
	public void validateRequest(MultipartFile file) throws HttpMessageNotReadableException {

		if(file == null || file.getSize() == 0) {
			logger.error(MALFORMED_FILE);
			throw new HttpMessageNotReadableException(MALFORMED_FILE);			
		}
	}
	
	@Override
	public Response getResponse(MultipartFile file) throws UploadedCSVFileException, RemoteConnectionException {
		
		try {
			Response rs = getResponse(fileUtil.convertMultipartFileToList(file));
			
			return rs;
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new UploadedCSVFileException(e.getMessage());
		}
	}
	
	@Override
	public Response getResponse(List<String> addresses) throws RemoteConnectionException {
		Response rs = new Response();
		List<Location> locations = new ArrayList<>();
		for (String a : addresses)
			locations.add(getLocation(a));
		rs.setLocations(locations);
		
		return rs;
	}

	private Location getLocation(String a) throws RemoteConnectionException {
		Location location = new Location();
		location.setAddress(a);
		location.setCoordinates(getCoordinates(a));
		
		return location;
	}

	private Coordinates getCoordinates(String address) throws RemoteConnectionException {
		Coordinates coordinates = new Coordinates();
		GeoApiContext context = new GeoApiContext.Builder().apiKey(apikey).build();
		GeocodingResult[] results;		
		
		try {
			results = GeocodingApi.geocode(context, address).await();
		} catch (ApiException | InterruptedException | IOException e) {
			logger.error(e.getMessage());
			throw new RemoteConnectionException(e.getMessage());
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		coordinates.setLon(new Double(gson.toJson(results[0].geometry.location.lng)));
		coordinates.setLat(new Double(gson.toJson(results[0].geometry.location.lat)));
		
		return coordinates;
	}

}
