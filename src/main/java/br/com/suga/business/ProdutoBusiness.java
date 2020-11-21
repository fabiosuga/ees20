package br.com.suga.business;

import br.com.suga.dao.ProdutoDao;
import br.com.suga.entity.Produto;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

/**
 * The type Produto business.
 */
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
     * @return the list
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Produto> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    /**
     * Incluir.
     *
     * @param produto the produto
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void incluir(Produto produto) throws Exception {
        validarIncluirAlterar(produto);

        dao.incluir(produto);
    }

    /**
     * Alterar.
     *
     * @param produto the produto
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void alterar(Produto produto) throws Exception {
        validarIncluirAlterar(produto);

        dao.alterar(produto);
    }

    /**
     * Validacao para inclusao/alteracao de produto
     * - nao pode ser nulo
     * - campo descricao deve estar preenchido
     * - nao pode ter o mesmo nome
     * @param produto thr produto
     * @throws Exception
     */
    private void validarIncluirAlterar(Produto produto) throws Exception {
        if (produto == null) {
            throw new Exception("Não é possível salvar objeto nulo");
        }

//        if (StringUtils.isBlank(produto.getDescricao())) {
//            throw new Exception("Campo descrição não preenchido");
//        }

        if (produto.getId() != null) {
            // nao pode ter produto com mesmo nome

        }

        if (dao.exists(produto.getId(), produto.getDescricao())) {

        }
    }

    public Produto obter(Integer id) throws Exception {
        if (id == null) {
            return null;
        }

        return dao.obterPorId(id);
    }
}
