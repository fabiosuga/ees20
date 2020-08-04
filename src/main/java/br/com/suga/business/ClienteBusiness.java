package br.com.suga.business;

import br.com.suga.dao.ClienteDao;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ClienteBusiness {

    @Inject
    private ClienteDao dao;
}
