package com.example.demoWeb.services;

import com.example.demoWeb.entities.EntityNomenclature;
import com.example.demoWeb.repository.RepositoryNomenclature;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class ServicesNomenclature {
    RepositoryNomenclature repositoryNomenclature;

    public ServicesNomenclature(RepositoryNomenclature repositoryNomenclature) {
        this.repositoryNomenclature = repositoryNomenclature;
    }

    @Transactional
    public List<EntityNomenclature> readNomenclature() {
        return (List<EntityNomenclature>) repositoryNomenclature.findAll();
    }

    @Transactional
    public EntityNomenclature createNomenclature(EntityNomenclature entityNomenclature) {
        return repositoryNomenclature.save(entityNomenclature);
    }

    @Transactional
    public EntityNomenclature updateNomenclature (Long id, EntityNomenclature entityNomenclature) {
        EntityNomenclature entityNomenclatureFromDB = repositoryNomenclature.findById(id).get();
        if (entityNomenclatureFromDB == null) {
            throw new RestClientException("Nomenclature with id= " + entityNomenclatureFromDB.getId() + "not found");
        }
        entityNomenclatureFromDB.setName(entityNomenclature.getName());
        return repositoryNomenclature.save(entityNomenclatureFromDB);
    }

    @Transactional
    public Long deleteNomenclature(Long id) {
        EntityNomenclature entityNomenclature = repositoryNomenclature.findById(id).get();
        if (entityNomenclature == null) {
            throw new RestClientException("Nomenclature with id= " + id + "not found");
        }
        repositoryNomenclature.deleteById(id);
        return id;
    }
}
