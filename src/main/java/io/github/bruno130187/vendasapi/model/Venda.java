package io.github.bruno130187.vendasapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento")
    private FormaPagamento formaPagamento;

    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itens;

    @Column
    private BigDecimal total;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_venda")
    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDateTime.now());
    }

    public Venda() {
    }

    public Venda(Long id, Cliente cliente, FormaPagamento formaPagamento, List<ItemVenda> itens, String total, LocalDateTime dataCadastro) {
        this.id = id;
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.itens = itens;
        this.total = converterParaBigdecimal(total);;
        this.dataCadastro = dataCadastro;
    }

    public Venda(Cliente cliente, FormaPagamento formaPagamento, List<ItemVenda> itens, String total, LocalDateTime dataCadastro) {
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.itens = itens;
        this.total = converterParaBigdecimal(total);
        this.dataCadastro = dataCadastro;
    }

    private static BigDecimal converterParaBigdecimal(String valor) {
        String valorConvertido = valor
                .replace(" ", "")
                .replace("R$", "")
                .replace(".", "")
                .replace(",", ".");
        return new BigDecimal(valorConvertido);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = converterParaBigdecimal(total);
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id) && Objects.equals(cliente, venda.cliente) && formaPagamento == venda.formaPagamento && Objects.equals(itens, venda.itens) && Objects.equals(total, venda.total) && Objects.equals(dataCadastro, venda.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, formaPagamento, itens, total, dataCadastro);
    }

    @Override
    public String toString() {
        return "Venda[" +
                "id=" + id +
                ", cliente=" + cliente +
                ", formaPagamento=" + formaPagamento +
                ", itens=" + itens +
                ", total=" + total +
                ", dataCadastro=" + dataCadastro +
                ']';
    }
}
