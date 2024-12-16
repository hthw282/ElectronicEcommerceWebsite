package DaiHoc.Molla.service.Imp;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import DaiHoc.Molla.service.IStorageService;

@Service
public class FileSystemStorageService implements IStorageService {

	private Path rootLocation;

	public FileSystemStorageService() {
		this.rootLocation = Paths.get("src/main/resources/static/assets/images/products");
	}

	@Override
	public void setRootLocation(String s) {
		this.rootLocation = Paths.get(s);
	}

	@Override
	public void store(MultipartFile file) {
		try {
			Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize()
					.toAbsolutePath();

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void store(MultipartFile file, String prefix, int size) {
		try {
			// Đọc tệp vào một BufferedImage
			BufferedImage originalImage = ImageIO.read(file.getInputStream());

			// Resize ảnh thành kích thước mới, ví dụ: 200x200
			BufferedImage resizedImage = Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH,
					size, size);

			// Chuyển đổi BufferedImage thành mảng byte
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(resizedImage, "jpg", baos);
			baos.flush();
			byte[] resizedImageBytes = baos.toByteArray();
			baos.close();

			// Lưu trữ tệp đã resize vào vị trí mong muốn

			Path destinationFile = this.rootLocation.resolve(Paths.get(prefix+file.getOriginalFilename())).normalize()
					.toAbsolutePath();
			Files.write(destinationFile, resizedImageBytes);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file of that name already exists.");
			}

			throw new RuntimeException(e.getMessage());
		}
	}

	public Resource load(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}

}
