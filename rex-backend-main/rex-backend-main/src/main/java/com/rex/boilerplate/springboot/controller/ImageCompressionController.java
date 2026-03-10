package com.rex.boilerplate.springboot.controller;

import com.rex.boilerplate.springboot.dto.ImageCompressionResponse;
import com.rex.boilerplate.springboot.service.ImageCompressionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
@Tag(name = "Image Compression API", description = "Endpoints for uploading and compressing images")
public class ImageCompressionController {

    private final ImageCompressionService imageCompressionService;

    @Value("${app.image.max-size:5242880}") // 5MB default
    private long maxFileSize;

    @Value("${app.image.max-width:1024}")
    private int maxWidth;

    @Value("${app.image.max-height:1024}")
    private int maxHeight;

    @Value("${app.image.quality:0.8}")
    private float quality;

    @Operation(summary = "Upload and compress an image file")
    @PostMapping("/compress")
    public ResponseEntity<ImageCompressionResponse> compressImage(@RequestParam("file") MultipartFile file) {
        // Basic validation
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (file.getContentType() == null || ( file.getContentType() != null && !file.getContentType().startsWith("image/"))) {
            return ResponseEntity.badRequest().build();
        }
        if (file.getSize() > maxFileSize) {
            return ResponseEntity.badRequest().build();
        }

        ImageCompressionResponse response = imageCompressionService.compressAndSave(file, maxWidth, maxHeight, quality);
        return ResponseEntity.ok(response);
    }
}

