package com.farm.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.farm.domain.LandSize;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QLandSize is a Querydsl query type for LandSize
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QLandSize extends BeanPath<LandSize> {

    private static final long serialVersionUID = -1641584853L;

    public static final QLandSize landSize = new QLandSize("landSize");

    public final NumberPath<Integer> area = createNumber("area", Integer.class);

    public final StringPath unit = createString("unit");

    public QLandSize(String variable) {
        super(LandSize.class, forVariable(variable));
    }

    public QLandSize(Path<? extends LandSize> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLandSize(PathMetadata metadata) {
        super(LandSize.class, metadata);
    }

}

