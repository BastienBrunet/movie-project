package com.mouvie.library.service.storage;

import com.mouvie.library.exception.StorageException;
import com.mouvie.library.exception.StorageFileNotFoundException;
import com.mouvie.library.tools.Base64Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements IFileSystemStorageService {

    private final Path rootLocation = Paths.get("upload-dir");

    @Override
    public void store(String fileContentAsBase64, String filename, String fileExtension) {
        try {
            if (fileContentAsBase64.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            byte[] inputStream = Base64Helper.decodeBase64ToBytes(fileContentAsBase64);

            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(filename + fileExtension))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }

            Files.write(destinationFile, inputStream);
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.");
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files");
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
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
                        "File not found: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("File not found: " + filename);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage");
        }
    }
}
