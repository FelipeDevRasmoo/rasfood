package br.com.rasmoo.restaurante.util;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CargaDeDadosUtil {

    private EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
    private CategoriaDao categoriaDao = new CategoriaDao(entityManager);
    private CardapioDao cardapioDao = new CardapioDao(entityManager);

    public void cadastarCategoria() {
        Categoria entrada = new Categoria("Entradas");
        Categoria salada = new Categoria("Saladas");
        Categoria principal = new Categoria("Pratos Principais");

        entityManager.getTransaction().begin();
        categoriaDao.cadastrar(entrada);
        categoriaDao.cadastrar(salada);
        categoriaDao.cadastrar(principal);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    public void cadastrarProdutoCardapio() {

        Cardapio cardapio = new Cardapio("Moqueca", "Peixe branco, banana da terra, arroz e farofa",
                true, BigDecimal.valueOf(65.00), categoriaDao.consultarPorId(3));
    }
}
