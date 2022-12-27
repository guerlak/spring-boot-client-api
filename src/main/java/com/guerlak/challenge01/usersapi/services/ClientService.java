package com.guerlak.challenge01.usersapi.services;

import com.guerlak.challenge01.usersapi.dtos.ClientDTO;
import com.guerlak.challenge01.usersapi.entities.Client;
import com.guerlak.challenge01.usersapi.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repo;

    public Page<ClientDTO> findAllPaged(PageRequest pg) {
        Page<Client> list = repo.findAll(pg);
        return list.map(c -> new ClientDTO(c.getId(), c.getName(), c.getCpf(), c.getIncome(), c.getBirthDate(), c.getChildren()));
    }

    @Transactional(readOnly = true)
    public Optional<Client> findById(Long id) {
        Optional<Client> optionalClient = repo.findById(id);
        return optionalClient;
    }

    @Transactional
    public Client update(ClientDTO dto, Long id){
        Client client = repo.getReferenceById(id);
        client.setName(dto.getName());
        client.setChildren(dto.getChildren());
        client.setCpf(dto.getName());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());

        return client;
    }

    public void deleteClient(Long id) throws EmptyResultDataAccessException{
        repo.deleteById(id);
    }

    @Transactional
    public Client create(ClientDTO dto) {

        Client client = new Client();

        client.setName(dto.getName());
        client.setChildren(dto.getChildren());
        client.setCpf(dto.getName());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());

        return repo.save(client);
    }
}
