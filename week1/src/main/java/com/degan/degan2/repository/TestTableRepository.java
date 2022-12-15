package com.degan.degan2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.degan.degan2.domain.TestTable;


@Repository
public interface TestTableRepository extends JpaRepository<TestTable, Long> {

}
