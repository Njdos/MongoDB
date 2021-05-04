package company;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Platforms{
    enum  somePlatform
    {
        Firstly,
        Secondary,
        Else
    }
}