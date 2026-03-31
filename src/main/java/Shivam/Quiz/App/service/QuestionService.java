package Shivam.Quiz.App.service;


import Shivam.Quiz.App.model.Question;
import Shivam.Quiz.App.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question question) {
        try {
            if (!questionDao.existsById(id)) {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }

            question.setId(id);
            questionDao.save(question);

            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }
}
