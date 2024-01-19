package dev;

import com.seomse.commons.utils.packages.classes.field.FieldUtil;

import java.lang.reflect.Field;

public class BooleanTypeCheck {
    public static void main(String[] args) throws Exception {
        InsertObj obj = new InsertObj();
        obj.isTranslated = false;

        Field [] fields = FieldUtil.getFieldArrayToParents(InsertObj.class);
        for(Field field : fields){
            System.out.println(field.getType());

            if(field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
                Boolean a = field.getBoolean(obj);
                System.out.println("불린 " + a);
            }

        }
    }
}
