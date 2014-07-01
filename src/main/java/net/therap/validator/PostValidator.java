package net.therap.validator;

import net.therap.domain.WallPost;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/9/12
 * Time: 12:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class PostValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void validate(Object o, Errors errors) {
        WallPost wallPost = (WallPost) o;
        if (wallPost.getAudioFileName().isEmpty() && wallPost.getPostContent().isEmpty()) {
            errors.rejectValue("postContent", "Post is empty");
        }
    }
}
