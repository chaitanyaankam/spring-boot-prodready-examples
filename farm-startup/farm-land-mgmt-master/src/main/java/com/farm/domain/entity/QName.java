package com.farm.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.farm.domain.Name;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QName is a Querydsl query type for Name
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QName extends BeanPath<Name> {

    private static final long serialVersionUID = 1699221674L;

    public static final QName name = new QName("name");

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public final StringPath middleName = createString("middleName");

    public QName(String variable) {
        super(Name.class, forVariable(variable));
    }

    public QName(Path<? extends Name> path) {
        super(path.getType(), path.getMetadata());
    }

    public QName(PathMetadata metadata) {
        super(Name.class, metadata);
    }

}

