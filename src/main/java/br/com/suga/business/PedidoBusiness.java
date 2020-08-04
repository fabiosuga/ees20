package br.com.suga.business;

import br.com.suga.dao.PedidoDao;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PedidoBusiness {

    @Inject
    private PedidoDao dao;


}
