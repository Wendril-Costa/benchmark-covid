package com.wendril.application.controller;

import com.wendril.application.model.ResultadoBenchmark;
import com.wendril.application.repository.ResultadoBenchmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultadoBenchmarkController extends ControllerGeneric<ResultadoBenchmark, Long, ResultadoBenchmarkRepository> {
    public ResultadoBenchmarkController(ResultadoBenchmarkRepository resultadoBenchmarkRepository) {
        this.repository = resultadoBenchmarkRepository;
    }

    public List<ResultadoBenchmark> list() {
        return this.repository.findAll();
    }

    public List<ResultadoBenchmark> loadByBenchmark(Long idBenchmark) {
        return this.repository.findAllByBenchmarkId(idBenchmark);
    }

    @Override
    protected void validate(ResultadoBenchmark entity, Mode mode) throws Exception {
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
