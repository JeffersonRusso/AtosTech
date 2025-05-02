package br.com.pi.atostech.adapters.out.storage.repository.video;

import br.com.pi.atostech.adapters.out.storage.entities.video.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {
}
