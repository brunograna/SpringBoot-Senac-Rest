package br.senac.rj.rest.service;

import br.senac.rj.rest.domain.Categoria;
import br.senac.rj.rest.repository.CategoriaRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> findAll(){
        return repository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria findById(Integer id) throws ObjectNotFoundException {
        Optional<Categoria> categoria = repository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrado, id "+id));
    }

    public Categoria update(Categoria categoriaAlterada) throws ObjectNotFoundException {
        Categoria categoria = findById(categoriaAlterada.getId());
        categoria.setId(categoriaAlterada.getId());
        categoria.setNome(categoriaAlterada.getNome());
        return save(categoria);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
