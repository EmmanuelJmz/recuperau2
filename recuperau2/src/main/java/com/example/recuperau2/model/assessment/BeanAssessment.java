package com.example.recuperau2.model.assessment;

import com.example.recuperau2.model.student.BeanStudent;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeanAssessment {
    private long id_assessment;
    private String subject;
    private String assessment;
    BeanStudent id_student;

    public long getId_assessment() {
        return id_assessment;
    }

    public void setId_assessment(long id_assessment) {
        this.id_assessment = id_assessment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public BeanStudent getId_student() {
        return id_student;
    }

    public void setId_student(BeanStudent id_student) {
        this.id_student = id_student;
    }
}
