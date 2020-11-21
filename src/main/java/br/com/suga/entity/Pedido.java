package br.com.suga.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Pedido.
 */
@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 7010069935712573627L;

    @Id
    @SequenceGenerator(name="seq_pedido", sequenceName="seq_pedido", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido")
    private Integer id;

    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
    private Set<ItemDoPedido> itensPedido;

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
     * Gets data.
     *
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Gets cliente.
     *
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets cliente.
     *
     * @param cliente the cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Gets itens pedido.
     *
     * @return the itens pedido
     */
    public Set<ItemDoPedido> getItensPedido() {
        return itensPedido;
    }

    /**
     * Sets itens pedido.
     *
     * @param itensPedido the itens pedido
     */
    public void setItensPedido(Set<ItemDoPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    /**
     * Instantiates a new Pedido.
     */
    public Pedido() {
        this.itensPedido = new HashSet<>();
    }
}
