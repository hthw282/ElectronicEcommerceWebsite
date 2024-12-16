package DaiHoc.Molla.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageFileFounder {
	public static List<String> findImageFiles(String folderPath) {
        List<String> imageFiles = new ArrayList<>();
        
        File folder = new File(folderPath);
        
        // Kiểm tra xem thư mục có tồn tại không
        if (folder.exists() && folder.isDirectory()) {
            // Lấy danh sách tất cả các tệp trong thư mục
            File[] files = folder.listFiles();
            if (files != null) {
                // Lặp qua từng tệp và kiểm tra xem có phải là tệp ảnh không
                for (File file : files) {
                    if (file.isFile() && isImageFile(file.getName())) {
                        imageFiles.add(file.getName());
                    }
                }
            }
        } else {
            System.err.println("Thư mục không tồn tại hoặc không phải là thư mục.");
        }
        
        return imageFiles;
    }
    
    public static boolean isImageFile(String filename) {
        // Kiểm tra phần mở rộng của tệp để xác định xem nó có phải là một tệp ảnh không
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif");
    }
}
