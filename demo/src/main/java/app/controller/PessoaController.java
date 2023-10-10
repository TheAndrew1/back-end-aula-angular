package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.dto.PessoaDTO;
import app.service.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	private ResponseEntity<List<PessoaDTO>> listAll(){
		try {		
			List<PessoaDTO> lista = pessoaService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	private ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
		try {		
			PessoaDTO pessoaSalva = pessoaService.save(pessoaDTO);
			return new ResponseEntity<>(pessoaSalva, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	private ResponseEntity<PessoaDTO> edit(@RequestParam("id") Long id, @RequestBody PessoaDTO pessoaDTO){
		try {
			PessoaDTO pessoaEditada = pessoaService.edit(id, pessoaDTO);
			return new ResponseEntity<>(pessoaEditada, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	private ResponseEntity<PessoaDTO> delete(@RequestParam("id") Long id){
		try {
			pessoaService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("erro")
	private ResponseEntity<List<PessoaDTO>> exemploErro(){
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
