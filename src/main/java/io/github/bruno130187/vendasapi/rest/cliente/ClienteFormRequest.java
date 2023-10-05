package io.github.bruno130187.vendasapi.rest.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.github.bruno130187.vendasapi.model.Cliente;

import java.time.LocalDate;

public class ClienteFormRequest {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate nascimento;
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    public ClienteFormRequest() {
    }

    public ClienteFormRequest(LocalDate nascimento, String cpf, String nome, String endereco, String telefone, String email, LocalDate dataCadastro) {
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }

    public ClienteFormRequest(Long id, LocalDate nascimento, String cpf, String nome, String endereco, String telefone, String email, LocalDate dataCadastro) {
        this.id = id;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }

    public Cliente toModel() {
        return new Cliente(id, nascimento, cpf, nome, endereco, telefone, email, dataCadastro);
    }

    public static ClienteFormRequest fromModel(Cliente cliente) {
        return new ClienteFormRequest(
                cliente.getId(),
                cliente.getNascimento(),
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getEndereco(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getDataCadastro()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "ClienteFormRequest[" +
                "id=" + id +
                ", nascimento=" + nascimento +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", dataCadastro=" + dataCadastro +
                ']';
    }
}
