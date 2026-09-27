package com.example.character_store.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.character_store.model.Producto;
import com.example.character_store.repository.ProductoRepository;

@Service
public class ProductoService {
	
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listar(String universo, String q) {
        if (q != null && !q.isBlank()) {
            return repository.findByNombreContainingIgnoreCaseOrderByNombreAsc(q);
        }
        if (universo != null && !universo.isBlank()) {
            return repository.findByUniversoOrderByNombreAsc(universo);
        }
        return repository.findAll();
    }

    public Producto buscar(Long id) {
        return repository.findById(id).orElse(null);
    }


}
