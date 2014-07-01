package net.therap.validator;

import net.therap.domain.Comment;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/9/12
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void validate(Object o, Errors errors) {
        Comment comment = (Comment) o;
        if (comment.getAudioFileName().isEmpty() && comment.getContent().isEmpty()) {
            errors.reject("content", "content Empty");
        }
    }
}
