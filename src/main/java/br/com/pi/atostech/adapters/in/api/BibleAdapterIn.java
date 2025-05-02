package br.com.pi.atostech.adapters.in.api;

import br.com.pi.atostech.adapters.in.api.dto.mapper.BookDtoMapper;
import br.com.pi.atostech.adapters.in.api.dto.response.VerseResponseDto;
import br.com.pi.atostech.adapters.in.api.dto.response.BookResponseDto;
import br.com.pi.atostech.adapters.out.storage.repository.bible.BibleRepository;
import br.com.pi.atostech.aplication.bible.BibleApplication;
import br.com.pi.atostech.aplication.domain.BookDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class BibleAdapterIn {

    @Autowired
    private BibleApplication bibleApplication;

    private BookDtoMapper bookDtoMapper;

    @Autowired
    private BibleRepository bibleRepository;

    /**
     *
     * Quando for chamado esse endepoint, será assim:
     * www.igrejanovotempo.com./bible/salmos/23/1
     * Caso não passe o verso, por exemplo, ele trás todo o capitulo.
     *
     */

    @GetMapping("/bible/{book}/{chapter}/{verse}")
    public VerseResponseDto getVerse(final String book, final String chapter, final String verse) {
        return null;
    }

    @GetMapping("/getAllBooks")
    @Cacheable()
    //public ResponseEntity<List<BookResponseDto>> getAllBooks() {
    public List<?> getAllBooks() {
        return bibleRepository.findAll();
//        List<BookDomain> allBooks = bibleApplication.getAllBooks();
//        if(allBooks.isEmpty())
//            return ResponseEntity.notFound().build();
//        List<BookResponseDto> dto = BookDtoMapper.toDto(allBooks);
//        return ResponseEntity.ok(dto);
    }

}
