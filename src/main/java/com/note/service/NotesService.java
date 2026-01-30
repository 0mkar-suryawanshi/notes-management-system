package com.note.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.note.entity.Notes;
import com.note.entity.ResponseStructure;
import com.note.exception.NotesNotFoundException;
import com.note.repository.NotesRepository;

@Service
public class NotesService {

	@Autowired
	private NotesRepository notesRepository;

	// CREATE A NOTES
	public ResponseEntity<ResponseStructure<Notes>> createNote(Notes notes) {

		ResponseStructure<Notes> structure = new ResponseStructure<>();
		structure.setData(notesRepository.save(notes));
		structure.setMessage("Created Successfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Notes>>(structure, HttpStatus.CREATED);
	}

	// UPDATES NOTES
	public ResponseEntity<ResponseStructure<Notes>> updateNotes(Notes notes, Integer id) {
		ResponseStructure<Notes> structure = new ResponseStructure<Notes>();
		Optional<Notes> recNotes = notesRepository.findById(id);

		if (recNotes.isPresent()) {
			Notes existNotes = recNotes.get();
			existNotes.setTitle(notes.getTitle());
			existNotes.setContent(notes.getContent());

			Notes updateNotes = notesRepository.save(existNotes);
			structure.setData(updateNotes);
			structure.setMessage("Notes Update Successfully");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<Notes>>(structure, HttpStatus.OK);
		}
		throw new NotesNotFoundException("Notes Not found by ID : " + id);
	}
	
	//DELETE API
	public ResponseEntity<ResponseStructure<String>> deleteNotes (Integer id)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		Optional<Notes> recNotes = notesRepository.findById(id);
		if(recNotes.isPresent())
		{
			notesRepository.deleteById(id);
			structure.setData("Notes Deleted successfully");
			structure.setMessage("Deleted Succes");
			structure.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<String>> (structure, HttpStatus.OK);
		}
		throw new NotesNotFoundException("Notes Not Found");
	}
	//GET ALL RECORD
	public ResponseEntity<ResponseStructure<java.util.List<Notes>>> viewAllNotes(){
		
		ResponseStructure<List<Notes>> structure = new ResponseStructure<List<Notes>>();
		List<Notes> recNotes = notesRepository.findAll();
		
		if(recNotes.size()>0)
		{
			structure.setMessage("Notes Found");
			structure.setData(recNotes);
			structure.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<List<Notes>>>(structure, HttpStatus.OK);
		}
		throw new NotesNotFoundException("Notes Not Found Exception");
		
	}
}
