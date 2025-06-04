package br.fiap.repositories;


import br.fiap.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface _CrudRepository<T> {

    // Criação
    void add(T object);

    // Atualização
    boolean update(T object);

    // Remoção física (exclusão permanente do banco)
    boolean remove(T object);
    boolean removeById(int id);

    // Remoção lógica (marca como excluído, sem apagar do banco)
    boolean delete(T object);
    boolean deleteById(int id);

    // Leitura

    // Traz todos os registros (inclusive deletados logicamente)
    List<T> getAll();

    // Traz apenas os registros ativos (não deletados logicamente)
    List<T> getAllAtivos();

    // Agora está correto para qualquer tipo T
    List<T> get();

    // Busca por ID considerando apenas registros ativos
    Optional<T> getById(int id);

    // Busca por ID inclusive registros deletados logicamente
    Optional<T> getByIdIncludingDeleted(int id);

    // Verifica se existe pelo ID (somente ativos por padrão)
    boolean existsById(int id);


}
