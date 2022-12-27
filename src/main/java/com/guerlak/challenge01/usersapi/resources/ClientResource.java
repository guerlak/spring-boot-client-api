package com.guerlak.challenge01.usersapi.resources;

import com.guerlak.challenge01.usersapi.dtos.ClientDTO;
import com.guerlak.challenge01.usersapi.entities.Client;
import com.guerlak.challenge01.usersapi.exceptions.NotFoundException;
import com.guerlak.challenge01.usersapi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("clients")
public class ClientResource {
    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        PageRequest pg = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ClientDTO> list  = service.findAllPaged(pg);
       return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        Optional<Client> res = service.findById(id);
        Client client = res.orElseThrow(() -> new NotFoundException("Cliente não encontrado no id: " + id));
        return ResponseEntity.ok().body(convertClientToDTO(client));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(
            @RequestBody ClientDTO dto
    ){
        Client client = service.create(dto);
        ClientDTO resDTO = convertClientToDTO(client);

        return ResponseEntity.ok().body(resDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(
            @RequestBody ClientDTO dto,
            @PathVariable Long id
    ){
        Client client = service.update(dto, id);
        return ResponseEntity.ok().body(convertClientToDTO(client));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable Long id){
        try{
            service.deleteClient(id);
            return ResponseEntity.noContent().build();
        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException("Id " + id + " não encontrado.");
        }
    }

    private ClientDTO convertClientToDTO(Client client) {
        return new ClientDTO(client.getId(),
                client.getName(),
                client.getCpf(),
                client.getIncome(),
                client.getBirthDate(),
                client.getChildren()
        );
    }

}
