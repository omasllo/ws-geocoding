package o.masllo.wsgeocoding.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

	public List<String> convertMultipartFileToList(MultipartFile file) throws IOException {

		InputStream inputStream = new BufferedInputStream(file.getInputStream());
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
		List<String>  lines = new BufferedReader(new StringReader(writer.toString()))
				.lines()
				.collect(Collectors.toList());
		
		return lines;
	}

}
