package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Categoria;
import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.vo.ClienteVo;

import javax.persistence.EntityManager;
import java.util.List;

public class EnderecoDao {

    private EntityManager entityManager;

    public EnderecoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(final Endereco endereco) {
        this.entityManager.persist(endereco);
    }

    public Categoria consultarPorId(final Integer id) {
        return this.entityManager.find(Categoria.class, id);
    }

    public List<Endereco> consultarTodos() {
        String jpql = "SELECT e FROM Endereco e";
        return this.entityManager.createQuery(jpql,Endereco.class).getResultList();
    }

    public List<ClienteVo> consultarClientes(final String estado, final String cidade, final String rua) {
        String jpql = "SELECT new br.com.rasmoo.restaurante.vo.ClienteVo(e.cliente.cpf,e.cliente.nome) " +
                "FROM Endereco e WHERE UPPER(e.estado) = UPPER(:estado) AND " +
                "UPPER(e.cidade) = UPPER(:cidade) AND " +
                "UPPER(e.rua) = UPPER(:rua)";
        return this.entityManager.createQuery(jpql,ClienteVo.class).setParameter("estado",estado)
                .setParameter("cidade",cidade).setParameter("rua",rua).getResultList();
    }

    public void atualizar(final Endereco endereco){
        this.entityManager.merge(endereco);
    }

    public void excluir(final Endereco endereco){
        this.entityManager.remove(endereco);
    }
}
