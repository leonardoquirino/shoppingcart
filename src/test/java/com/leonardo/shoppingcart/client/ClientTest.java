package com.leonardo.shoppingcart.client;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class ClientTest {
    private Client client;

    @Before
    public void setUp() {
        this.client = new Client("Original Client", "origin@l.com");
    }

    @Test
    public void testNullArguments(){
        assertThatThrownBy(() -> {
            new Client(null, null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void clientIsSerializable() {
        final byte[] serializedMyClient = SerializationUtils.serialize(client);
        final Client deserializedClient = SerializationUtils.deserialize(serializedMyClient);
        assertEquals(client, deserializedClient);
    }

}