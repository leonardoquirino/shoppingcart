package com.leonardo.shoppingcart.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client insert(Client client) throws ClientException {
        if (client == null) {
            throw new ClientException("Client is null.");
        }

        if (clientRepository.existsById(client.getId())) {
            throw new ClientException("Client ID already in use.");
        }

        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new ClientException("E-mail already in use.");
        }

        return clientRepository.insert(client);
    }

    public Optional<Client> findById(String id) throws ClientException {
        if (id == null || id.trim().isEmpty()) {
            throw new ClientException("Client Id is empty.");
        }
        return clientRepository.findById(id);
    }

    public Optional<Client> findByEmail(String email) throws ClientException {
        if (email == null || email.trim().isEmpty()) {
            throw new ClientException("E-mail is empty.");
        }
        return clientRepository.findById(email);
    }

    public Client save(Client client) throws ClientException {
        if (client == null || client.getId() == null) {
            throw new ClientException("Client Id is null.");
        }

        if (!clientRepository.existsById(client.getId())) {
            throw new ClientException("Client not found");
        }

        Optional<Client> ogClient = clientRepository.findByEmail(client.getEmail());
        if (ogClient.isPresent() && !ogClient.get().getId().equals(client.getId())) {
            throw new ClientException("E-mail already in use.");
        }

        return clientRepository.save(client);
    }

    public void remove(Client client) throws ClientException {
        if (client == null || client.getId() == null) {
            throw new ClientException("Client ID is null.");
        }

        clientRepository.deleteById(client.getId());
    }
}