package app.controller;


import app.dto.LivroDTO;
import app.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin(origins = "http://localhost:4200")
public class LivroController {
	
	@Autowired
	private LivroService livroService;

	@GetMapping
	private ResponseEntity<LivroDTO> findById(Long id){
		try {
			LivroDTO livroDTO = livroService.findById(id);
			return new ResponseEntity<>(livroDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/lista")
	private ResponseEntity<List<LivroDTO>> listAll(){
		try {		
			List<LivroDTO> lista = livroService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	private ResponseEntity<LivroDTO> save(@RequestBody LivroDTO livroDTO){
		try {
			LivroDTO livroSalva = livroService.save(livroDTO);
			return new ResponseEntity<>(livroSalva, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	private ResponseEntity<LivroDTO> edit(@RequestParam("id") Long id, @RequestBody LivroDTO livroDTO){
		try {
			LivroDTO livroEditada = livroService.edit(id, livroDTO);
			return new ResponseEntity<>(livroEditada, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	private ResponseEntity<LivroDTO> delete(@RequestParam("id") Long id){
		try {
			livroService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("erro")
	private ResponseEntity<List<LivroDTO>> exemploErro(){
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
