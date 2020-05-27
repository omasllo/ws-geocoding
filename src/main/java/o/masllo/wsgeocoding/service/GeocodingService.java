package o.masllo.wsgeocoding.service;

import java.util.List;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.multipart.MultipartFile;

import o.masllo.wsgeocoding.exception.RemoteConnectionException;
import o.masllo.wsgeocoding.exception.UploadedCSVFileException;
import o.masllo.wsgeocoding.model.dto.Response;

public interface GeocodingService {

	void validateRequest(String address) throws HttpMessageNotReadableException;
	
	void validateRequest(MultipartFile file) throws HttpMessageNotReadableException;
	
	Response getResponse(List<String> addresses) throws RemoteConnectionException;

	Response getResponse(MultipartFile file) throws UploadedCSVFileException, RemoteConnectionException;

}
