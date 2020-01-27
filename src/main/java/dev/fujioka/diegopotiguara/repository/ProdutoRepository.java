package dev.fujioka.diegopotiguara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.fujioka.diegopotiguara.modelo.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query("SELECT Count(id) from Produto")
	public Integer quantidadeDeProdutosEmGeral();

}
