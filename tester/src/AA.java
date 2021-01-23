import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AA {

	static void dosya_yaz() {
		SIS sis = new SIS();
		
		Field f;
		try {
			f = sis.getClass().getDeclaredField("studentList");
			f.setAccessible(true);
			List<Student> iWant = (List<Student>) f.get(sis);
			
			List<String> lines = new ArrayList<String>();
			Random rand = new Random();
			
			
			for(int i = 0; i < 100; i++) {
				int rand1 = rand.nextInt(iWant.size());
				int rand2 = rand.nextInt(iWant.get(rand1).getTakenCourses().size());
				
				lines.add(new Integer(iWant.get(rand1).getStudentID()).toString());
				lines.add(new Integer(iWant.get(rand1).getTakenCourses().get(rand2).getCourseCode()).toString());
				lines.add(new Integer(iWant.get(rand1).getTakenCourses().get(rand2).getYear()).toString());
				lines.add(new Double(rand.nextDouble()*100).toString());
				lines.add(new Double(rand.nextDouble()*100).toString());
				lines.add(new Double(rand.nextDouble()*100).toString());
				
				
			}
		
			
			Files.write(Paths.get("student_course.txt"), lines);
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //NoSuchFieldException
		catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void dosya_oku_test() {
		SIS sis = new SIS();
		
		PrintStream fileStream;
		try {
			fileStream = new PrintStream("test_result.txt");
			System.setOut(fileStream);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			
			Stream<String> liness = Files.lines(Paths.get("student_course.txt"));
			List<String> lines = liness.collect(Collectors.toList());
			
			
			Random rand = new Random();
			
			
			for(int i = 0; i < 100; i++) {
				int aa = i * 6;
				// Bir suru test yapalim...
				int studentID   = Integer.parseInt(lines.get(aa));
				int courseCode  = Integer.parseInt(lines.get(aa+1));
				int year        = Integer.parseInt(lines.get(aa+2));
				double newGrade1 = Double.parseDouble(lines.get(aa+3));
				double newGrade2 = Double.parseDouble(lines.get(aa+4));
				double newGrade3 = Double.parseDouble(lines.get(aa+5));
				
				
				System.out.println(sis.getGrade(studentID, courseCode, year));
				sis.updateExam(studentID, courseCode, "Midterm1", newGrade1);
				sis.updateExam(studentID, courseCode, "Midterm2", newGrade2);
				sis.updateExam(studentID, courseCode, "Final", newGrade3);
				sis.createTranscript(studentID);
				sis.findCourse(courseCode);
				sis.createHistogram(courseCode, year);
				
				
				
				
			}
		
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //NoSuchFieldException
		catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		dosya_yaz();
		dosya_oku_test();
	}

}
