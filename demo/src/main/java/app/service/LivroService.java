package app.service;

import app.dto.LivroDTO;
import app.entity.Livro;
import app.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	public LivroDTO findById(Long id){
		Livro livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Id não encontrado!"));

		return toPessoaDTO(livro);
	}

	public List<LivroDTO> listAll(){
		List<Livro> lista = livroRepository.findAll();
		List<LivroDTO> listaDTO = new ArrayList<>();

		for(int i=0; i<lista.size(); i++) 
			listaDTO.add(this.toPessoaDTO(lista.get(i)));

		return listaDTO;
	}
	
	public LivroDTO save(LivroDTO livroDTO){
		Livro livro = this.toPessoa(livroDTO);

		Livro livrosalva = livroRepository.save(livro);

		return this.toPessoaDTO(livrosalva);
	}

	public LivroDTO edit(Long id, LivroDTO livroDTO){
		Livro livro = livroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id não encontrado!"));

		Assert.isTrue(livroDTO.getId().equals(livro.getId()), "Id's não conferem!");

		livro = toPessoa(livroDTO);

		livroRepository.save(livro);

		return this.toPessoaDTO(livro);
	}

	public void delete(Long id){
		livroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id não encontrado!"));

		livroRepository.deleteById(id);
	}

	private LivroDTO toPessoaDTO(Livro livro) {
		LivroDTO livroDTO = new LivroDTO();
		livroDTO.setId(livro.getId());
		livroDTO.setTitulo(livro.getTitulo());
		livroDTO.setAutor(livro.getAutor());
		return livroDTO;
	}
	
	private Livro toPessoa(LivroDTO livroDTO) {
		Livro livro = new Livro();
		livro.setId(livroDTO.getId());
		livro.setTitulo(livroDTO.getTitulo());
		livro.setAutor(livroDTO.getAutor());
		return livro;
	}

}
