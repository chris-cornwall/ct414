/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author cornwall
 */
public class TestDriver {
 ArrayList<Student> students = new ArrayList();
      
    public ArrayList<Student> init (){
        // Create question details
        String questionDetails1 = "What colour are oranges?";
        String questionDetails2 = "1+1=";
        String questionDetails3 = "Which county has the best footballers?";
         
        // Create answer options
        String[] answerOptions1 = {"yellow", "blue", "orange"};
        String[] answerOptions2 = {"2", "4", "1"};
        String[] answerOptions3 = {"Roscommon", "Mayo", "Donegal"};
        
        // Create questions using these
        Question question1 = new MCQ(1, questionDetails1, answerOptions1);
        Question question2 = new MCQ(2, questionDetails2, answerOptions2);
        Question question3 = new MCQ(3, questionDetails3, answerOptions3);
        
        // Create sets of questions
        ArrayList<Question> questionSet1 = new ArrayList(); 
        ArrayList<Question> questionSet2 = new ArrayList();
        ArrayList<Question> questionSet3 = new ArrayList(); 
        
        // Add quetions to these sets
        questionSet1.add(question1);
        questionSet1.add(question2);
        questionSet1.add(question3);
        
        questionSet2.add(question1);
        questionSet2.add(question2);
        questionSet2.add(question3);
        
        questionSet1.add(question1);
        questionSet2.add(question2);
        questionSet3.add(question3);
        
        
        
        // Create array of course codes... Used by assignment to determine if an assignment is for a particular class
        // Each assessment has the same coursecodes for now... Will need to change
        ArrayList<String> courseCodes = new ArrayList();
        courseCodes.add("CT111");
        courseCodes.add("CT222");
        courseCodes.add("CT333");
        
        // Create due dates...
        Date dueDate1 = new Date (2018, 2, 14, 1800, 0);
        Date dueDate2 = new Date (2018, 2, 21, 1800, 0);
        Date dueDate3 = new Date (2018, 2, 15, 1800, 0);
        
        
        //Create Assessments..
        Assessment assessment1 = new MCQAssessment(questionSet1, courseCodes, dueDate1);
        Assessment assessment2 = new MCQAssessment(questionSet2, courseCodes, dueDate2);
        Assessment assessment3 = new MCQAssessment(questionSet3, courseCodes, dueDate3);

        
        //Create ArrayLists of Assessments
        //Just adding all three assessments to assessmentlist1 for now...
        ArrayList<Assessment> assessmentList1 = new ArrayList();
        assessmentList1.add(assessment1);
        assessmentList1.add(assessment2);
        assessmentList1.add(assessment3);
        ArrayList<Assessment> assessmentList2 = new ArrayList();
        assessmentList2.add(assessment2);
        ArrayList<Assessment> assessmentList3 = new ArrayList();
        assessmentList3.add(assessment3);
        
        //ArrayList of Assessment summary.... Need to use for getAvailableSummary down below
        ArrayList<String> summary = new ArrayList();
        summary.add("Maths");
        summary.add("English");
        summary.add("drinking cans");
        
        //Create students, assign id, password and assessments
       
        Student s1 = new Student(1, "pass1");
        s1.setAssessments(assessmentList1);
        s1.setSummary(summary);
        students.add(s1);
        Student s2 = new Student(2, "pass2");
        s2.setAssessments(assessmentList2);
        students.add(s2);
        Student s3 = new Student(3, "pass3");
        s3.setAssessments(assessmentList3);
        students.add(s3);  
        System.out.println("Setup Complete!");
        
        return students;

    }

    
}
