package com.xadrez.carlos.xadrez_variante.variantes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XadrezRepository extends JpaRepository<Xadrez, Long>{
    List<Xadrez> findAllByAtivoTrue();
}
