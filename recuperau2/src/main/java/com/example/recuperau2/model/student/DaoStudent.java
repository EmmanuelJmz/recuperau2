package com.example.recuperau2.model.student;

import com.example.recuperau2.model.Repository;
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

public class DaoStudent implements Repository<BeanStudent> {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    MySQL client = new MySQL();
    
    @Override
    public List<BeanStudent> findAll() {
        List<BeanStudent> students = new ArrayList<>();
        BeanStudent student = null;
        try{
            connection = client.getConnection();
            String query = "SELECT * FROM students;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                student = new BeanStudent();
                student.setId_student(resultSet.getLong("id_student"));
                student.setName(resultSet.getString("name"));
                student.setLast_name(resultSet.getString("last_name"));
                student.setBithday(resultSet.getString("birthday"));
                student.setCurp(resultSet.getString("curp"));
                student.setMatricula(resultSet.getString("matricula"));

                students.add(student);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoStudent.class.getName())
                    .log(Level.SEVERE, "Error -> findAll" + e.getMessage());
        }finally {
            client.close(connection,preparedStatement,resultSet);
        }
        return students;
    }

    @Override
    public BeanStudent findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanStudent> save(BeanStudent student) {
        try{
            connection = client.getConnection();
            String query = "INSERT INTO students(name,last_name, birthday,curp,matricula)VALUES(?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getLast_name());
            preparedStatement.setString(3, student.getBithday());
            preparedStatement.setString(4,student.getCurp());
            preparedStatement.setString(5,student.getMatricula());

            if (preparedStatement.executeUpdate() == 1){
                return new Response<>(200,"Registrado correctamente", student, false);
            }else{
                return new Response<>(200,"Error al registrar",student,true);
            }
        }catch (SQLException e) {
            Logger.getLogger(DaoStudent.class.getName())
                    .log(Level.SEVERE, "Error -> save: " + e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        } finally {
            client.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Response<BeanStudent> update(BeanStudent student) {
        try{
            connection = client.getConnection();
            String query = "UPDATE students set name= ?, last_name=?, birthday=?, curp=?,matricula=? WHERE id_student=?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getLast_name());
            preparedStatement.setString(3,student.getBithday());
            preparedStatement.setString(4,student.getCurp());
            preparedStatement.setString(5,student.getMatricula());
            preparedStatement.setLong(6,student.getId_student());

            if (preparedStatement.executeUpdate() == 1){
                return new Response<>(200,"Actualizado correctamente", student, false);
            }else{
                return new Response<>(400,"Error al registrar",student,true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoStudent.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        }finally {
            client.close(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public Response<BeanStudent> remove(Long id) {
        return null;
    }


/*reglas*/

    public boolean validarCurp(String curp){
        try{
            connection = client.getConnection();
            String query = "SELECT * FROM students WHERE curp = ?;";
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

    public boolean validarMatricula(String matricula){

        try{

            connection = client.getConnection();
            String query = "SELECT * FROM students WHERE matricula = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, matricula);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;

            }

        }catch (Exception e){
            System.out.println("Error -> validarMatricula"+ e.getMessage());

        } finally {
            client.close(connection,preparedStatement,resultSet);

        }

        return false;
    }
}
