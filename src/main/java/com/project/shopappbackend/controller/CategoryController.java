package com.project.shopappbackend.controller;

import com.project.shopappbackend.dtos.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllCategories(@PathVariable("id") Long y,
                                              @RequestParam(value = "name", required = false) String myName,
                                              @RequestBody()CategoryDTO category, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).build();
        }
        return ResponseEntity.ok("ok " + y + " " + myName + category.getName());
    }

}
