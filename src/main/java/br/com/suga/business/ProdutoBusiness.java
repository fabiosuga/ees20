package br.com.suga.business;

import br.com.suga.dao.ProdutoDao;
import br.com.suga.entity.Produto;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProdutoBusiness {

    @Inject
    private ProdutoDao dao;

    /**
     * Salvar.
     *
     * @param produto the produto
     * @throws Exception the exception
     */
    public void salvar(Produto produto) throws Exception {
        if (produto == null) {
            throw new Exception("Não é possível salvar objeto nulo");
        }

        if (StringUtils.isBlank(produto.getDescricao())) {
            throw new Exception("Campo descrição não preenchido");
        }

        if (produto.getId() == null) {
            incluir(produto);
        } else {
            alterar(produto);
        }
    }

    /**
     * Excluir.
     *
     * @param idProduto the id produto
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void excluir(Integer idProduto) throws Exception {
        if (idProduto == null) {
            throw new Exception("Não é possível excluir objeto nulo");
        }

        dao.excluir(idProduto);
    }

    /**
     * Listar todos.
     *
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Produto> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    private void incluir(Produto produto) throws Exception {
        dao.incluir(produto);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    private void alterar(Produto produto) throws Exception {
        dao.alterar(produto);
    }
}
