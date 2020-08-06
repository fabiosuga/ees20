package br.com.suga.controller;

import br.com.suga.business.ClienteBusiness;
import br.com.suga.entity.Cliente;
import javax.annotation.PostConstruct;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Cliente controller.
 */
@ViewScoped
@Named(value = "clienteController")
public class ClienteController implements Serializable {

    private static final long serialVersionUID = -4709613398567738685L;

    @Inject
    private Cliente clienteSelected;

    @Inject
    private ClienteBusiness business;

    private List<Cliente> lstCliente;

    private Logger logger = Logger.getLogger(ClienteController.class.getName());

    /**
     * Gets cliente selected.
     *
     * @return the cliente selected
     */
    public Cliente getClienteSelected() {
        return clienteSelected;
    }

    /**
     * Sets cliente selected.
     *
     * @param clienteSelected the cliente selected
     */
    public void setClienteSelected(Cliente clienteSelected) {
        this.clienteSelected = clienteSelected;
    }

    /**
     * Gets lst cliente.
     *
     * @return the lst cliente
     */
    public List<Cliente> getLstCliente() {
        return lstCliente;
    }

    /**
     * Sets lst cliente.
     *
     * @param lstCliente the lst cliente
     */
    public void setLstCliente(List<Cliente> lstCliente) {
        this.lstCliente = lstCliente;
    }

    /**
     * Salvar.
     *
     * @throws Exception the exception
     */
    public void salvar() throws Exception {
        business.salvar(clienteSelected);
        reset();
        lstCliente = business.listarTodos();
    }

    /**
     * Excluir.
     *
     * @throws Exception the exception
     */
    public void excluir() throws Exception {
        business.excluir(clienteSelected.getId());
        lstCliente = business.listarTodos();
    }


    /**
     * Inicio.
     *
     * @throws Exception the exception
     */
    @PostConstruct
    public void inicio() {
        lstCliente = new ArrayList<>();
        try {
            lstCliente = business.listarTodos();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao montar lista de Clientes", e);
        }
    }

    /**
     * Reset.
     */
    public void reset() {
        clienteSelected = new Cliente();
    }

}
