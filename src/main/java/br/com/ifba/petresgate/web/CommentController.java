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
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author lara
 */
@RestController
@RequestMapping("/api/animals/{animalId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> addComment(@PathVariable Long animalId, @Valid @RequestBody CommentDTO commentDto) {
        commentService.addComment(animalId, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment added sucessfully");
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByAnimal(@PathVariable Long animalId) {
        List<Comment> comments = this.commentService.getCommentsByAimalId(animalId);

        return ResponseEntity.ok(comments);
    }
}
