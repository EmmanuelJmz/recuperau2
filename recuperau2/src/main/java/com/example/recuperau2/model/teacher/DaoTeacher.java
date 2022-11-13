package com.example.recuperau2.model.teacher;

import com.example.recuperau2.model.Repository;
import com.example.recuperau2.model.assessment.BeanAssessment;
import com.example.recuperau2.model.student.BeanStudent;
import com.example.recuperau2.utils.MySQL;
import com.example.recuperau2.utils.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoTeacher implements Repository<BeanTeacher> {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    MySQL client = new MySQL();

    @Override
    public List<BeanTeacher> findAll() {
        List<BeanTeacher> teachers = new ArrayList<>();
        BeanTeacher teacher = null;

        try {
            connection = client.getConnection();
            String query = "SELECT * FROM teacher;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                teacher = new BeanTeacher();
                teacher.setId_teacher(resultSet.getLong("id_teacher"));
                teacher.setName(resultSet.getString("name"));
                teacher.setLast_name(resultSet.getString("last_name"));
                teacher.setBirthday(resultSet.getString("birthday"));
                teacher.setCurp(resultSet.getString("curp"));
                teacher.setNum_empleado(resultSet.getString("num_empleado"));

                teachers.add(teacher);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> findAll" + e.getMessage());
        }finally {
            client.close(connection,preparedStatement,resultSet);
        }
        return teachers;
    }

    @Override
    public BeanTeacher findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanTeacher> save(BeanTeacher teacher) {
        try {
            connection = client.getConnection();
            String query = "INSERT INTO teacher (name,last_name, birthday,curp,num_empleado)VALUES(?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,teacher.getName());
            preparedStatement.setString(2,teacher.getLast_name());
            preparedStatement.setString(3, teacher.getBirthday());
            preparedStatement.setString(4,teacher.getCurp());
            preparedStatement.setString(5,teacher.getNum_empleado());

            if (preparedStatement.executeUpdate() == 1){
                return new Response<>(200,"Registrado correctamente", teacher, false);
            }else{
                return new Response<>(200,"Error al registrar",teacher,true);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> save: " + e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        } finally {
            client.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Response<BeanTeacher> update(BeanTeacher teacher) {
        try{
            connection = client.getConnection();
            String query = "UPDATE teacher set name= ?, last_name=?, birthday=?, curp=?,num_empleado=? WHERE id_teacher=?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,teacher.getName());
            preparedStatement.setString(2,teacher.getLast_name());
            preparedStatement.setString(3,teacher.getBirthday());
            preparedStatement.setString(4,teacher.getCurp());
            preparedStatement.setString(5, teacher.getNum_empleado());
            preparedStatement.setLong(6,teacher.getId_teacher());

            if (preparedStatement.executeUpdate() == 1){
                return new Response<>(200,"Actualizado correctamente", teacher, false);
            }else{
                return new Response<>(400,"Error al registrar",teacher,true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        }finally {
            client.close(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public Response<BeanTeacher> remove(Long id) {
        return null;
    }

    public List<BeanAssessment> getAssessment() {
        List<BeanAssessment> assessments = new ArrayList<>();
        BeanAssessment assessment = null;
        BeanStudent student = null;

        try {
            connection = client.getConnection();
            String query = "SELECT as.*, st.name FROM assessment as INNER JOIN students st ON as.id_student = st.id_student;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                assessment = new BeanAssessment();
                student = new BeanStudent();
                student.setName(resultSet.getString("name"));
                assessment.setId_student(student);
                assessment.setSubject(resultSet.getString("subject"));
                assessment.setAssessment(resultSet.getString("assessment"));
                assessments.add(assessment);
            }

        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> findAll" + e.getMessage());
        }finally {
            client.close(connection,preparedStatement,resultSet);
        }
        return assessments;
    }

    public List<BeanAssessment> average() {
        List<BeanAssessment> assessements = new ArrayList<>();
        BeanAssessment assessment = null;

        try {
            connection = client.getConnection();
            String query = "SELECT AVG(assessement) AS average FROM assessment";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                assessment = new BeanAssessment();
                assessment.setAssessment(resultSet.getString("average"));
               assessements.add(assessment);
            }

        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName()).log(Level.SEVERE, "Error -> findAll" + e.getMessage());
        }finally {
            client.close(connection,preparedStatement,resultSet);
        }
        return assessements;

    }

    public boolean validarCurp(String curp){
        try{
            connection = client.getConnection();
            String query = "SELECT * FROM teacher WHERE curp = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, curp);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;

            }
        }catch (Exception e){
            System.out.println("Error -> validarCurp"+ e.getMessage());

        } finally {
            client.close(connection,preparedStatement,resultSet);

        }

        return false;
    }

    public boolean validarEmpleado(int number){

        try{

            connection = client.getConnection();
            String query = "SELECT * FROM teacher WHERE num_empleado = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;

            }

        }catch (Exception e){
            System.out.println("Error -> validarNumEmpleado"+ e.getMessage());

        } finally {
            client.close(connection,preparedStatement,resultSet);

        }

        return false;
    }
}

