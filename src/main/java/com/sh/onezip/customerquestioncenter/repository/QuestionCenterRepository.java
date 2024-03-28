package com.sh.onezip.customerquestioncenter.repository;

import com.sh.onezip.customerquestioncenter.entity.QuestionCenter;
import com.sh.onezip.member.entity.Member;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Table(name = "tb_qone")
@Repository
public interface QuestionCenterRepository extends JpaRepository <QuestionCenter, Long>{
    // HBK start
    @Query("SELECT qc FROM QuestionCenter qc WHERE qc.id = :id ORDER BY qc.id ASC")
    QuestionCenter findByQId(@Param("id") Long id);
    // HBK end
}
