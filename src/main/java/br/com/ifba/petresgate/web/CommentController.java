/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.web;

import br.com.ifba.petresgate.domain.Comment;
import br.com.ifba.petresgate.domain.DTOs.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.petresgate.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lara
 */
@RestController
@RequestMapping("/api/animals/{animalId}/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> addComment(@PathVariable Long animalId, @Valid @RequestBody CommentDTO commentDto) {
        commentService.addComment(animalId, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment added sucessfully");
    }

    @GetMapping
    public ResponseEntity<Page<Comment>> getCommentsByAnimal(@PathVariable Long animalId, @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
             @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        
         var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        
         Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));
        
        Page<Comment> comments = this.commentService.getCommentsByAnimalId(animalId, pageable);

        return ResponseEntity.ok(comments);
    }
}
