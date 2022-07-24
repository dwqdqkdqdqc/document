package ru.sitronics.tn.document.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ObjectUtils {


    public static <T> T partialUpdate(T dbObject, T partialUpdateObject) {
        String[] ignoredProperties = getNullPropertyNames(partialUpdateObject);
        BeanUtils.copyProperties(partialUpdateObject, dbObject, ignoredProperties);
        return dbObject;
    }

    private static String[] getNullPropertyNames(Object object) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(object);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public static <T, D> D convertObject(T source, D target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T, D> D convertObject(T source, D target, String fields) {
        if (fields != null) {
            Set<String> selectedFields = Arrays.stream(fields.split(" *, *")).collect(Collectors.toSet());
            // Set<String> selectedFields2 =  Arrays.stream(fields.split("\\.")).collect(Collectors.toSet());
            Set<String> selectedFields2 = selectedFields.stream()
                    .map(f -> f.split("\\.")[0])
                    .collect(Collectors.toSet());

            //  String str = "contract.type".contains(".") ? "contract.type" : "";
            //  System.out.println(str);

            String[] excludedProperties =
                    Arrays.stream(BeanUtils.getPropertyDescriptors(target.getClass()))
                            .map(PropertyDescriptor::getName)
                            .filter(name -> !selectedFields.contains(name) || name.contains("."))
                            .toArray(String[]::new);
            excludedProperties[2] = "content";
            BeanUtils.copyProperties(source, target, excludedProperties);

            return target;
        }
        return target;
    }
/*
    public static <T, D> D convertObject(T source, D target, String fields) {
        if (fields != null) {
            Set<String> selectedFields = Arrays.stream(fields.split(" *, *")).collect(Collectors.toSet());
            // Set<String> selectedFields2 =  Arrays.stream(fields.split("\\.")).collect(Collectors.toSet());
            Set<String> selectedFields2 = selectedFields.stream()
                    .map(f -> f.split("\\.")[0])
                    .collect(Collectors.toSet());

            String str = "contract.type".contains(".") ? "contract.type" : "";
            System.out.println(str);

            String[] excludedProperties =
                    Arrays.stream(BeanUtils.getPropertyDescriptors(target.getClass()))
                            .map(PropertyDescriptor::getName)
                            .filter(name -> !selectedFields.contains(name) || name.contains("."))
                            .toArray(String[]::new);
            excludedProperties[6] = "content";
            BeanUtils.copyProperties(source, target, excludedProperties);

            return target;
        }
        return target;
    }
    */

}
