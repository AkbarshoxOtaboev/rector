package uz.urspi.student.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path uploadLocation = Paths.get(new StorageProperties().getUploadsLocation());
    private final Path tempLocation = Paths.get(new StorageProperties().getTempLocation());

    @Override
    public void init() {
        try {
            Files.createDirectories(uploadLocation);
            Files.createDirectories(tempLocation);
            System.out.println("Location : "+uploadLocation.toAbsolutePath().toString());
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }

    }

    @Override
    public String store(MultipartFile file) {
        if(file == null){
            return "no_image.png";
        }
        String filename = getAndValidateFilename(file);
        try {
            Files.copy(file.getInputStream(), this.uploadLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new StorageException("Fayl yuklashda xatolik bo`ldi! ", e);
        }
    }

    @Override
    public Path load(String filename) {
        return uploadLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
    private String getAndValidateFilename(MultipartFile file) {
        if (file.isEmpty()) {
            return "no_image.png";
        }
        String name = file.getOriginalFilename();
        int ind = -1;
        for (int i=name.length()-1; i > 0; i--){
            if (name.charAt(i) =='.'){
                ind = i;
                break;
            }
        }
        if(ind < 0){
            throw new StorageException("Faylni yuklashdaa xatolik");
        }
        return UUID.randomUUID().toString()+name.substring(ind);
    }
    @Bean
    public StorageProperties createStorageProperites(){
        return new StorageProperties();
    }
}
