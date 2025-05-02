package br.com.pi.atostech.aplication.bible;

import br.com.pi.atostech.aplication.domain.BookDomain;

import java.util.List;

public interface BibleApplicationInterface {

    List<BookDomain> getAllBooks();

    String getVerse();

}
