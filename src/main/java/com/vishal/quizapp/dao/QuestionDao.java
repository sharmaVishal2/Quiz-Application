package com.vishal.quizapp.dao;


import com.vishal.quizapp.model.Question;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
        List<Question> findByCategory(String category);

        void deleteById(SingularAttribute<AbstractPersistable, Serializable> id);

        @Query(value = "SELECT * FROM question \n" +
                "WHERE category = :category \n" +
                "ORDER BY RAND() \n" +
                "LIMIT :numQ;", nativeQuery = true)
        List<Question>findRandomQuestionsByCategory(String category, int numQ);

}
