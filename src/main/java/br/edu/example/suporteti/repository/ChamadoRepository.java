package br.edu.example.suporteti.repository;

import br.edu.example.suporteti.model.Chamado;
import br.edu.example.suporteti.model.Status;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChamadoRepository {

    private final List<Chamado> chamados = new ArrayList<>();

    private Long contador = 1L;

    public List<Chamado> listarTodos() {

        return chamados;

    }

    public void salvar(Chamado chamado) {

        chamado.setId(contador++);
        chamados.add(chamado);

    }

    public Optional<Chamado> buscarPorId(Long id) {

        return chamados.stream()
                .filter(chamado -> chamado.getId().equals(id)).findFirst();

    }

    public List<Chamado> listarChamadosAbertos() {

        return chamados.stream()
                .filter(chamado -> chamado.getStatus() == Status.ABERTO).toList();

    }


}
