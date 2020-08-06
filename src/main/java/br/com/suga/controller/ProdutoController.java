package br.com.suga.controller;

import br.com.suga.business.ProdutoBusiness;
import br.com.suga.entity.Produto;
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
 * The type Produto controller.
 */
@ViewScoped
@Named(value = "produtoController")
public class ProdutoController implements Serializable {

    private static final long serialVersionUID = -7107432002195023746L;

    @Inject
    private Produto produtoSelected;

    @Inject
    private ProdutoBusiness business;

    private Logger logger = Logger.getLogger(ProdutoController.class.getName());

    private List<Produto> lstProduto;

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
     * Gets lst produto.
     *
     * @return the lst produto
     */
    public List<Produto> getLstProduto() {
        return lstProduto;
    }

    /**
     * Sets lst produto.
     *
     * @param lstProduto the lst produto
     */
    public void setLstProduto(List<Produto> lstProduto) {
        this.lstProduto = lstProduto;
    }

    /**
     * Salvar.
     *
     * @throws Exception the exception
     */
    public void salvar() throws Exception {
        business.salvar(produtoSelected);
        reset();
        lstProduto = business.listarTodos();
    }

    /**
     * Excluir.
     *
     * @throws Exception the exception
     */
    public void excluir() throws Exception {
        business.excluir(produtoSelected.getId());
        lstProduto = business.listarTodos();
    }

    /**
     * Iniciar.
     *
     * @throws Exception the exception
     */
    @PostConstruct
    public void iniciar() {
        lstProduto = new ArrayList<>();

        try {
            lstProduto = business.listarTodos();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao montar lista de Produtos", e);
        }
    }

    /**
     * Reset.
     */
    public void reset() {
        produtoSelected = new Produto();
    }

}
