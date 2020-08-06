package br.com.suga.controller;

import br.com.suga.business.PedidoBusiness;
import br.com.suga.entity.Pedido;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Lista pedido controller.
 */
@ViewScoped
@Named(value = "listaPedidoController")
public class ListaPedidoController implements Serializable {

    private static final long serialVersionUID = -6586773059065412640L;

    private String cpf;

    private List<Pedido> lstPedido;

    @Inject
    private PedidoBusiness business;

    Logger logger = Logger.getLogger(ListaPedidoController.class.getName());

    /**
     * Gets cpf.
     *
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Sets cpf.
     *
     * @param cpf the cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Gets lst pedido.
     *
     * @return the lst pedido
     */
    public List<Pedido> getLstPedido() {
        return lstPedido;
    }

    /**
     * Sets lst pedido.
     *
     * @param lstPedido the lst pedido
     */
    public void setLstPedido(List<Pedido> lstPedido) {
        this.lstPedido = lstPedido;
    }

    /**
     * Pesquisar.
     *
     * @throws Exception the exception
     */
    public void pesquisar() throws Exception {
        lstPedido = business.listarPorCpf(cpf);

        logger.log(Level.INFO, "Qtde pedidos: " + lstPedido.size());
    }

    /**
     * Reset.
     */
    public void reset() {
        cpf = "";
        lstPedido = new ArrayList<>();
    }
}
