package com.note.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.note.entity.ResponseStructure;

@RestControllerAdvice
public class NotesExceptionHandler {
	@ExceptionHandler(NotesNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNNFE(NotesNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(exception.getMessage());
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NO_CONTENT.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NO_CONTENT);
	}

}
