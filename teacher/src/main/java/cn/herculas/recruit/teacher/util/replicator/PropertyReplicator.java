package cn.herculas.recruit.teacher.util.replicator;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class PropertyReplicator {
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = sourceWrapper.getPropertyDescriptors();
        Set<String> nullPropertyNames = new HashSet<>();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            if (sourceWrapper.getPropertyValue(descriptor.getName()) == null)
                nullPropertyNames.add(descriptor.getName());
        }
        return nullPropertyNames.toArray(new String[0]);
    }

    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, PropertyReplicator.getNullPropertyNames(source));
    }
}
