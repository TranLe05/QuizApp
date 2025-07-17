package com.lhbt.services;

import com.lhbt.pojo.Choice;
import com.lhbt.pojo.Question;
import com.lhbt.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionServices {

    public List<Question> getQuestions() throws SQLException, Exception {
        //Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();

        //Truy van
        List<Question> questions = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM question")) {
            while (rs.next()) {
                Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
                questions.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh sách question: " + e.getMessage());
        }
        return questions;
    }

    public List<Question> getQuestions(String kw) throws SQLException, Exception {
        //Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stmt = conn.prepareCall("SELECT * FROM question WHERE content like concat('%', ?, '%')");
        stmt.setString(1, kw);
        ResultSet rs = stmt.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }
    
    public List<Question> getQuestions(int num) throws SQLException, Exception {
        //Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stmt = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
        stmt.setInt(1, num);
        ResultSet rs = stmt.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).addAllChoice(this.getChoiceByQuestionId(rs.getInt("id"))).build();
            questions.add(q);
        }
        return questions;
    }
    
    public List<Choice> getChoiceByQuestionId(int questionId) throws SQLException, Exception {
        //Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stmt = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stmt.setInt(1, questionId);
        ResultSet rs = stmt.executeQuery();

        List<Choice> choices = new ArrayList<>();
        while (rs.next()) {
            Choice c = new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct"));
            choices.add(c);
        }
        return choices;
    }

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            int questionId = -1;
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                questionId = rs.getInt(1);
            }

            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
            stm = conn.prepareCall(sql);
            for (var c : q.getChoices()) {
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);

                stm.executeUpdate();
            }
            conn.commit();
        } else {
            conn.rollback();
        }
    }
}
