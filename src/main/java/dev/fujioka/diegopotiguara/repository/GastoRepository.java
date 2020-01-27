package dev.fujioka.diegopotiguara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.fujioka.diegopotiguara.modelo.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long>{

	@Query("SELECT SUM(valorGasto) from Gasto")
	public Double calcularValorTotalGasto();
}
