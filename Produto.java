package cadastroee.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    // conforme instrução: usar Float em vez de BigDecimal
    @Column(name = "preco_venda")
    private Float precoVenda;

    public Produto() {}

    public Produto(Integer id) { this.id = id; }

    public Produto(Integer id, String nome, Float precoVenda) {
        this.id = id;
        this.nome = nome;
        this.precoVenda = precoVenda;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Float getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(Float precoVenda) { this.precoVenda = precoVenda; }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", precoVenda=" + precoVenda + '}';
    }
}

