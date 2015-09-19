package br.edu.ufrn.imd.coopuni.boundary;

public interface AbstractDAO<K, E> {
  void create(E entity) throws Exception;

  void delete(K id);

  E retrieve(K id);

  void update(E entity);
}
