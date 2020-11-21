package br.com.suga.dao;

import br.com.suga.entity.Pedido;
import br.com.suga.util.Util;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The type Pedido dao.
 */
@Stateless
public class PedidoDao {

    @PersistenceContext(unitName = "ees20")
    private EntityManager em;

    private static final String SELECT_ALL = "SELECT p FROM Pedido p WHERE 1=1 ";
    private static final String SELECT_POR_CPF = "SELECT distinct p FROM Pedido p " +
            "LEFT JOIN FETCH p.cliente c " +
            "LEFT JOIN FETCH p.itensPedido itens " +
            "LEFT JOIN FETCH itens.produto prod " +
            "WHERE 1=1 and c.cpf = :cpf " +
            "ORDER BY p.id";
    private static final String SELECT_POR_ID = "SELECT distinct p FROM Pedido p " +
            "LEFT JOIN FETCH p.cliente c " +
            "LEFT JOIN FETCH p.itensPedido itens " +
            "LEFT JOIN FETCH itens.produto prod " +
            "WHERE 1=1 and c.id = :id " +
            "ORDER BY p.id";

    /**
     * Incluir.
     *
     * @param pedido the pedido
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void incluir(Pedido pedido) throws Exception {
        em.persist(pedido);
    }

    /**
     * Alterar.
     *
     * @param pedido the pedido
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void alterar(Pedido pedido) throws Exception {
        em.merge(pedido);
    }

    /**
     * Excluir.
     *
     * @param idPedido the id pedido
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void excluir(Integer idPedido) throws Exception {
        Pedido pedido = obterPorId(idPedido);
        em.remove(pedido);
    }

    /**
     * Listar todos list.
     *
     * @return the list
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Pedido> listarTodos() throws Exception {
        return em.createQuery(SELECT_ALL).getResultList();
    }

    /**
     * Obter por id pedido.
     *
     * @param idPedido the id pedido
     * @return the pedido
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Pedido obterPorId(Integer idPedido) throws Exception {
        StringBuilder jpql = new StringBuilder(SELECT_POR_ID);
        return em.createQuery(jpql.toString(), Pedido.class)
                .setParameter("id", idPedido)
                .getSingleResult();
    }

    /**
     * Listar por cpf list.
     *
     * @param cpf the cpf
     * @return the list
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Pedido> listarPorCpf(String cpf) throws  Exception {
        StringBuilder jpql = new StringBuilder(SELECT_POR_CPF);
        return em.createQuery(jpql.toString(), Pedido.class)
                .setParameter("cpf", Util.formatarCpf(cpf))
                .getResultList();
    }
}
