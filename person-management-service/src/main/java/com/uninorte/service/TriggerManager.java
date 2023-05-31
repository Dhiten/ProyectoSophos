package com.uninorte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TriggerManager {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public TriggerManager(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createTriggers() {
		String triggerSQL = """
				CREATE TRIGGER IF NOT EXISTS `update_spaces_on_enrollment` AFTER INSERT ON `enrollment`
				FOR EACH ROW
				BEGIN
				    DECLARE course_spaces INT;
				    DECLARE course_credits INT;
				    
				    -- Get the current number of available spaces for the course
				    SELECT spaces INTO course_spaces FROM course WHERE id_course = NEW.id_course;
				    SELECT credits INTO course_credits FROM course WHERE id_course = NEW.id_course;
				    -- Decrease the number of available spaces by 1
				    SET course_spaces = course_spaces - 1;
				    
				    -- Check if the available spaces have reached 0
				    IF course_spaces < 0 THEN
				        SIGNAL SQLSTATE '45000'
				            SET MESSAGE_TEXT = 'No available spaces in the course.';
				    ELSE
				        -- Update the spaces value in the course table
				        UPDATE course SET spaces = course_spaces WHERE id_course = NEW.id_course;
				        UPDATE student SET credits = credits + course_credits WHERE id_student = NEW.id_student;
				    END IF;
				END
				""";
		jdbcTemplate.execute(triggerSQL);
		
		triggerSQL = """
				CREATE TRIGGER IF NOT EXISTS `update_spaces_on_delete` AFTER DELETE ON `enrollment`
				FOR EACH ROW
				BEGIN
				    DECLARE course_spaces INT;
				    DECLARE course_credits INT;
				    
				    -- Get the current number of available spaces for the course
				    SELECT spaces INTO course_spaces FROM course WHERE id_course = OLD.id_course;
				    SELECT credits INTO course_credits FROM course WHERE id_course = OLD.id_course;
				    -- Increase the number of available spaces by 1
				    SET course_spaces = course_spaces + 1;
				    
				    -- Update the spaces value in the course table
				    UPDATE course SET spaces = course_spaces WHERE id_course = OLD.id_course;
				    UPDATE student SET credits = credits - course_credits WHERE id_student = OLD.id_student;
				END
				""";
		jdbcTemplate.execute(triggerSQL);
		
		triggerSQL = """
				CREATE TRIGGER IF NOT EXISTS `validate_prerequisite_approval` BEFORE INSERT ON `enrollment`
				FOR EACH ROW
				BEGIN
				    DECLARE prerequisite_approved BIGINT;
				    DECLARE pre_requisite BIGINT;
				    SELECT id_prerequisite INTO pre_requisite FROM course WHERE id_course = NEW.id_course;
				    -- Check if the course has a prerequisite
				    IF pre_requisite IS NOT NULL THEN
				        -- Check if the student has approved the prerequisite
				        SELECT COUNT(*) INTO prerequisite_approved
				        FROM enrollment
				        WHERE id_student = NEW.id_student
				          AND id_course = pre_requisite
				          AND approved = 1;
				        
				        -- If the prerequisite is not approved, raise an error
				        IF prerequisite_approved = 0 THEN
				            SIGNAL SQLSTATE '45000'
				                SET MESSAGE_TEXT = 'Student has not approved the prerequisite course.';
				        END IF;
				    END IF;
				END
				""";
		jdbcTemplate.execute(triggerSQL);
		
		triggerSQL = """
				CREATE TRIGGER IF NOT EXISTS update_credit_on_completion
				AFTER UPDATE ON enrollment
				FOR EACH ROW
				BEGIN
					DECLARE course_credits INT;
					SELECT credits INTO course_credits FROM course WHERE id_course = NEW.id_course;
                    
				    IF NEW.approved = true AND OLD.approved != NEW.approved THEN
				        UPDATE student SET credits = credits - course_credits WHERE id_student = NEW.id_student;
				    ELSEIF NEW.approved = false AND OLD.approved != NEW.approved THEN
				        UPDATE student SET credits = credits + course_credits WHERE id_student = NEW.id_student;
				    END IF;
				END
				""";
		jdbcTemplate.execute(triggerSQL);
	}
}
