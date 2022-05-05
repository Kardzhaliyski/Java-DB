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


    private int doInsert(E entity) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity.getClass());
        String[] tableFields = getColumnNamesWithoutId(entity.getClass());
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
        String[] tableFields = getColumnNamesWithoutId(entity.getClass());
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

    public void doCreate(Class<?> aClass) throws SQLException {
        String tableName = getTableName(aClass);


        String updateQuery = String.format("CREATE TABLE `%s` (id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, %s)",
                tableName,
                getAllFieldsAndDataTypesWithoutId(aClass));

        connection.prepareStatement(updateQuery).execute();

    }

    public void doAlter (Class<?> aClass) throws SQLException {
        String tableName = getTableName(aClass);

        String[] classColumnNames = getColumnNamesWithoutId(aClass);
        List<String> tableColumnNames = getTableColumnNames(tableName);
        List<String> columnsNamesToAdd = new ArrayList<>();
        for (String columnName : classColumnNames) {
            if(!tableColumnNames.contains(columnName)) {
                columnsNamesToAdd.add(columnName);
            }
        }

        if(columnsNamesToAdd.size() == 0) {
            return;
        }

        List<String> addColumnsStatements = new ArrayList<>();

        Arrays.stream(aClass.getDeclaredFields())
                .filter(c -> columnsNamesToAdd.contains(c.getAnnotationsByType(Column.class)[0].name()))
                .forEach(f -> addColumnsStatements
                        .add("ADD COLUMN " + f.getAnnotation(Column.class).name() + " " + parseVariableType(f.getType())));


        String query = String.format("ALTER TABLE %s %s",
                aClass.getAnnotation(Entity.class).name(),
                String.join(", ", addColumnsStatements));

        connection.prepareStatement(query).executeUpdate();
    }

    public int doDelete(Class<?> aClass, String where) throws SQLException {
        String tableName = getTableName(aClass);
        String query = String.format("DELETE FROM %s WHERE %s",
                tableName,
                where);

        return connection.prepareStatement(query).executeUpdate();
    }

    private Field getIdColumn(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity missing an Id column"));
    }

    private String getIdColumnName(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.getAnnotationsByType(Id.class).length == 1)
                .map(f -> f.getAnnotationsByType(Column.class)[0])
                .toArray(Column[]::new)[0].name();
    }

    public List<String> getTableColumnNames(String tableName) throws SQLException {
        String query = String.format("SELECT COLUMN_NAME FROM information_schema.`COLUMNS` WHERE TABLE_NAME  = '%s' ;"
                , tableName);
        ResultSet resultSet = connection.prepareStatement(query).executeQuery();
        List<String> columnNames = new ArrayList<>();
        while (resultSet.next()) {
            columnNames.add(resultSet.getString("COLUMN_NAME"));
        }


        return columnNames;
    }

    private String getAllFieldsAndDataTypesWithoutId(Class<?> aClass) {
        List<String> fields = new ArrayList<>();
        for (Field field : aClass.getDeclaredFields()) {
            if(field.getAnnotationsByType(Id.class).length != 0) {
                continue;
            }
            fields.add(field.getAnnotation(Column.class).name() + " " + parseVariableType(field.getType()));

        }

        return String.join(", ", fields);
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

    private String[] getColumnNamesWithoutId(Class<?> aClass) {
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

    private String parseVariableType(Class<?> type) {
        if(type == String.class) {
            return "VARCHAR(255)";
        } else if (type == int.class || type == Integer.class) {
            return "INT";
        } else if (type == LocalDate.class) {
            return "DATE";
        } else if (type == long.class || type == Long.class) {
            return "BIGINT";
        } else {
            return null;
        }
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
}
