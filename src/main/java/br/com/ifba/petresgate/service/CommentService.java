/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.service;

import br.com.ifba.petresgate.domain.Animal;
import br.com.ifba.petresgate.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.ifba.petresgate.domain.DTOs.CommentDTO;
import br.com.ifba.petresgate.repository.AnimalRepository;
import br.com.ifba.petresgate.repository.CommentRepository;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author lara
 */

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AnimalRepository animalRepository;

    public void addComment(Long animalId, CommentDTO commentDto) {
        Animal animal = this.animalRepository.findById(animalId).orElseThrow(
                () -> new RuntimeException("Animal not found")
        );
        
        Comment comment = Comment.builder().animal(animal).content( commentDto.getContent())
                .fullname(commentDto.getFullname()).timeStamp(LocalDateTime.now())
                .build();
        
        this.commentRepository.save(comment);
    }
    
    public List<Comment> getCommentsByAimalId(Long animalId){
        return this.commentRepository.findByAnimalId(animalId);
    }
}
