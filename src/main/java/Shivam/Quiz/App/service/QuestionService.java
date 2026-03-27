package Shivam.Quiz.App.service;


import Shivam.Quiz.App.Question;
import Shivam.Quiz.App.dao.QuestionDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }
}
