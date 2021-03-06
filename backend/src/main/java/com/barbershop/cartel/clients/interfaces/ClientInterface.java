package com.barbershop.cartel.clients.interfaces;

import com.barbershop.cartel.clients.entity.ClientEntity;

import java.util.Optional;

public interface ClientInterface {
    ClientEntity saveNewClient(String email, String username);

    Optional<ClientEntity> findByEmail(String email);
}
