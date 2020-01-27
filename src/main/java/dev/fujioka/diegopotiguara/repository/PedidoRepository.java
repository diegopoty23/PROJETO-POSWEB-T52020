package dev.fujioka.diegopotiguara.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.fujioka.diegopotiguara.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	List<Pedido> findByOrderByIdDesc();

	@Query("SELECT SUM(valorTotal) from Pedido")
	public Double calcularValorTotalEmCaixa();
	
	@Query("SELECT Count(id) from Pedido")
	public Integer quantidadeDeVendasEmGeral();
	
	@Query("SELECT Count(id) from Pedido")
	public Integer quantidadeDeVendasEmGeralNoMesAtual();
	
	@Query(value = "select * from pedido where status != 'FINALIZADO'", nativeQuery = true)
	public List<Pedido> pedidosEmAberto();
	
	
	

}
