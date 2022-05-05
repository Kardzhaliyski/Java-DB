package orm;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityManager<E> implements DbContext<E> {
    private final Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field idColumn = getIdColumn(entity.getClass());
        idColumn.setAccessible(true);
        Object idValue = idColumn.get(entity);

        if (idValue == null || (long) idValue <= 0) {
            int generatedKey = doInsert(entity);
            idColumn.set(entity, generatedKey);
            return true;
        } else {
            doUpdate(entity, idValue);
            return false;
        }
    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(table, null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String tableName = getTableName(table);

        String query = String.format("SELECT * FROM %s %s",
                tableName,
                where != null ? "WHERE " + where : "");

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<E> entityList = new ArrayList<>();

        Constructor<E> declaredConstructor = table.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        while (resultSet.next()) {
            E entity = declaredConstructor.newInstance();
            fillEntity(table, resultSet, entity);
            entityList.add(entity);
        }

        return entityList;
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table, null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String tableName = getTableName(table);

        String query = String.format("SELECT * FROM %s %s LIMIT 1",
                tableName,
                where != null ? "WHERE " + where : "");

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        Constructor<E> declaredConstructor = table.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        E entity = declaredConstructor.newInstance();

        resultSet.next();
        fillEntity(table, resultSet, entity);

        return entity;
    }

    private void fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Field[] fields = Arrays.stream(table.getDeclaredFields()).toArray(Field[]::new);

        for (Field field : fields) {
            field.setAccessible(true);
            fillField(field, resultSet, entity);
        }

    }

    private void fillField(Field field, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        field.setAccessible(true);
        String fieldName = field.getAnnotationsByType(Column.class)[0].name();
        if(field.getType() == int.class || field.getType() == long.class) {
            field.set(entity, resultSet.getInt(fieldName));
        } else if(field.getType() == LocalDate.class) {
            field.set(entity, LocalDate.parse(resultSet.getString(fieldName)));
        } else {
            field.set(entity, resultSet.getString(fieldName));
        }
    }

    private Field getIdColumn(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity missing an Id column"));
    }


    private int doInsert(E entity) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity.getClass());
        String[] tableFields = getColumnsWithoutId(entity.getClass());
        String[] tableValues = getColumnsValuesWithoutId(entity);

        String insertQuery = String.format("INSERT INTO %s(%s) VALUES (%s);",
                tableName,
                String.join(", ", tableFields),
                String.join(", ", tableValues));

        PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getInt(1);
    }

    private int doUpdate(E entity, Object id) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity.getClass());
        String[] tableFields = getColumnsWithoutId(entity.getClass());
        String[] tableValues = getColumnsValuesWithoutId(entity);

        ArrayList<String> updates = new ArrayList<>();
        for (int i = 0; i < tableFields.length; i++) {
            updates.add(tableFields[i] + " = " + tableValues[i]);
        }

        String updateQuery = String.format("UPDATE %s SET %s WHERE %s = %s;",
                tableName,
                String.join(", ", updates),
                getIdColumnName(entity),
                id);

        return connection.prepareStatement(updateQuery).executeUpdate();
    }

    private String[] getColumnsValuesWithoutId(E entity) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            if(field.getAnnotationsByType(Id.class).length != 0) {
                continue;
            }

            field.setAccessible(true);
            values.add(field.get(entity));
        }

        return formatValues(values);
    }

    private String[] formatValues(List<Object> values) {
        List<String> formattedValues = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            Object value = values.get(i);
            if(value instanceof LocalDate || value instanceof String) {
                formattedValues.add("'" + value +  "'");
            }else {
                formattedValues.add(value.toString());
            }
        }

        return formattedValues.toArray(String[]::new);
    }

    private String[] getColumnsWithoutId(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> f.getAnnotationsByType(Id.class).length == 0)
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotationsByType(Column.class))
                .map(a -> a[0].name())
                .toArray(String[]::new);
    }

    private String getTableName(Class<?> entity) {
        Entity[] annotationsByType = entity.getAnnotationsByType(Entity.class);

        if (annotationsByType.length == 0) {
            throw new UnsupportedOperationException("Class must be Entity");
        }

        return annotationsByType[0].name();
    }

    private String getIdColumnName(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.getAnnotationsByType(Id.class).length == 1)
                .map(f -> f.getAnnotationsByType(Column.class)[0])
                .toArray(Column[]::new)[0].name();
    }
}
