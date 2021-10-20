package com.atguigu.udtf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.*;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/1.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class HiveUDTFTest extends GenericUDTF {
    private transient ArrayList<String> dataArr = new ArrayList<>();

    /**
     * 参数检测和定义返回值类型
     * @param argOIs
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        //参数检测
        List<? extends StructField> allStructFieldRefs = argOIs.getAllStructFieldRefs();
        if (allStructFieldRefs.isEmpty()) {
            throw new UDFArgumentLengthException("Error of Arguments Length");
        }
        for (StructField structFieldRef : allStructFieldRefs) {
            if (!ObjectInspector.Category.PRIMITIVE.equals(structFieldRef.getFieldObjectInspector().getCategory())) {
                throw new UDFArgumentTypeException(0,"Error of Arguments Type");
            }
        }

        //参数定义

        ArrayList<String> fieldNames = new ArrayList();
        ArrayList<ObjectInspector> fieldOIs = new ArrayList();

        fieldNames.add("col");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);



    }

    @Override
    public void process(Object[] objects) throws HiveException {
        String arg0 = objects[0].toString();
        String arg1 = objects[1].toString();
        String[] datas = arg0.split(arg1);
        for (String data : datas) {
            dataArr.clear();
            dataArr.add(data);
            forward(data);

        }

    }

    @Override
    public void close() throws HiveException {
        System.out.println("");
    }
}
