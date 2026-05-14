package br.com.shoestore.repository;

import br.com.shoestore.model.RoleEntity;

import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
