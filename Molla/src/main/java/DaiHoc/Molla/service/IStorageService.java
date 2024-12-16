package DaiHoc.Molla.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	void init();
	void store(MultipartFile file); 
	void setRootLocation(String s);
	
	void save(MultipartFile file);
	Resource load(String filename);
	Stream<Path> loadAll();
	void store(MultipartFile file, String storageLocation, int size);
}
