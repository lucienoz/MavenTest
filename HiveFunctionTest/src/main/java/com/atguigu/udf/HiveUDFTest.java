package com.atguigu.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/1.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class HiveUDFTest extends GenericUDF {

    /**
     * 参数数据类型校验，定义当前函数的返回值
     *
     * @param objectInspectors
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        if (objectInspectors == null || objectInspectors.length != 1) {
            throw new UDFArgumentLengthException("Error of Arguments Length");
        }

        if (!ObjectInspector.Category.PRIMITIVE.equals(objectInspectors[0].getCategory())) {
            throw new UDFArgumentTypeException(0, "Error of Arguments Type");
        }

        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }

    /**
     * 业务计算逻辑规则
     *
     * @param deferredObjects
     * @return
     * @throws HiveException
     */
    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        if (deferredObjects[0].get() == null) {
            return 0;
        }

        String s = deferredObjects[0].get().toString();
        return s.length();
    }

    @Override
    public String getDisplayString(String[] strings) {
        return "";
    }
}
