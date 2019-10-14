package com.leonardo.shoppingcart.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ClientServiceTest {
    private Client client;
    private Client savedClient;
    private ClientService clientService;
    private ClientRepository clientRepository;

    @Before
    public void setUp() {
        this.client = new Client("Original Client", "firstEm@il.com");
        this.savedClient = new Client("123456789","Original Client", "firstEm@il.com");
        clientRepository = Mockito.mock(ClientRepository.class);
        clientService = new ClientService(clientRepository);
    }

    @Test
    public void testInsertNullClient() throws ClientException {
        assertThatThrownBy(() ->
                clientService.insert(null))
                .isInstanceOf(ClientException.class)
                .hasMessage("Client is null.");
    }

    @Test
    public void testInsertClientWithUsedEmail() {
        Client newClient = new Client("New", "firstEm@il.com");

        Mockito.when(clientRepository.existsByEmail(newClient.getEmail())).thenReturn(Boolean.TRUE);

        assertThatThrownBy(() ->
                clientService.insert(newClient))
                .isInstanceOf(ClientException.class)
                .hasMessage("E-mail already in use.");
    }

    @Test
    public void testFindClientById() throws ClientException {
        Mockito.when(clientRepository.findById(savedClient.getId())).thenReturn(Optional.of(savedClient));
        Optional<Client> result = clientService.findById(savedClient.getId());
        Assert.assertEquals(savedClient, result.get());
    }

    @Test
    public void testFindClientByIdNull() throws ClientException {
        assertThatThrownBy(() ->
                clientService.findById(null)).isInstanceOf(ClientException.class)
                .hasMessage("Client Id is empty.");
    }

    @Test
    public void testFindClientByEmail() throws ClientException {
        Mockito.when(clientRepository.findById(client.getEmail())).thenReturn(Optional.of(client));
        Optional<Client> result = clientService.findByEmail(client.getEmail());
        Assert.assertEquals(client, result.get());
    }

    @Test
    public void testFindClientByEmailNull() throws ClientException {
        assertThatThrownBy(() ->
                clientService.findByEmail(null)).isInstanceOf(ClientException.class)
                .hasMessage("E-mail is empty.");
    }

    @Test
    public void testSaveNullClient() throws ClientException {
        assertThatThrownBy(() ->
                clientService.save(null))
                .isInstanceOf(ClientException.class)
                .hasMessage("Client Id is null.");
    }

    @Test
    public void testSaveClientWithUsedEmail() {
        Client newClient = new Client("654321","New", "firstEm@il.com");

        Mockito.when(clientRepository.existsById(newClient.getId())).thenReturn(Boolean.TRUE);
        Mockito.when(clientRepository.existsByEmail(newClient.getEmail())).thenReturn(Boolean.TRUE);
        Mockito.when(clientRepository.findByEmail(newClient.getEmail())).thenReturn(Optional.of(savedClient));

        assertThatThrownBy(() ->
                clientService.save(newClient))
                .isInstanceOf(ClientException.class)
                .hasMessage("E-mail already in use.");
    }

    @Test
    public void testChangeClientEmail() throws ClientException {
        Client updatedClient = new Client("123456789", "Original Client", "newEm@il.com");

        Mockito.when(clientRepository.existsById(savedClient.getId())).thenReturn(Boolean.TRUE);
        Mockito.when(clientRepository.existsByEmail(updatedClient.getEmail())).thenReturn(Boolean.FALSE);
        Mockito.when(clientRepository.save(updatedClient)).thenReturn(updatedClient);

        Client result = clientService.save(updatedClient);

        Assert.assertEquals(result, updatedClient);
    }

    @Test
    public void testDeleteClient() throws ClientException{
        Mockito.when(clientRepository.existsByEmail(savedClient.getEmail())).thenReturn(Boolean.TRUE);
        Mockito.when(clientRepository.existsById(savedClient.getId())).thenReturn(Boolean.TRUE);

        clientService.remove(savedClient);
    }

    @Test
    public void testDeleteClientIdNull() {
        assertThatThrownBy(() ->
                clientService.remove(client))
                .isInstanceOf(ClientException.class)
                .hasMessage("Client ID is null.");
    }
}
