package com.degan.degan2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.degan.degan2.domain.TestTable;
import com.degan.degan2.repository.TestTableRepository;


@Service
public class TestaaService {
    
    @Autowired
    private TestTableRepository rs;

    public List<TestTable> fildAll() {
        return rs.findAll();
    }

    public TestTable findById(Long no) {
        // 없으면 다르게...
        return rs.findById(no).orElse(new TestTable());
    }

    public TestTable save(TestTable tt) {
        rs.save(tt);
        return tt;
    }
}
