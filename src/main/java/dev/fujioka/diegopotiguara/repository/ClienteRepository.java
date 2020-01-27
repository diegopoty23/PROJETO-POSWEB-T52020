package dev.fujioka.diegopotiguara.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.fujioka.diegopotiguara.modelo.Cliente;
import dev.fujioka.diegopotiguara.modelo.Pedido;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	List<Cliente> findByOrderByNomeClienteAsc();
	List<Cliente>findByNomeCliente(String nome);
	List<Cliente>findByTelefone(String telefone);
	
	@Query("SELECT Count(id) from Cliente")
	public Integer quantidadeDeClientesEmGeral();

}
