package com.atguigu.udtf2;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/2.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class HiveUDTFTest2 extends GenericUDTF {
    private transient List<String> forwarddatas = new ArrayList<>();

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        //参数检测
        if (argOIs.getAllStructFieldRefs().isEmpty()) {
            throw new UDFArgumentLengthException("Error of Arguments Length");
        }
        int i = 0;
        for (StructField allStructFieldRef : argOIs.getAllStructFieldRefs()) {

            if (!ObjectInspector.Category.PRIMITIVE.equals(allStructFieldRef.getFieldObjectInspector().getCategory())) {
                throw new UDFArgumentTypeException(0, "Error of Arguments Type");
            }
        }


        List<String> fieldNames = new ArrayList<>();
        List<ObjectInspector> fieldOIs = new ArrayList<>();
        int length = argOIs.getAllStructFieldRefs().size();

        for (int i1 = 0; i1 < length - 1; i1++) {
            fieldNames.add("_col" + i1);
        }

        for (int i1 = 0; i1 < length - 1; i1++) {
            fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
//            fieldOIs.add(argOIs.getAllStructFieldRefs().get(0).getFieldObjectInspector());
        }


        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    @Override
    public void process(Object[] objects) throws HiveException {
        String arg0 = objects[0].toString();
        String[] rows = splits(arg0, objects[1].toString());
        //get Columns
        for (String row : rows) {
            forwarddatas.clear();
            for (int i = 2; i < objects.length; i++) {
                row = row.replace(objects[i].toString(), "~@~");
            }
            System.out.println("row = " + row);
            for (String s : row.split("~@~")) {
                forwarddatas.add(s);
            }
            forward(forwarddatas);

        }



    }


    private String[] splits(String str1,String str2){

        return str1.split(str2);
    }

    @Override
    public void close() throws HiveException {

    }
}
