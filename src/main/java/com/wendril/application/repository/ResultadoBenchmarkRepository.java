package com.wendril.application.repository;

import com.wendril.application.model.ResultadoBenchmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoBenchmarkRepository extends JpaRepository<ResultadoBenchmark, Long> {

    List<ResultadoBenchmark> findAllByBenchmarkId(Long benchmarkId);

}
