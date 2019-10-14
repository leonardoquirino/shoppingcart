package com.leonardo.shoppingcart.client;

import com.leonardo.shoppingcart.base.BaseDocument;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Document
@NoArgsConstructor
public class Client extends BaseDocument<String> implements Serializable {

    private static final long serialVersionUID = -2928194286949389288L;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    @Email
    private String email;

    public Client(String nome, String email) {
        this(null, nome, email);
    }

    public Client(String id, String nome, String email) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
        }
        if (nome == null || email == null) {
            throw new IllegalArgumentException("Client fields should not be null");
        }
        this.nome = nome;
        this.email = email;
    }
}
