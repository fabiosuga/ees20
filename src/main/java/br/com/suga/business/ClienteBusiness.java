package br.com.suga.business;

import br.com.suga.dao.ClienteDao;
import br.com.suga.entity.Cliente;
import br.com.suga.util.Util;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

/**
 * The type Cliente business.
 */
@Stateless
public class ClienteBusiness {

    @Inject
    private ClienteDao dao;

    /**
     * Salvar.
     *
     * @param cliente the cliente
     * @throws Exception the exception
     */
    public void salvar(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("Não é possível salvar objeto nulo");
        }

        cliente.setCpf(Util.formatarCpf(cliente.getCpf()));

        if (cliente.getId() == null) {
            incluir(cliente);
        } else {
            alterar(cliente);
        }
    }

    /**
     * Excluir.
     *
     * @param idCliente the id cliente
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void excluir(Integer idCliente) throws Exception {
        if (idCliente == null) {
            throw new Exception("Não é possível excluir objeto nulo");
        }

        dao.excluir(idCliente);
    }

    /**
     * Listar todos.
     *
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Cliente> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void incluir(Cliente cliente) throws Exception {
        if (dao.obterPorCpf(cliente.getCpf()) != null) {
            throw new Exception("Já existe Cliente com o CPF informado");
        }

        dao.incluir(cliente);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void alterar(Cliente cliente) throws Exception {
        dao.alterar(cliente);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Cliente obter(Integer id) throws Exception {
        return dao.obterPorId(id);
    }
}
