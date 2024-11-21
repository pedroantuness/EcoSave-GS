package br.com.fiap.ecosave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.ecosave.model.Ponto;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

}
