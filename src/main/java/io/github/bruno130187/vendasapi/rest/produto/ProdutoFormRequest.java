package io.github.bruno130187.vendasapi.rest.produto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.bruno130187.vendasapi.model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoFormRequest {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String sku;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    public ProdutoFormRequest() {

    }

    public ProdutoFormRequest(String nome, String descricao, BigDecimal preco, String sku, LocalDate dataCadastro) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.sku = sku;
        this.dataCadastro = dataCadastro;
    }

    public ProdutoFormRequest(Long id, String nome, String descricao , BigDecimal preco, String sku, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.sku = sku;
        this.dataCadastro = dataCadastro;
    }

    public Produto toModel() {
        return new Produto(id, nome, descricao, preco.setScale(2, BigDecimal.ROUND_UP), sku, dataCadastro);
    }

    public static ProdutoFormRequest fromModel(Produto produto) {
        return new ProdutoFormRequest(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco().setScale(2, BigDecimal.ROUND_UP),
                produto.getSku(),
                produto.getDataCadastro()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "ProdutoFormRequest [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco
                + ", sku=" + sku + "]";
    }
}
