package br.com.suga.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The type Item do pedido.
 */
@Entity
@Table(name = "tb_item_do_pedido")
public class ItemDoPedido implements Serializable {
    private static final long serialVersionUID = 4116541548048358964L;

    @Id
    @SequenceGenerator(name="seq_item_pedido", sequenceName="seq_item_pedido")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_pedido")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Pedido deve ser selecionado")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produto", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Produto deve ser selecionado")
    private Produto produto;

    @Column(name = "quantidade")
    @Min(value = 1, message = "Quantidade deve ser igual ou maior a 1")
    private Integer quantidade;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets pedido.
     *
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Sets pedido.
     *
     * @param pedido the pedido
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Gets produto.
     *
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Sets produto.
     *
     * @param produto the produto
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * Gets quantidade.
     *
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Sets quantidade.
     *
     * @param quantidade the quantidade
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
