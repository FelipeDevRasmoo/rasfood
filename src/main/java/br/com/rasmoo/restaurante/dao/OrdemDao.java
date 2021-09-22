package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.vo.ItensPrincipaisVo;

import javax.persistence.EntityManager;
import java.util.List;

public class OrdemDao {

    private EntityManager entityManager;

    public OrdemDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(final Ordem ordem) {
        this.entityManager.persist(ordem);
    }

    public Ordem consultarPorId(final Integer id) {
        return this.entityManager.find(Ordem.class, id);
    }

    public List<Ordem> consultarTodos() {
        String jpql = "SELECT o FROM Ordem o";
        return this.entityManager.createQuery(jpql,Ordem.class).getResultList();
    }

    public List<ItensPrincipaisVo> consultarItensMaisVendidos(){
        String jpql = "SELECT new br.com.rasmoo.restaurante.vo.ItensPrincipaisVo(" +
                "c.nome, SUM(oc.quantidade)) FROM Ordem o " +
                "JOIN OrdensCardapio oc on o.id = oc.cardapio.id " +
                "JOIN oc.cardapio c " +
                "GROUP BY c.nome " +
                "ORDER BY SUM(oc.quantidade) DESC";
        return this.entityManager.createQuery(jpql,ItensPrincipaisVo.class).getResultList();
    }

    public Ordem joinFetchCliente(final Integer id) {
        String jpql = "SELECT o FROM Ordem o JOIN FETCH o.cliente WHERE o.id = :id";
        return this.entityManager.createQuery(jpql,Ordem.class).setParameter("id",id).getSingleResult();
    }

    public void atualizar(final Ordem ordem){
        this.entityManager.merge(ordem);
    }

    public void excluir(final Ordem ordem){
        this.entityManager.remove(ordem);
    }
}
