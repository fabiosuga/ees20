package br.com.suga.business;

import br.com.suga.dao.PedidoDao;
import br.com.suga.entity.ItemDoPedido;
import br.com.suga.entity.Pedido;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

/**
 * The type Pedido business.
 */
@Stateless
public class PedidoBusiness {

    @Inject
    private PedidoDao dao;

    @Inject
    private ItemDoPedidoBusiness itemDoPedidoBusiness;

    /**
     * Salvar.
     *
     * @param pedido the pedido
     * @throws Exception the exception
     */
    public void salvar(Pedido pedido) throws Exception {
        if (pedido == null) {
            throw new Exception("Não é possível salvar objeto nulo");
        }

        if (pedido.getId() == null) {
            incluir(pedido);
        } else {
            alterar(pedido);
        }
    }

    /**
     * Excluir.
     *
     * @param idPedido the id pedido
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void excluir(Integer idPedido) throws Exception {
        if (idPedido == null) {
            throw new Exception("Não é possível excluir objeto nulo");
        }

        dao.excluir(idPedido);
    }

    /**
     * Listar todos.
     *
     * @return the list
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Pedido> listarTodos() throws Exception {
        return dao.listarTodos();
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
        if (StringUtils.isBlank(cpf)) {
            throw new Exception("Não é possível fazer pesquisa com CPF não preenchido");
        }
        return dao.listarPorCpf(cpf);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    private void incluir(Pedido pedido) throws Exception {
        dao.incluir(pedido);

        for (ItemDoPedido ip : pedido.getItensPedido()) {
            ip.setPedido(pedido);
            itemDoPedidoBusiness.salvar(ip);
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    private void alterar(Pedido pedido) throws Exception {
        dao.alterar(pedido);
    }
}
