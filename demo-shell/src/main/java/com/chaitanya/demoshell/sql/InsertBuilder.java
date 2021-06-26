package com.chaitanya.demoshell.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Component;

import com.chaitanya.demoshell.annotation.One2One;
import com.chaitanya.demoshell.annotation.Column;
import com.chaitanya.demoshell.annotation.One2Many;
import com.chaitanya.demoshell.annotation.Table;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InsertBuilder {
	
	private final SQLContext context;
	
	public void build(Object object, boolean cascade) {
		String tableName = getTableName(object.getClass());
		Map<String, String> colsAndVals = getColumnsAndValues(object);

		Insert.builder().table(tableName)
			.columns(colsAndVals.keySet())
			.values(colsAndVals.values())
			.context(context)
			.build()
			.builSQL();
		
		if(cascade)
			this.buildAssociations(object);
	}
	
	@SuppressWarnings("unchecked")
	public void buildAssociations(Object object) {
		try {
			//one-to-one association
			Field[] fieldsWithAnnotation = FieldUtils.getFieldsWithAnnotation(object.getClass(), One2One.class);
			for (Field f : fieldsWithAnnotation) {
				f.setAccessible(true);
				this.build(f.get(object), true);
			}
			
			//one-to-many association
			fieldsWithAnnotation = FieldUtils.getFieldsWithAnnotation(object.getClass(), One2Many.class);
			for (Field f : fieldsWithAnnotation) {
				f.setAccessible(true);
				List<Object> associatedObjects = (ArrayList<Object>) f.get(object);
				for(Object obj: associatedObjects)
					this.build(obj, true);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String getTableName(Class<?> reflectClass) {
		if (reflectClass.isAnnotationPresent(Table.class)) {
			Table tableAnnotation = (Table) reflectClass.getAnnotation(Table.class);
			return tableAnnotation.table();
		} else
			throw new RuntimeException("table name not found");
	}

	public Map<String, String> getColumnsAndValues(Object object) {
		Map<String, String> data = new LinkedHashMap<>();
		Field[] fieldsWithAnnotation = FieldUtils.getFieldsWithAnnotation(object.getClass(), Column.class);

		Arrays.stream(fieldsWithAnnotation).forEach(f -> {
			try {
				Column colAnnotation = (Column) f.getAnnotation(Column.class);
				f.setAccessible(true);
				
				String key = colAnnotation.name();
				String value = String.valueOf(f.get(object));
				boolean mandatory = colAnnotation.mandatory();
				boolean isId = colAnnotation.isId();

				if (mandatory && "null".equals(value))
					throw new RuntimeException("mandatory field " + f.getName() + " can not be null");
				else
					data.put(key, (isId) ? value : "'" + value + "'");

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
		return data;
	}
}