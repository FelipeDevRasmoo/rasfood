package br.com.rasmoo.restaurante.util;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CargaDeDadosUtil {

    private static final EntityManager ENTITY_MANAGER = JPAUtil.getEntityManagerRasFood();
    private static final CategoriaDao CATEGORIA_DAO = new CategoriaDao(ENTITY_MANAGER);
    private static final CardapioDao CARDAPIO_DAO = new CardapioDao(ENTITY_MANAGER);

    private CargaDeDadosUtil(){}

    public static void cadastarCategorias() {
        Categoria entrada = new Categoria("Entradas");
        Categoria salada = new Categoria("Saladas");
        Categoria principal = new Categoria("Pratos Principais");

        ENTITY_MANAGER.getTransaction().begin();
        CATEGORIA_DAO.cadastrar(entrada);
        ENTITY_MANAGER.flush();
        CATEGORIA_DAO.cadastrar(salada);
        ENTITY_MANAGER.flush();
        CATEGORIA_DAO.cadastrar(principal);
        ENTITY_MANAGER.flush();
        ENTITY_MANAGER.clear();
    }

    public static void cadastrarProdutosCardapio() {
        List<Categoria> categorias = CATEGORIA_DAO.consultarTodos();
        Cardapio moqueca = new Cardapio("Moqueca", "Peixe branco, banana da terra, arroz e farofa",
                true, BigDecimal.valueOf(95.00), categorias.get(2));
        Cardapio spaguetti = new Cardapio("Spaguetti", "Spagatti ao molho de parmesão e cogumelos",
                true, BigDecimal.valueOf(68.00), categorias.get(2));
        Cardapio bife = new Cardapio("Bife", "Bife acebolado com arroz branco, farofa e batata frita",
                true, BigDecimal.valueOf(59.00), categorias.get(2));
        Cardapio cordeiro = new Cardapio("Cordeiro", "Cordeiro com risoto de funghi",
                true, BigDecimal.valueOf(88.00), categorias.get(2));
        Cardapio burrata = new Cardapio("Burrata", "Tomates queimados, rúcula e torrada",
                true, BigDecimal.valueOf(15.00), categorias.get(0));
        Cardapio bruschetta = new Cardapio("Bruschetta", "Tomate, mucarela e manjericao",
                true, BigDecimal.valueOf(20.00), categorias.get(0));
        Cardapio scappeta = new Cardapio("Scappeta", "Ragu de linguica e torradinhas",
                true, BigDecimal.valueOf(25.00), categorias.get(0));
        Cardapio caprese = new Cardapio("Caprese", "Mini rucula e mucarela",
                true, BigDecimal.valueOf(47.00), categorias.get(1));
        Cardapio caesar = new Cardapio("Caesar", "Salada de franco com molho ceasar",
                true, BigDecimal.valueOf(40.00), categorias.get(1));
        Cardapio chevre = new Cardapio("Chevre", "Mix de folhas, mostarda e mel",
                true, BigDecimal.valueOf(50.00), categorias.get(1));

        CARDAPIO_DAO.cadastrar(moqueca);
        CARDAPIO_DAO.cadastrar(spaguetti);
        CARDAPIO_DAO.cadastrar(bife);
        CARDAPIO_DAO.cadastrar(cordeiro);
        CARDAPIO_DAO.cadastrar(burrata);
        CARDAPIO_DAO.cadastrar(bruschetta);
        CARDAPIO_DAO.cadastrar(scappeta);
        CARDAPIO_DAO.cadastrar(caprese);
        CARDAPIO_DAO.cadastrar(caesar);
        CARDAPIO_DAO.cadastrar(chevre);
        ENTITY_MANAGER.getTransaction().commit();
        ENTITY_MANAGER.close();
    }
}
