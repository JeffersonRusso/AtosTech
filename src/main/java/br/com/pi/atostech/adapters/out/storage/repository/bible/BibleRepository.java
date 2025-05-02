package br.com.pi.atostech.adapters.out.storage.repository.bible;

import br.com.pi.atostech.adapters.out.storage.entities.bible.BookEntity;
import br.com.pi.atostech.adapters.out.storage.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.UUID;

@Repository
public interface BibleRepository extends JpaRepository<BookEntity, Integer> {

}
