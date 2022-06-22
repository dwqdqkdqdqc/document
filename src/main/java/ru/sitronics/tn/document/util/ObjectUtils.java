package ru.sitronics.tn.document.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ObjectUtils {

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
