package com.example.recuperau2.model.student;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeanStudent {
    private long id_student;
    private String name;
    private String last_name;
    private String bithday;
    private String curp;
    private String matricula;

    public long getId_student() {
        return id_student;
    }

    public void setId_student(long id_student) {
        this.id_student = id_student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBithday() {
        return bithday;
    }

    public void setBithday(String bithday) {
        this.bithday = bithday;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
