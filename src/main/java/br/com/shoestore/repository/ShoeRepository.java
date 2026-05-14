package br.com.shoestore.repository;

import br.com.shoestore.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ShoeRepository extends JpaRepository<Shoe, Long> {

}
