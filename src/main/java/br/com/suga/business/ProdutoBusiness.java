package br.com.suga.business;

import br.com.suga.dao.ProdutoDao;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ProdutoBusiness {

    @Inject
    private ProdutoDao dao;

}
