package br.com.suga.dao;

import br.com.suga.entity.Cliente;
import br.com.suga.util.Util;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The type Cliente dao.
 */
@Stateless
public class ClienteDao {

    @PersistenceContext(unitName = "ees20")
    private EntityManager em;

    private static final String SELECT_ALL = "SELECT DISTINCT c FROM Cliente c LEFT JOIN FETCH c.pedidos WHERE 1=1 ";

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
        String jpql = SELECT_ALL + " ORDER BY c.nome";
        return em.createQuery(jpql).getResultList();
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

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Cliente obterPorCpf(String numeroCpf) throws Exception {
        Cliente cliente = null;
        String cpf = Util.formatarCpf(numeroCpf);
        StringBuilder jpql = new StringBuilder(SELECT_ALL);
        jpql.append(" AND c.cpf = :cpf");
        try {
            cliente = em.createQuery(jpql.toString(), Cliente.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        } catch (NoResultException nre) {
            cliente = null;
        } catch (Exception e) {
            throw new Exception("Erro ao procurar cliente por cpf. " + e);
        }

        return cliente;
    }
}
