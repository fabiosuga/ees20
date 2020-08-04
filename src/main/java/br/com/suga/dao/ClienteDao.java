package br.com.suga.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClienteDao {

    @PersistenceContext(unitName = "ees20")
    private EntityManager em;

}
