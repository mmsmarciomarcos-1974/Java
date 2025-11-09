package br.com.portofoliodomarcio.erp.moduloproduto;

import org.springframework.data.jpa.repository.JpaRepository;

// 1. Diz ao Spring: "Esta é uma interface de repositório"
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // É SÓ ISSO. NÃO PRECISA DE MAIS NADA.
    
}