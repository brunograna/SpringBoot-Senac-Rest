package br.senac.rj.rest.service;

import br.senac.rj.rest.domain.Curso;
import br.senac.rj.rest.repository.CursoRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> findAll(){
        return repository.findAll();
    }

    public Curso save(Curso aluno) {
        return repository.save(aluno);
    }

    public Curso findById(Integer id) throws ObjectNotFoundException {
        Optional<Curso> aluno = repository.findById(id);
        return aluno.orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado, id "+id));
    }

    public Curso update(Curso cursoAlterado) throws ObjectNotFoundException {
        Curso aluno = findById(cursoAlterado.getId());
        aluno.setId(cursoAlterado.getId());
        aluno.setNome(cursoAlterado.getNome());
        return save(aluno);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
