package br.com.suga.dao;

import br.com.suga.entity.ItemDoPedido;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Stateless
public class ItemDoPedidoDao implements Serializable {

    private static final long serialVersionUID = -9094138601182998518L;

    @PersistenceContext(unitName = "ees20")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void incluir(ItemDoPedido itemDoPedido) throws Exception {
        em.persist(itemDoPedido);
    }
}
