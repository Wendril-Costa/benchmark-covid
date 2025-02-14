package com.wendril.application.repository;

import com.wendril.application.model.Benchmark;
import com.wendril.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenchmarkRepository extends JpaRepository<Benchmark, Long>, JpaSpecificationExecutor<Benchmark> {
    Optional<Benchmark> findById(Long id);

    List<Benchmark> findAllByUser(User user);

    void deleteById(Long id);
}
