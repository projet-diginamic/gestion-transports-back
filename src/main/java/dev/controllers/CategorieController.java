package dev.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entites.Categorie;
import dev.services.CategorieService;

@RestController
@RequestMapping("categorie")
public class CategorieController {
	private CategorieService categorieService;

	public CategorieController(CategorieService categorieService) {
		super();
		this.categorieService = categorieService;
	}

	@GetMapping
	public List<Categorie> listerCategorie() {
		return this.categorieService.listerCategorie();
	}
}
