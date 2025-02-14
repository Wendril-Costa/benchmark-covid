package com.wendril.application.controller;

import com.wendril.application.model.Benchmark;
import com.wendril.application.model.ResultadoBenchmark;
import com.wendril.application.model.User;
import com.wendril.application.repository.BenchmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenchmarkController extends ControllerGeneric<Benchmark, Long, BenchmarkRepository> {
    public BenchmarkController(BenchmarkRepository BenchmarkRepository) {
        this.repository = BenchmarkRepository;
    }

    public List<Benchmark> list() {
        return this.repository.findAll();
    }

    public Benchmark load(Long id) throws Exception {
        return this.repository.findById(id)
                .orElseThrow(() -> new Exception("Benchmark não encontrado"));

    }

    public List<Benchmark> findBenchmarksByUser(User user) {
        return repository.findAllByUser(user);
    }

    public void deleteResultadoBenchmark(ResultadoBenchmark resultado) throws Exception {
        Optional<Benchmark> benchmarkOptional = repository.findById(resultado.getBenchmark().getId());
        if (benchmarkOptional.isPresent()) {
            Benchmark benchmark = benchmarkOptional.get();
            boolean removed = benchmark.getResultados().remove(resultado);
            if (removed) {
                repository.save(benchmark);
            } else {
                throw new Exception("Erro ao remover resultado");
            }
        } else {
            throw new Exception("Benchmark não encontrado");
        }
    }

    @Override
    protected void validate(Benchmark entity, Mode mode) throws Exception {
        super.validate(entity, mode);

        switch (mode) {
            case SAVE:
                System.out.println("save");
                break;
            case UPDATE:
                System.out.println("update");
                if (!repository.existsById(entity.getId())) throw new Exception("Não existe");
                break;
            case DELETE:
                System.out.println("delete");
                if (!repository.existsById(entity.getId())) throw new Exception("Não existe");
                break;
        }
    }
}
