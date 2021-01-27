package br.com.suga.dao;

import br.com.suga.entity.Produto;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        String jpql = SELECT_ALL + " ORDER BY p.descricao";
        return em.createQuery(jpql).getResultList();
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

    public Boolean exists(Integer id, String descricao) {
        StringBuilder jpql = new StringBuilder("SELECT id <> 0 FROM Produto p WHERE UPPER(p.descricao) = UPPER(:descricao) ");
        if (id != null) {
            jpql.append(" AND p.id <> :id");
        }

        Boolean retorno = Boolean.FALSE;

        Query query = em.createQuery(jpql.toString());

        if (id != null) {
            query.setParameter("id", id);
        }

        if (StringUtils.isNotBlank(descricao)) {
            query.setParameter("descricao", descricao);
        }

        try {
            retorno = (Boolean) query.getSingleResult();
        } catch (NoResultException nre) {
            retorno = Boolean.FALSE;
        }

        return retorno;
    }
}
