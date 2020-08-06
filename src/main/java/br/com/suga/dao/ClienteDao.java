package br.com.suga.dao;

import br.com.suga.entity.Cliente;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The type Cliente dao.
 */
@Stateless
public class ClienteDao {

    @PersistenceContext(unitName = "ees20")
    private EntityManager em;

    private static final String SELECT_ALL = "SELECT c FROM Cliente c WHERE 1=1 ";

    /**
     * Incluir.
     *
     * @param cliente the cliente
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void incluir(Cliente cliente) throws Exception {
         em.persist(cliente);
    }

    /**
     * Alterar.
     *
     * @param cliente the cliente
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void alterar(Cliente cliente) throws Exception {
        em.merge(cliente);
    }

    /**
     * Excluir.
     *
     * @param idCliente the id cliente
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void excluir(Integer idCliente) throws Exception {
        Cliente cliente = obterPorId(idCliente);
        em.remove(cliente);
    }

    /**
     * Listar todos list.
     *
     * @return the list
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Cliente> listarTodos() throws Exception {
        return em.createQuery(SELECT_ALL).getResultList();
    }

    /**
     * Obter por id cliente.
     *
     * @param idCliente the id cliente
     * @return the cliente
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Cliente obterPorId(Integer idCliente) throws Exception {
        StringBuilder jpql = new StringBuilder(SELECT_ALL);
        jpql.append(" AND c.id = :id");
        return em.createQuery(jpql.toString(), Cliente.class)
                .setParameter("id", idCliente)
                .getSingleResult();
    }
}
