package br.com.rasmoo.restaurante.util;

import br.com.rasmoo.restaurante.dao.*;
import br.com.rasmoo.restaurante.entity.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CargaDeDadosUtil {

    private CargaDeDadosUtil(){}

    public static void cadastarCategorias(EntityManager entityManager) {
        Categoria entrada = new Categoria("Entradas");
        Categoria salada = new Categoria("Saladas");
        Categoria principal = new Categoria("Pratos Principais");

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        categoriaDao.cadastrar(entrada);
        entityManager.flush();
        categoriaDao.cadastrar(salada);
        entityManager.flush();
        categoriaDao.cadastrar(principal);
        entityManager.flush();
        entityManager.clear();
    }

    public static void cadastrarProdutosCardapio(EntityManager entityManager) {
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        CardapioDao cardapioDao = new CardapioDao(entityManager);

        List<Categoria> categorias = categoriaDao.consultarTodos();
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
                true, BigDecimal.valueOf(59.00), categorias.get(1));

        cardapioDao.cadastrar(moqueca);
        cardapioDao.cadastrar(spaguetti);
        cardapioDao.cadastrar(bife);
        cardapioDao.cadastrar(cordeiro);
        cardapioDao.cadastrar(burrata);
        cardapioDao.cadastrar(bruschetta);
        cardapioDao.cadastrar(scappeta);
        cardapioDao.cadastrar(caprese);
        cardapioDao.cadastrar(caesar);
        cardapioDao.cadastrar(chevre);
        entityManager.flush();
        entityManager.clear();
    }

    public static void cadastrarClientes(EntityManager entityManager){

        ClienteDao clienteDao = new ClienteDao(entityManager);
        EnderecoDao enderecoDao = new EnderecoDao(entityManager);

        Endereco augusta = new Endereco("000000000","augusta","casa 43","Sao Paulo","SP");
        Cliente felipe = new Cliente("12345678901","feilpe@email.com","Felipe Ribeiro");
        felipe.addEndereco(augusta);

        Endereco rioVermelho = new Endereco("000000001","Lapa","apto 1001","Salvador","BA");
        Cliente cleber = new Cliente("111111111111","cleber@email.com","Cleber Machado");
        cleber.addEndereco(rioVermelho);

        Endereco leblon = new Endereco("000000002","Lapa","apto 203","Rio de Janeiro","RJ");
        Cliente calvin = new Cliente("09876543210","calvin@email.com","Calvin Coelho");
        calvin.addEndereco(leblon);

        Endereco heitorPenteado = new Endereco("000000000","Heitor Penteado","apto 101","Santos","SP");
        Cliente tayane = new Cliente("111111111123","tayane@email.com","Tayane Lopes Costa");
        tayane.addEndereco(heitorPenteado);

        Endereco consolacao = new Endereco("000000000","Lapa","apto 1001","Sao Paulo","SP");
        Cliente denise = new Cliente("111111111145","denise@email.com","Denise Costa");
        denise.addEndereco(consolacao);

        Endereco nacoesUnidas = new Endereco("000000000","NacoesUnidas","casa 27","Sao Paulo","SP");
        Cliente claudia = new Cliente("111111111345","claudia@email.com","Claudia Rosa");
        claudia.addEndereco(nacoesUnidas);

        enderecoDao.cadastrar(augusta);
        clienteDao.cadastrar(felipe);
        enderecoDao.cadastrar(rioVermelho);
        clienteDao.cadastrar(cleber);
        enderecoDao.cadastrar(leblon);
        clienteDao.cadastrar(calvin);
        enderecoDao.cadastrar(heitorPenteado);
        clienteDao.cadastrar(tayane);
        enderecoDao.cadastrar(consolacao);
        clienteDao.cadastrar(denise);
        enderecoDao.cadastrar(nacoesUnidas);
        clienteDao.cadastrar(claudia);

        entityManager.flush();
        entityManager.clear();
    }

    public static void cadastrarOrdensClientes(EntityManager entityManager){
        ClienteDao clienteDao = new ClienteDao(entityManager);
        CardapioDao cardapio = new CardapioDao(entityManager);
        OrdemDao ordemDao = new OrdemDao(entityManager);
        List<Cliente> clientes = clienteDao.consultarTodos();
        List<Cardapio> cardapioList = cardapio.consultarTodos();

        Ordem ordemFelipe = new Ordem(clientes.get(0));
        ordemFelipe.addOrdensCardapio(new OrdensCardapio(cardapioList.get(0),2));
        ordemFelipe.addOrdensCardapio(new OrdensCardapio(cardapioList.get(5),3));

        Ordem ordemCleber = new Ordem(clientes.get(1));
        ordemCleber.addOrdensCardapio(new OrdensCardapio(cardapioList.get(0),1));
        ordemCleber.addOrdensCardapio(new OrdensCardapio(cardapioList.get(1),2));
        ordemCleber.addOrdensCardapio(new OrdensCardapio(cardapioList.get(6),3));

        Ordem ordemCalvin = new Ordem(clientes.get(2));
        ordemCalvin.addOrdensCardapio(new OrdensCardapio(cardapioList.get(2),2));
        ordemCalvin.addOrdensCardapio(new OrdensCardapio(cardapioList.get(9),3));

        Ordem ordemTayane = new Ordem(clientes.get(3));
        ordemTayane.addOrdensCardapio(new OrdensCardapio(cardapioList.get(0),2));
        ordemTayane.addOrdensCardapio(new OrdensCardapio(cardapioList.get(2),3));

        Ordem ordemDenise = new Ordem(clientes.get(4));
        ordemDenise.addOrdensCardapio(new OrdensCardapio(cardapioList.get(4),2));
        ordemDenise.addOrdensCardapio(new OrdensCardapio(cardapioList.get(3),1));

        Ordem ordemClaudia = new Ordem(clientes.get(5));
        ordemClaudia.addOrdensCardapio(new OrdensCardapio(cardapioList.get(3),2));
        ordemClaudia.addOrdensCardapio(new OrdensCardapio(cardapioList.get(5),3));

        ordemDao.cadastrar(ordemFelipe);
        ordemDao.cadastrar(ordemCleber);
        ordemDao.cadastrar(ordemCalvin);
        ordemDao.cadastrar(ordemTayane);
        ordemDao.cadastrar(ordemDenise);
        ordemDao.cadastrar(ordemClaudia);

        entityManager.flush();
        entityManager.clear();

    }
}
