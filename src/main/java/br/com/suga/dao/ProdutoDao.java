package br.com.suga.dao;

import br.com.suga.entity.Produto;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoDao {

    @PersistenceContext(unitName = "ees20")
    private EntityManager em;

    private static final String SELECT_ALL = "SELECT p FROM Produto p WHERE 1=1 ";

    /**
     * Incluir.
     *
     * @param produto the produto
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void incluir(Produto produto) throws Exception {
        em.persist(produto);
    }

    /**
     * Alterar.
     *
     * @param produto the produto
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void alterar(Produto produto) throws Exception {
        em.merge(produto);
    }

    /**
     * Excluir.
     *
     * @param idProduto the id produto
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void excluir(Integer idProduto) throws Exception {
        Produto produto = obterPorId(idProduto);
        em.remove(produto);
    }

    /**
     * Listar todos list.
     *
     * @return the list
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Produto> listarTodos() throws Exception {
        return em.createQuery(SELECT_ALL).getResultList();
    }

    /**
     * Obter por id produto.
     *
     * @param idProduto the id produto
     * @return the produto
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Produto obterPorId(Integer idProduto) throws Exception {
        StringBuilder jpql = new StringBuilder(SELECT_ALL);
        jpql.append(" AND p.id = :id");
        return em.createQuery(jpql.toString(), Produto.class)
                .setParameter("id", idProduto)
                .getSingleResult();
    }
}
