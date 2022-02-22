package dev.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.entites.Categorie;
import dev.repositories.CategorieRepository;

@Service
public class CategorieService {
	private CategorieRepository categorieRepository;

	public CategorieService(CategorieRepository categorieRepository) {
		super();
		this.categorieRepository = categorieRepository;
	}

	public List<Categorie> listerCategorie() {
		return this.categorieRepository.findAll();
	};
}
