package br.com.fiap.ecosave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.ecosave.model.Comodo;

@Repository
public interface ComodoRepository extends JpaRepository<Comodo, Long> {

}
