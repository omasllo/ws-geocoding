package o.masllo.wsgeocoding.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import o.masllo.wsgeocoding.exception.RemoteConnectionException;
import o.masllo.wsgeocoding.exception.UploadedCSVFileException;
import o.masllo.wsgeocoding.model.dto.Response;
import o.masllo.wsgeocoding.service.GeocodingService;

@RestController
class GeocodingController {

	@Autowired
	GeocodingService geocodingService;

	@RequestMapping(value = "/get-coords", method = RequestMethod.GET)
	public ResponseEntity<?> getCoordsFromParam(@RequestParam(required = true) String address) throws RemoteConnectionException {
		
		geocodingService.validateRequest(address);
		Response rs = geocodingService.getResponse(Arrays.asList(new String[] { address }));
		
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-coords/upload", method = RequestMethod.POST, consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upload(@RequestPart(value = "file", required = true) MultipartFile file)
			throws UploadedCSVFileException, RemoteConnectionException {
		
		geocodingService.validateRequest(file);
		Response rs = geocodingService.getResponse(file);
		
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}
	
	

}
