package br.com.pi.atostech.adapters.in.api.dto.mapper;

import br.com.pi.atostech.adapters.in.api.dto.response.BookResponseDto;
import br.com.pi.atostech.aplication.domain.BookDomain;

import java.util.List;

public class BookDtoMapper {

    public static List<BookResponseDto> toDto(final List<BookDomain> bookDomain) {
        return bookDomain.stream().map(book ->
                new BookResponseDto(
                    book.getBook(),
                    book.getChapter(),
                    book.getVerse()
                    )).toList();
    }
}
