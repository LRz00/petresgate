/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.petresgate.repository;

import br.com.ifba.petresgate.domain.AppUser;
import br.com.ifba.petresgate.domain.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 *
 * @author lara
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    Page<Comment> findByAnimalId(Long animalId, Pageable pageable);
}
