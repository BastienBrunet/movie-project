package com.mouvie.client.controller;

import com.mouvie.library.service.storage.IFileSystemStorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.Objects;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileController {

    private final IFileSystemStorageService storageService;

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
                "image/" + Objects.requireNonNull(file.getFilename()).split("\\.")[1]).body(file);
    }
}
