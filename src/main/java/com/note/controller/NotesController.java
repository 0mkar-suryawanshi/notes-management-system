package com.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note.entity.Notes;
import com.note.entity.ResponseStructure;
import com.note.service.NotesService;

@RestController
@RequestMapping("/notes")
public class NotesController {

	@Autowired
	private NotesService notesService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseStructure<Notes>> createNote(@RequestBody Notes note){
		return notesService.createNote(note);
	}
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<ResponseStructure<Notes>> updateNotes(@RequestBody Notes notes, @PathVariable Integer id){
		return notesService.updateNotes(notes, id);
	}
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteNotes (@PathVariable Integer id)
	{
		return notesService.deleteNotes(id);
	}
	@GetMapping("/getNotes")
	public ResponseEntity<ResponseStructure<java.util.List<Notes>>> viewAllNotes(){
		return notesService.viewAllNotes();
	}
	

}
