package com.heroes.TourofHeroes.repository;
import com.heroes.TourofHeroes.model.Heroes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroesRepository extends JpaRepository<Heroes, Long> {
	List<Heroes> findByNameContaining(String name);
}
