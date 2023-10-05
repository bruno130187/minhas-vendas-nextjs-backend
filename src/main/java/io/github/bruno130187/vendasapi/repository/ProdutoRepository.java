package io.github.bruno130187.vendasapi.repository;

import io.github.bruno130187.vendasapi.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select p from Produto p where upper(p.nome) like upper(:nome) and p.descricao like :descricao")
    Page<Produto> buscarPorNomeDescricao (
            @Param("nome") String nome,
            @Param("descricao") String descricao,
            Pageable pageable);

}
