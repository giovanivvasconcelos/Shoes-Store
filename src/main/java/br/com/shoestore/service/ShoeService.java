package br.com.shoestore.service;

import br.com.shoestore.model.Shoe;
import br.com.shoestore.repository.ShoeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService {

    private final ShoeRepository shoeRepository;

    public ShoeService(ShoeRepository shoeRepository){
        this.shoeRepository = shoeRepository;
    }

    public List<Shoe> findAll(){
        return shoeRepository.findAll();
    }

    public Shoe findById(Long id){
        return shoeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tênis não encontrado"));
    }

    public Shoe save(Shoe shoe){
        return shoeRepository.save(shoe);
    }

    public void deleteById(Long id){
        shoeRepository.deleteById(id);
    }
}
