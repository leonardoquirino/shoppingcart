package com.leonardo.shoppingcart.client;

import com.leonardo.shoppingcart.DemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = DemoApplication.class)
@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class ClientMongodbIntegrationTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void insertClientTest() {
        Client client = new Client("Client Insert", "insert@client.com");
        Client saved = clientRepository.insert(client);
        assertNotNull(saved.getId());
        assertNotNull(saved.getNome());
        assertNotNull(saved.getEmail());
        assertEquals(saved.getNome(), client.getNome());
        assertEquals(saved.getEmail(), client.getEmail());
    }

    @Test
    public void deleteClientTest() {
        Client client = new Client("Client Insert", "insert@client.com");
        Client saved = clientRepository.insert(client);
        assertNotNull(saved.getId());
        String id = client.getId();
        clientRepository.deleteById(id);
        assertEquals(clientRepository.existsById(id), Boolean.FALSE);
    }

    @Test
    public void findByIdClientTest() {
        Client client = new Client("Client Insert", "insert@client.com");
        Client saved = clientRepository.insert(client);
        assertNotNull(saved.getId());
        String id = client.getId();
        client = clientRepository.findById(id).get();
        assertEquals(client, saved);
    }

    @Test
    public void findByEmailClientTest() {
        Client client = new Client("Client Insert", "insert@client.com");
        Client saved = clientRepository.insert(client);
        assertNotNull(saved.getId());
        String email = client.getEmail();
        client = clientRepository.findByEmail(email).get();
        assertEquals(client, saved);
    }

    @Test
    public void updateClientTest() {
        Client client = new Client("Client Update", "update@client.com");
        Client saved = clientRepository.insert(client);
        Assert.assertNotNull(saved.getId());
        String updateEmail = "updateEm@ail.com";
        saved.setEmail(updateEmail);
        saved = clientRepository.save(saved);
        Assert.assertTrue(saved.getEmail().equals(updateEmail));
    }
}
