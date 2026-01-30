package com.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.note.entity.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {

}
