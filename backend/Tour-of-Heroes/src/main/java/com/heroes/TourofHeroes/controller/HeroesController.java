package com.heroes.TourofHeroes.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heroes.TourofHeroes.model.Heroes;
import com.heroes.TourofHeroes.repository.HeroesRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class HeroesController {
	
	@Autowired
	HeroesRepository heroesRepository;

	@GetMapping("/heroes")
	public ResponseEntity<List<Heroes>> getAllHeroes(@RequestParam(required = false) String name) {
		try {
			List<Heroes> heroes = new ArrayList<Heroes>();

			if (name == null)
				heroesRepository.findAll().forEach(heroes::add);
			else
				heroesRepository.findByNameContaining(name).forEach(heroes::add);

			if (heroes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(heroes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/heroes/{id}")
	public ResponseEntity<Heroes> getHeroById(@PathVariable("id") long id) {
		Optional<Heroes> heroesData = heroesRepository.findById(id);

		if (heroesData.isPresent()) {
			return new ResponseEntity<>(heroesData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/heroes")
	public ResponseEntity<Heroes> createHero(@RequestBody Heroes hero) {
		try {
			Heroes _hero = heroesRepository
					.save(new Heroes(hero.getName(), hero.getStrength()));
			return new ResponseEntity<>(_hero, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/heroes/{id}")
	public ResponseEntity<Heroes> updateHero(@PathVariable("id") long id, @RequestBody Heroes hero) {
		Optional<Heroes> heroesData = heroesRepository.findById(id);

		if (heroesData.isPresent()) {
			Heroes _heroes = heroesData.get();
			_heroes.setName(hero.getName());
			return new ResponseEntity<>(heroesRepository.save(_heroes), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/heroes/{id}")
	public ResponseEntity<HttpStatus> deleteHero(@PathVariable("id") long id) {
		try {
			heroesRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	  
}


