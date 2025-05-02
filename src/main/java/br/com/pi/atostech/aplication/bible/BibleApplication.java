package br.com.pi.atostech.aplication.bible;

import br.com.pi.atostech.aplication.domain.BookDomain;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * BibleApplication
 * @author Jefferson Russo
 * @since 2025-04-22

 * Classe que responsavel por trabalhar com as regras de negocio.
 * Ex: Ligar com retorno nulo (vazio) do banco de dados, ou das APIs
 * Retornar só uma parte do conteudo do banco de dados ou das APIs...
 *
 */

@Component
public class BibleApplication {

    public List<BookDomain> getAllBooks() {
        return null;
    }

}
