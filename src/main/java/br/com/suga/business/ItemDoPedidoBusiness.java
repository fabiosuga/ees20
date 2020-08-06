package br.com.suga.business;

import br.com.suga.entity.ItemDoPedido;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * The type Item do pedido business.
 */
@Stateless
public class ItemDoPedidoBusiness implements Serializable {

    private static final long serialVersionUID = 6278957068372851885L;

    @PersistenceContext(unitName = "ees20")
    private EntityManager em;

    /**
     * Salvar.
     *
     * @param itemDoPedido the item do pedido
     * @throws Exception the exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void salvar(ItemDoPedido itemDoPedido) throws Exception {
        if (itemDoPedido == null) {
            throw new Exception("Não é possível salvar objeto nulo");
        }

        em.persist(itemDoPedido);
    }
}
