package lk.ijse.dep11.orm;

import lk.ijse.dep11.orm.annotation.Column;
import lk.ijse.dep11.orm.annotation.Id;
import lk.ijse.dep11.orm.annotation.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SimpleOrm {
    // Object Relation Mapping

    private final Connection connection;

    public SimpleOrm(Connection connection) {
        this.connection = connection;
    }

    public void register(Class<?> annotatedEntityClass) {
        Table tableAnnotation = annotatedEntityClass.getDeclaredAnnotation(Table.class);
        if (tableAnnotation == null) throw new RuntimeException("This is not an entity class");

        String tableName = tableAnnotation.value();
        List<String> columnDefinitions = new ArrayList<>();

        Field[] fields = annotatedEntityClass.getDeclaredFields();
        for (Field field : fields) {
            Column columnAnnotation = field.getDeclaredAnnotation(Column.class);
            Id idAnnotation = field.getDeclaredAnnotation(Id.class);
            if (columnAnnotation == null) continue;

            String colName = columnAnnotation.name().isBlank() ? field.getName(): columnAnnotation.name();
            String colDataType;
            switch (field.getType().getSimpleName()) {
                case "int": case "long":
                    colDataType = "INT";
                    break;
                case "boolean":
                    colDataType = "BOOLEAN";
                    break;
                case "float": case "double":
                    colDataType = "DOUBLE";
                    break;
                default:
                    colDataType = "VARCHAR(500)";
            }
            String collNullable = columnAnnotation.nullable() ? "" : "NOT NULL";
            String colPrimaryKey = idAnnotation == null ? "" : "PRIMARY KEY";
            String columnDef = String.format("%s %s %s %s", colName, colDataType, collNullable, colPrimaryKey);
            columnDefinitions.add(columnDef);
        }
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append("( \n");
        for (String columnDefinition : columnDefinitions) {
            sql.append("\t").append(columnDefinition).append(", \n");
        }
        sql.delete(sql.length() - 4 , sql.length());
        sql.append("\n)");
        System.out.println(sql);

        try {
            Statement stm = connection.createStatement();
            stm.execute(sql.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
