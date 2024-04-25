package com.project.shopappbackend.controller;

import com.project.shopappbackend.dtos.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    @GetMapping("")
    public ResponseEntity<String> getProducts(){
        return ResponseEntity.ok("here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String productId){
        return ResponseEntity.ok("ProductId: " + productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") String productId){
        return ResponseEntity.ok("Delete productId: " + productId);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(@Valid @ModelAttribute ProductDTO productDTO,
                                           BindingResult result/*,
                                           @RequestPart("file")MultipartFile file*/) throws IOException {
        if (result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .parallelStream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        List<MultipartFile> files = productDTO.getFiles();
        files = files == null ? new ArrayList<>() : files;
        for (MultipartFile file : files) {
            if (file.getSize() == 0) {
                continue;
            }
            if (file.getSize() > 10 * 2024 * 2024) { /* > 10MB */
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large! Maximum size is 10MB");
            }
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")){
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
            }
            String fileName = storeFile(file);
        }


        return ResponseEntity.ok("Success");
    }

    private String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        /*Đảm bảo tên file là duy nhất*/
        String uniqueFileName = UUID.randomUUID() + "_" + fileName;
        /*Đường dẫn thư mục muốn luwu file*/
        Path uploadDir = Paths.get("uploads");
        /*Kiểm tra thư mục đó có tồn tại hay không*/
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        /*Đường dẫn đầy đủ đến file*/
        Path description = Paths.get(uploadDir.toString(), uniqueFileName);
        /*Sao chép file vào thư mục đích*/
        Files.copy(file.getInputStream(), description, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }


}
