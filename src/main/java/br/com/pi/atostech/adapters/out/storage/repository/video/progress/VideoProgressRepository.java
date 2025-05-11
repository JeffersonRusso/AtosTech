package br.com.pi.atostech.adapters.out.storage.repository.video.progress;

import br.com.pi.atostech.adapters.out.storage.entities.video.progress.VideoProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoProgressRepository extends JpaRepository<VideoProgressEntity, Integer> {

}
