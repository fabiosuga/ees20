package br.com.suga.controller;

import br.com.suga.business.ClienteBusiness;
import br.com.suga.business.PedidoBusiness;
import br.com.suga.business.ProdutoBusiness;
import br.com.suga.entity.Cliente;
import br.com.suga.entity.ItemDoPedido;
import br.com.suga.entity.Pedido;
import br.com.suga.entity.Produto;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.omnifaces.cdi.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Pedido controller.
 */
@ViewScoped
@Named(value = "pedidoController")
public class PedidoController implements Serializable {

    private static final long serialVersionUID = -1047992547257809328L;

    @Inject
    private Pedido pedidoSelected;

    @Inject
    private PedidoBusiness business;

    @Inject
    private ClienteBusiness clienteBusiness;

    @Inject
    private ProdutoBusiness produtoBusiness;

    private List<Cliente> lstClientes;
    private List<Produto> lstProdutos;

    private List<Produto> lstProdutosSelected;

    private List<ItemDoPedido> lstItensPedido;

    @Inject
    private Produto produtoSelected;
    private Integer quantidadeProduto;

    private Logger logger = Logger.getLogger(PedidoController.class.getName());

    /**
     * Gets pedido selected.
     *
     * @return the pedido selected
     */
    public Pedido getPedidoSelected() {
        return pedidoSelected;
    }

    /**
     * Sets pedido selected.
     *
     * @param pedidoSelected the pedido selected
     */
    public void setPedidoSelected(Pedido pedidoSelected) {
        this.pedidoSelected = pedidoSelected;
    }

    /**
     * Gets lst clientes.
     *
     * @return the lst clientes
     */
    public List<Cliente> getLstClientes() {
        return lstClientes;
    }

    /**
     * Gets lst produtos.
     *
     * @return the lst produtos
     */
    public List<Produto> getLstProdutos() {
        return lstProdutos;
    }

    /**
     * Gets lst produtos selected.
     *
     * @return the lst produtos selected
     */
    public List<Produto> getLstProdutosSelected() {
        return lstProdutosSelected;
    }

    /**
     * Sets lst produtos selected.
     *
     * @param lstProdutosSelected the lst produtos selected
     */
    public void setLstProdutosSelected(List<Produto> lstProdutosSelected) {
        this.lstProdutosSelected = lstProdutosSelected;
    }

    /**
     * Gets produto selected.
     *
     * @return the produto selected
     */
    public Produto getProdutoSelected() {
        return produtoSelected;
    }

    /**
     * Sets produto selected.
     *
     * @param produtoSelected the produto selected
     */
    public void setProdutoSelected(Produto produtoSelected) {
        this.produtoSelected = produtoSelected;
    }

    /**
     * Gets quantidade produto.
     *
     * @return the quantidade produto
     */
    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }

    /**
     * Sets quantidade produto.
     *
     * @param quantidadeProduto the quantidade produto
     */
    public void setQuantidadeProduto(Integer quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    /**
     * Salvar.
     *
     * @throws Exception the exception
     */
    public void salvar() throws Exception {
        business.salvar(pedidoSelected);
        reset();
    }

    /**
     * Reset.
     */
    public void reset() {
        pedidoSelected = new Pedido();
        lstProdutosSelected = new ArrayList<>();
        produtoSelected = new Produto();
        quantidadeProduto = 0;
    }

    /**
     * Iniciar.
     */
    @PostConstruct
    public void iniciar()
    {
        try {
            lstProdutos = produtoBusiness.listarTodos();
            lstClientes = clienteBusiness.listarTodos();
            reset();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao popular listas de produto e/ou clientes para interface de Pedidos", e);
        }
    }


    public void addProdutoQtde() {
        if (produtoSelected != null && quantidadeProduto > 0){
            boolean adicionaQtde = Boolean.FALSE;

            for (ItemDoPedido itemDoPedido : pedidoSelected.getItensPedido()) {
                if (itemDoPedido.getProduto().getId() == produtoSelected.getId()) {
                    adicionaQtde = Boolean.TRUE;
                }
            }

            if (adicionaQtde) {
                pedidoSelected.getItensPedido().forEach(itemDoPedido -> {
                    if (itemDoPedido.getProduto().getId() == produtoSelected.getId()) {
                        itemDoPedido.setQuantidade(itemDoPedido.getQuantidade() + quantidadeProduto);
                    }
                });
            } else {
                ItemDoPedido ip = new ItemDoPedido();
                ip.setProduto(produtoSelected);
                ip.setQuantidade(quantidadeProduto);
                ip.setPedido(pedidoSelected);
                pedidoSelected.getItensPedido().add(ip);
            }

            produtoSelected = new Produto();
            quantidadeProduto = 0;
        }
    }
}
