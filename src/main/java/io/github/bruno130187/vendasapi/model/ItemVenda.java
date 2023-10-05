package io.github.bruno130187.vendasapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column
    private Integer quantidade;

    public ItemVenda() {
    }

    public ItemVenda(Long id, Venda venda, Produto produto, Integer quantidade) {
        this.id = id;
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemVenda(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemVenda itemVenda = (ItemVenda) o;
        return Objects.equals(id, itemVenda.id) && Objects.equals(venda, itemVenda.venda) && Objects.equals(produto, itemVenda.produto) && Objects.equals(quantidade, itemVenda.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, venda, produto, quantidade);
    }

    @Override
    public String toString() {
        return "ItemVenda[" +
                "id=" + id +
                ", venda=" + venda +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ']';
    }
}
