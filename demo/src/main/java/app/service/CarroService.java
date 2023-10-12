package app.service;

import app.dto.CarroDTO;
import app.entity.Carro;
import app.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;

	public CarroDTO findById(Long id){
		Carro carro = carroRepository.findById(id).orElseThrow(() -> new RuntimeException("Id não encontrado!"));

		return toCarroDTO(carro);
	}

	public List<CarroDTO> listAll(){
		List<Carro> lista = carroRepository.findAll();
		List<CarroDTO> listaDTO = new ArrayList<>();

		for(int i=0; i<lista.size(); i++) 
			listaDTO.add(this.toCarroDTO(lista.get(i)));

		return listaDTO;
	}
	
	public CarroDTO save(CarroDTO carroDTO){
		Carro pessoa = this.toCarro(carroDTO);

		Carro pessoasalva = carroRepository.save(pessoa);

		return this.toCarroDTO(pessoasalva);
	}

	public CarroDTO edit(Long id, CarroDTO carroDTO){
		Carro carro = carroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id não encontrado!"));

		Assert.isTrue(carroDTO.getId().equals(carro.getId()), "Id's não conferem!");

		carro = toCarro(carroDTO);

		carroRepository.save(carro);

		return this.toCarroDTO(carro);
	}

	public void delete(Long id){
		carroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id não encontrado!"));

		carroRepository.deleteById(id);
	}

	private CarroDTO toCarroDTO(Carro carro) {
		CarroDTO carroDTO = new CarroDTO();
		carroDTO.setId(carro.getId());
		carroDTO.setModelo(carro.getModelo());
		carroDTO.setAno(carro.getAno());
		return carroDTO;
	}
	
	private Carro toCarro(CarroDTO carroDTO) {
		Carro carro = new Carro();
		carro.setId(carroDTO.getId());
		carro.setModelo(carroDTO.getModelo());
		carro.setAno(carroDTO.getAno());
		return carro;
	}

}
