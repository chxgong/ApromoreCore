package org.apromore.dao;

import org.apromore.dao.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Integer> {

    /**
     * Get statistics of specified LogId from DB
     * @param logid
     * @return
     */
    List<Statistic> findByLogid(Integer logid);
}

