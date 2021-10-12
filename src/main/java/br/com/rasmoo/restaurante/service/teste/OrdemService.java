package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.EnderecoDao;
import br.com.rasmoo.restaurante.entity.ClienteId;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;

public class OrdemService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
        entityManager.getTransaction().begin();
        CargaDeDadosUtil.cadastarCategorias(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
        CargaDeDadosUtil.cadastrarClientes(entityManager);
        CargaDeDadosUtil.cadastrarOrdensClientes(entityManager);
        EnderecoDao enderecoDao = new EnderecoDao(entityManager);
        System.out.println(enderecoDao.consultarClientes(null,null,"lapa"));
        System.out.println(enderecoDao.consultarClientesUsandoCriteria(null,null,"lapa"));

        ClienteDao clienteDao = new ClienteDao(entityManager);
        System.out.println(clienteDao.consultarPorNome("Maria"));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
