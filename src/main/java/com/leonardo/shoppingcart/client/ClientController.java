package com.leonardo.shoppingcart.client;

import com.leonardo.shoppingcart.base.BasePaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;

@RestController
@RequestMapping(BasePaths.Client.ROOT)
public class ClientController {

    public ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Invalid client parameters");
        }
    }

    @GetMapping(BasePaths.Client.ID)
    public ResponseEntity<Resource<Client>> get(@PathVariable String id, BindingResult bindingResult) {
        Client result;
        try {
            validate(bindingResult);
            result = clientService.findById(id).get();
        } catch (ClientException e) {
            throw new BadRequestException(e);
        }

        Resource<Client> resource = new Resource<>(result);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping(BasePaths.Client.EMAIL)
    public ResponseEntity<Resource<Client>> getByEmail(@PathVariable String email, BindingResult bindingResult) {
        Client result;
        try {
            validate(bindingResult);
            result = clientService.findByEmail(email).get();
        } catch (ClientException e) {
            throw new BadRequestException(e);
        }

        Resource<Client> resource = new Resource<>(result);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Resource<Client>> post(@RequestBody @Valid Client client, BindingResult bindingResult) {
        Client result;
        try {
            validate(bindingResult);
            result = clientService.insert(client);
        } catch (ClientException | BadRequestException e) {
            throw new BadRequestException(e);
        }
        Resource<Client> resource = new Resource<>(result);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Resource<Client>> put(@RequestBody @Valid Client client, BindingResult bindingResult) {
        Client result;
        try {
            validate(bindingResult);
            result = clientService.save(client);
        } catch (ClientException | BadRequestException e) {
            throw new BadRequestException(e);
        }
        Resource<Client> resource = new Resource<>(result);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @DeleteMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> delete(@RequestBody @Valid Client client, BindingResult bindingResult) {
        try {
            validate(bindingResult);
            clientService.remove(client);
        } catch (ClientException | BadRequestException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

        return ResponseEntity.ok("Client with ID: " + client.getId() + " deleted.");
    }
}
